#!/usr/bin/env bash
# ============================================================================
# lab-dev.sh - Lab System 服务器端编译开发工具
# 在服务器上直接修改反编译源码 -> javac 编译 -> 塞回 jar -> 重启
#
# 用法:
#   ./lab-dev.sh list [关键字]        列出可编辑的源码文件
#   ./lab-dev.sh edit <类名或路径>     用 vim 编辑源码（如 WorkstationService）
#   ./lab-dev.sh view <类名或路径>     查看源码内容
#   ./lab-dev.sh compile <类名或路径>  编译该类并塞回 jar（自动备份）
#   ./lab-dev.sh deploy               重启服务（jar 更新后用）
#   ./lab-dev.sh rollback             回滚 jar 到上一个备份并重启
#   ./lab-dev.sh backups              列出 jar 备份
#   ./lab-dev.sh status               服务状态
#   ./lab-dev.sh logs [行数]          查看最近日志
#   ./lab-dev.sh diff                 显示 src 与 baseline 的差异（改动清单）
#
# 示例:
#   ./lab-dev.sh edit WorkstationService
#   ./lab-dev.sh compile WorkstationService
#   ./lab-dev.sh deploy
# ============================================================================
set -euo pipefail

DEV=/opt/lab-dev
SRC=$DEV/src
BASELINE=$DEV/baseline/BOOT-INF/classes
DEPS=$DEV/deps/BOOT-INF/lib
OUT=$DEV/out
BACKUP_DIR=$DEV/backup
JAR=/root/lab-server/lab-server-0.1.0.jar
DEPLOY=/root/lab-server/lab-deploy.sh

# ---------- 工具函数 ----------
log()  { echo "[$(date '+%F %T')] $*"; }

find_java() {
  local name="$1"
  if [ -f "$SRC/$name" ]; then echo "$SRC/$name"; return 0; fi
  # 支持类名（如 WorkstationService）或带包名路径
  local f
  f=$(find "$SRC" -name "${name##*/}.java" -o -name "${name##*/}" 2>/dev/null | head -1)
  if [ -n "$f" ]; then echo "$f"; return 0; fi
  return 1
}

cls_of() {
  # /opt/lab-dev/src/com/lab/.../X.java -> com.lab....X
  local f="$1"
  echo "${f#$SRC/}" | sed 's/\.java$//' | tr '/' '.'
}

# ---------- 子命令 ----------
cmd_list() {
  local kw="${1:-}"
  echo "== 源码文件（$SRC）=="
  if [ -n "$kw" ]; then
    find "$SRC" -name '*.java' | grep -i "$kw" | sed "s|$SRC/||"
  else
    find "$SRC" -name '*.java' | sed "s|$SRC/||" | sort
  fi
}

cmd_edit() {
  local f; f=$(find_java "$1") || { echo "找不到: $1（试试 ./lab-dev.sh list 关键字）"; exit 1; }
  log "编辑: $f"
  vim "$f"
  log "保存完成。编译部署: ./lab-dev.sh compile $1 && ./lab-dev.sh deploy"
}

cmd_view() {
  local f; f=$(find_java "$1") || { echo "找不到: $1"; exit 1; }
  cat -n "$f"
}

cmd_compile() {
  local f; f=$(find_java "$1") || { echo "找不到: $1"; exit 1; }
  local cls; cls=$(cls_of "$f")
  local class_file="$OUT/BOOT-INF/classes/${f#$SRC/}"
  class_file="${class_file%.java}.class"

  log "编译: $cls"
  log "  源文件: $f"
  mkdir -p "$(dirname "$class_file")"
  if ! javac -encoding UTF-8 \
      -cp "$BASELINE:$DEPS/*" \
      -d "$OUT/BOOT-INF/classes" \
      "$f" 2>&1 | tee /tmp/lab-dev-compile.log; then
    log "❌ 编译失败，见上面错误。jar 未改动。"
    exit 1
  fi
  [ -f "$class_file" ] || { log "❌ 编译产物不存在: $class_file"; exit 1; }
  log "✅ 编译成功: $class_file"

  # 自动备份 jar（若今日尚无备份）
  local bak="$BACKUP_DIR/lab-server-$(date +%Y%m%d-%H%M%S).jar.bak"
  mkdir -p "$BACKUP_DIR"
  cp "$JAR" "$bak"
  log "已备份 jar -> $bak"

  # 塞回 jar
  ( cd "$OUT" && jar uf "$JAR" "BOOT-INF/classes/${f#$SRC/}" "${class_file#$OUT/}" 2>/dev/null \
    || jar uf "$JAR" -C "$OUT" "${class_file#$OUT/}" )
  log "已塞回 jar: ${class_file#$OUT/}"
  log "下一步: ./lab-dev.sh deploy（重启生效）"
}

cmd_deploy() {
  log "重启服务..."
  "$DEPLOY" restart
  log "完成。可用 ./lab-dev.sh status / logs 验证"
}

cmd_rollback() {
  local latest
  latest=$(ls -t "$BACKUP_DIR"/*.jar.bak 2>/dev/null | head -1) || { echo "无备份可回滚"; exit 1; }
  log "回滚到: $latest"
  cp "$latest" "$JAR"
  log "jar 已恢复，重启中..."
  "$DEPLOY" restart
}

cmd_backups() { ls -lt "$BACKUP_DIR"/*.jar.bak 2>/dev/null || echo "暂无备份"; }

cmd_status() { "$DEPLOY" status; }

cmd_logs() {
  local n="${1:-50}"
  tail -n "$n" /root/lab-server/logs/app.log
}

cmd_diff() {
  echo "== 改动清单（src 与 jar 基线对比）=="
  # 无历史基线源码，通过对比编译产物时间戳判断
  find "$SRC" -name '*.java' -newer "$JAR" 2>/dev/null | sed "s|$SRC/||" || true
  echo "（以上为修改时间晚于 jar 的源文件；若为空说明尚无改动）"
}

usage() {
  sed -n '2,24p' "$0" | sed 's/^# \{0,1\}//'
  exit 1
}

main() {
  case "${1:-}" in
    list)     cmd_list "${2:-}";;
    edit)     cmd_edit "$2";;
    view)     cmd_view "$2";;
    compile)  cmd_compile "$2";;
    deploy)   cmd_deploy;;
    rollback) cmd_rollback;;
    backups)  cmd_backups;;
    status)   cmd_status;;
    logs)     cmd_logs "${2:-50}";;
    diff)     cmd_diff;;
    *)        usage;;
  esac
}

main "$@"
