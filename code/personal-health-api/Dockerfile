# 第 1 阶段：构建 jar 包
FROM maven:3.8.1-jdk-8-slim AS builder

WORKDIR /app

# 使用阿里云 Maven 源（可选）
COPY settings.xml /usr/share/maven/conf/settings.xml

# 拷贝项目文件
COPY pom.xml .
COPY src ./src

# 打包 jar，跳过测试
RUN mvn clean package -DskipTests

# 第 2 阶段：运行 jar（使用轻量级 JRE 镜像）
FROM openjdk:8

WORKDIR /app

# 拷贝构建产物
COPY --from=builder /app/target/personal-health-api-1.0-SNAPSHOT.jar app.jar
COPY pic ./pic
# 若有 application-prod.yml 外部配置文件，也可 COPY 进来
# COPY application-prod.yml /app/config/

EXPOSE 21090

# 启动服务
#CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
CMD ["java",  "-Dcustom.upload.dir=/app", "-jar", "app.jar"]
