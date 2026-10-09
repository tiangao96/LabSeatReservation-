#!/bin/bash
# 服务器侧初始化: 安装 Redis + 生成后端配置(app.env / RSA 密钥对) + 启动后端 + 探活。
# 注意: 本脚本不含任何真实凭据, DB 口令/JWT 由环境变量或交互输入提供。
set -e

echo "===== 1. 安装 Redis ====="
if ! command -v redis-server >/dev/null 2>&1; then
  (dnf install -y redis 2>&1 || yum install -y redis 2>&1 || echo "REPOS_FAIL")
fi
if command -v redis-server >/dev/null 2>&1; then
  systemctl enable --now redis 2>/dev/null || systemctl enable --now redis-server 2>/dev/null || true
  sleep 2
  echo "redis ping => $(redis-cli ping 2>&1)"
else
  echo "!!! redis-server 仍不可用, 需人工处理 (可选项, 仅用于会话白名单)"
fi

echo "===== 2. 生成 config/app.env ====="
cd "$(dirname "$0")"          # deploy/ (lab-deploy.sh 在此查找 config/)
mkdir -p config
if [[ -f config/app.env ]]; then
  echo "config/app.env 已存在, 跳过 (如需重建请先备份删除)"
else
  DB_PASS_IN="${DB_PASS:?请用环境变量 DB_PASS=<口令> 运行本脚本, 避免把凭据写进仓库}"
  JWT=$(openssl rand -hex 48)
  cat > config/app.env <<ENVEOF
DB_HOST=${DB_HOST:-127.0.0.1}
DB_PORT=${DB_PORT:-3306}
DB_NAME=${DB_NAME:-lab_reservation}
DB_USER=${DB_USER:-lab_reservation}
DB_PASS=$DB_PASS_IN
JWT_SECRET=$JWT
PORT=${PORT:-8090}
SERVER_PORT=${SERVER_PORT:-8090}
ENVEOF
  chmod 600 config/app.env
  echo "config/app.env 已生成 (port=${SERVER_PORT:-8090})"
fi

echo "===== 3. 生成 RSA 登录密钥对 ====="
if [[ -f config/rsa.env ]]; then
  echo "config/rsa.env 已存在, 跳过"
else
  openssl genpkey -algorithm RSA -pkeyopt rsa_keygen_bits:2048 -out config/rsa.priv.pem
  openssl pkey -in config/rsa.priv.pem -pubout -out config/rsa.pub.pem
  { printf 'RSA_PUBLIC_KEY='; sed '1d;$d' config/rsa.pub.pem | tr -d "\n"; echo
    printf 'RSA_PRIVATE_KEY='; sed '1d;$d' config/rsa.priv.pem | tr -d "\n"; echo; } > config/rsa.env
  chmod 600 config/rsa.env
  rm -f config/rsa.priv.pem config/rsa.pub.pem
  echo "config/rsa.env 已生成 (不入库)"
fi

echo "===== 4. 启动后端 ====="
chmod +x lab-deploy.sh
./lab-deploy.sh start

echo "===== 5. 后端状态 ====="
./lab-deploy.sh status
PORT="${SERVER_PORT:-8090}"
echo "===== 公钥接口探测 ====="
curl -s "http://127.0.0.1:$PORT/api/auth/public-key" | head -c 120
echo
