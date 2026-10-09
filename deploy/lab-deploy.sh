#!/usr/bin/env bash
#
# lab-deploy.sh - lab-reservation-system 部署与服务管理
#
# 用法:
#   ./lab-deploy.sh start     # 后台启动（prod 配置随 jar，profile=prod 自动加载）
#   ./lab-deploy.sh stop      # 停止
#   ./lab-deploy.sh restart   # 重启（更新 jar 后用这个）
#   ./lab-deploy.sh status    # 查看状态
#   ./lab-deploy.sh logs      # tail -f 日志
#
# 部署步骤:
#   1) 把 lab-server-0.1.0.jar、本脚本、app.env.example 传到服务器同一目录
#   2) chmod +x lab-deploy.sh
#   3) cp app.env.example config/app.env && vi config/app.env  # 拷到 config/ 并填 DB/JWT 真实值（Redis 默认本机无密码）
#   4) 准备 config/rsa.env（登录加密密钥对，见 docs/deploy.md §3.3）
#   5) ./lab-deploy.sh start
#
# 配置说明:
#   - application-prod.yml 已随 jar 打包（src/main/resources/），只含 ${...} 占位符，
#     无敏感值，可入库。本脚本不再生成任何 yml。
#   - 本脚本只负责把 config/app.env（DB/Redis/JWT）与 config/rsa.env（RSA 密钥）source 成
#     环境变量，然后 java -jar ... --spring.profiles.active=prod；Spring 启动时按
#     application-prod.yml 的占位符从环境变量解析真实值。
#   - config/app.env 与 config/rsa.env 不入库（.gitignore）；app.env.example 为模板，入库（部署时拷到 config/app.env）。
#
set -euo pipefail

# ===================== 路径 =====================
APP_NAME="lab-rbac"
JAR_NAME="lab-server-0.1.0.jar"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
JAR_FILE="$SCRIPT_DIR/$JAR_NAME"
LOG_DIR="$SCRIPT_DIR/logs"
LOG_FILE="$LOG_DIR/app.log"
PID_FILE="$SCRIPT_DIR/app.pid"
RSA_ENV_FILE="$SCRIPT_DIR/config/rsa.env"
LEGACY_CONFIG="$SCRIPT_DIR/config/application-prod.yml"   # 旧版脚本生成的外部配置，需清理（见 start）
ENV_FILE="$SCRIPT_DIR/config/app.env"

# ===================== 加载敏感配置（config/app.env, 不入库）=====================
# 迁移：旧版 deploy.env（脚本同级）-> config/app.env（改名 + 移入 config/）
if [[ -f "$SCRIPT_DIR/deploy.env" && ! -f "$ENV_FILE" ]]; then
  mkdir -p "$SCRIPT_DIR/config"
  mv "$SCRIPT_DIR/deploy.env" "$ENV_FILE"
  echo "[迁移] $SCRIPT_DIR/deploy.env -> $ENV_FILE（deploy.env 已改名 app.env 并移入 config/）" >&2
fi
if [[ ! -f "$ENV_FILE" ]]; then
  echo "错误: 未找到 $ENV_FILE。请从 app.env.example 拷到 config/app.env 并填真实值:" >&2
  echo "  cp app.env.example config/app.env && vi config/app.env" >&2
  exit 1
fi
set -a; . "$ENV_FILE"; set +a

# ===================== 校验必填项（缺则报错，避免 Spring 启动时才挂）=====================
# JVM / 运行
JAVA_BIN="${JAVA_BIN:-java}"
JAVA_OPTS="${JAVA_OPTS:--Xms256m -Xmx512m}"
PROFILE="${PROFILE:-prod}"
# 端口单一真值：Spring 读 SERVER_PORT；脚本自检/日志用的 PORT 从它派生
# （兼容旧 env 只写 PORT 的情况）。这样 config/app.env 里的端口会真正传给 Spring，
# 不再出现「nginx 指向一个端口、应用实际听另一个端口」的错配。
PORT="${SERVER_PORT:-${PORT:-8080}}"
export SERVER_PORT="$PORT"

# 数据库
DB_HOST="${DB_HOST:?config/app.env 未设置 DB_HOST}"
DB_PORT="${DB_PORT:-3306}"
DB_NAME="${DB_NAME:-lab_reservation}"
DB_USER="${DB_USER:?config/app.env 未设置 DB_USER}"
DB_PASS="${DB_PASS:?config/app.env 未设置 DB_PASS}"

# Redis（会话白名单，默认本机无密码）
REDIS_HOST="${REDIS_HOST:-127.0.0.1}"
REDIS_PORT="${REDIS_PORT:-6379}"
REDIS_DB="${REDIS_DB:-0}"

# JWT 密钥（>=32 字节）
JWT_SECRET="${JWT_SECRET:?config/app.env 未设置 JWT_SECRET}"
# ============================================================

log()  { echo "[$(date '+%F %T')] $*"; }

