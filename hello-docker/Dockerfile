# 指定基础镜像，这是分阶段构建的前期阶段
FROM openjdk:8-alpine as builder
# 执行工作目录
WORKDIR build
# 配置参数
ARG JAR_FILE=target/hello-docker-1.0.jar
# 将编译构建得到的jar文件复制到镜像空间中
COPY ${JAR_FILE} app.jar
# 通过工具spring-boot-jarmode-layertools从application.jar中提取拆分后的构建结果
RUN java -Djarmode=layertools -jar app.jar extract && rm app.jar

# 正式构建镜像
FROM openjdk:8-alpine

WORKDIR application

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

# 配置时区
RUN apk add -U tzdata \
    && cp /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo '$TZ' > /etc/timezone

# 前一阶段从jar中提取除了多个文件，这里分别执行COPY命令复制到镜像空间中，每次COPY都是一个layer
COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/application/ ./
COPY docker-entrypoint.sh docker-entrypoint.sh

ENTRYPOINT [ "/bin/sh", "docker-entrypoint.sh" ]
CMD ["run-hello-docker"]