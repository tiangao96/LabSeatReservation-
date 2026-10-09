# ---------------------------------------------------------------------------
# 多阶段构建：stage 1 用 Maven 打包，stage 2 只留 JRE + 可执行 jar。
#
#   docker build -t lab-reservation-system:1.0.0 .
#
# 构建产物：/app/app.jar（Spring Boot 可执行 jar，内含全部依赖）
# 运行时环境变量见 docker-compose.yml 或 README。
# ---------------------------------------------------------------------------

# ---------- stage 1: build ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# 先只拷 pom，利用 Docker 层缓存预热依赖；只有 pom 变了才重新拉依赖
COPY pom.xml .
RUN mvn -B -q dependency:go-offline || true

# 再拷源码，正式打包
COPY src ./src
RUN mvn -B -q clean package -DskipTests \
    && cp target/lab-server-*.jar /build/app.jar

# ---------- stage 2: runtime ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# wget 供 healthcheck 使用
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget \
    && rm -rf /var/lib/apt/lists/*

COPY --from=build /build/app.jar /app/app.jar

# 头像等上传文件的落盘目录（与 application.yml 的 app.storage.upload-dir 对应）
RUN mkdir -p /app/uploads

EXPOSE 8080

# 入口：把 SERVER_PORT 真的透传给 Spring（与 deploy/lab-deploy.sh 的做法一致）
ENTRYPOINT ["sh", "-c", "exec java -Dfile.encoding=UTF-8 -jar /app/app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} --server.port=${SERVER_PORT:-8080}"]