usage() {
  cat <<'U'
用法:
  ./lab-deploy.sh start     后台启动（prod 配置随 jar，profile=prod 自动加载）
  ./lab-deploy.sh stop      停止
  ./lab-deploy.sh restart   重启（更新 jar 后用这个）
  ./lab-deploy.sh status    查看状态
  ./lab-deploy.sh logs      tail -f 日志
U
  exit 1
}

is_running() { [[ -f "$PID_FILE" ]] && kill -0 "$(cat "$PID_FILE")" 2>/dev/null; }

check_java() {
  command -v "$JAVA_BIN" >/dev/null 2>&1 || { log "错误: 找不到 java，请先安装 JDK 17+"; exit 1; }
  local jv
  jv=$("$JAVA_BIN" -version 2>&1 | sed -En '1s/.*version "([0-9]+).*/\1/p')
  if ! [[ "$jv" =~ ^[0-9]+$ ]] || (( jv < 17 )); then
    log "错误: 需要 JDK 17+，当前: $("$JAVA_BIN" -version 2>&1 | head -1)"
    exit 1
  fi
}

start() {
  if is_running; then log "已在运行 PID=$(cat "$PID_FILE")"; exit 0; fi
  [[ -f "$JAR_FILE" ]] || { log "错误: 找不到 jar: $JAR_FILE"; exit 1; }
  check_java
  mkdir -p "$LOG_DIR"

  # 清理旧版外部配置：旧 lab-deploy.sh 曾生成 config/application-prod.yml，Spring 会优先加载
  # 外部 config/ 下的同名文件从而覆盖 jar 内的，导致去密码等改动不生效。prod 配置已迁入 jar，这里清掉。
  if [[ -f "$LEGACY_CONFIG" ]]; then
    rm -f "$LEGACY_CONFIG"
    log "已清理旧外部配置 $LEGACY_CONFIG（prod 配置已随 jar 打包）"
  fi

  # 注入 RSA 公私钥（服务端 config/rsa.env，chmod 600，不入库）-> 环境变量，供 application-prod.yml 的 ${RSA_*} 解析
  if [[ -f "$RSA_ENV_FILE" ]]; then
    set -a; . "$RSA_ENV_FILE"; set +a
    log "已加载 RSA 密钥: $RSA_ENV_FILE"
  else
    log "⚠️ 未找到 $RSA_ENV_FILE（开启登录加密时缺私钥会导致启动失败）"
  fi

  log "启动 $APP_NAME ..."
  cd "$SCRIPT_DIR"   # 工作目录定位到 jar 同级（logs/、uploads/ 等相对路径基于此）
  nohup "$JAVA_BIN" $JAVA_OPTS \
    -Dfile.encoding=UTF-8 \
    -jar "$JAR_FILE" \
    --spring.profiles.active="$PROFILE" \
    > "$LOG_FILE" 2>&1 &
  echo $! > "$PID_FILE"

  log "等待启动（最多 40s）..."
  local i
  for ((i=1; i<=40; i++)); do
    if grep -q "Started LabApplication" "$LOG_FILE" 2>/dev/null; then
      log "启动成功 PID=$(cat "$PID_FILE") 端口=$PORT"
      log "Swagger: http://<本机IP>:$PORT/swagger-ui.html"
      exit 0
    fi
    if ! is_running; then
      log "启动失败，进程已退出。日志末尾："
      tail -30 "$LOG_FILE" || true
      rm -f "$PID_FILE"
      exit 1
    fi
    sleep 1
  done
  log "启动超时（40s 未看到 Started），检查日志: $LOG_FILE"
  tail -20 "$LOG_FILE" || true
  exit 1
}

stop() {
  if ! is_running; then log "未运行"; rm -f "$PID_FILE"; exit 0; fi
  local pid; pid=$(cat "$PID_FILE")
  log "停止 PID=$pid ..."
  kill "$pid" 2>/dev/null || true
  for ((i=1; i<=20; i++)); do
    kill -0 "$pid" 2>/dev/null || break
    sleep 1
  done
  if kill -0 "$pid" 2>/dev/null; then
    log "未在 20s 内退出，强制 kill -9"
    kill -9 "$pid" 2>/dev/null || true
  fi
  rm -f "$PID_FILE"
  log "已停止"
}

status() {
  if is_running; then
    log "运行中 PID=$(cat "$PID_FILE") 端口=$PORT"
    if command -v ss >/dev/null 2>&1 && ss -ltn 2>/dev/null | grep -q ":$PORT "; then
      log "端口 $PORT 已监听"
    elif command -v lsof >/dev/null 2>&1 && lsof -iTCP:$PORT -sTCP:LISTEN -P -n >/dev/null 2>&1; then
      log "端口 $PORT 已监听"
    else
      log "端口 $PORT 未监听（启动中？）"
    fi
  else
    log "未运行"
  fi
}

logs() { [[ -f "$LOG_FILE" ]] || { log "无日志: $LOG_FILE"; exit 1; }; tail -f "$LOG_FILE"; }

main() {
  case "${1:-}" in
    start)   start;;
    stop)    stop;;
    restart) stop; start;;
    status)  status;;
    logs)    logs;;
    *)       usage;;
  esac
}

main "$@"
