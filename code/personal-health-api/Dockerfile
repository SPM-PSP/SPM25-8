FROM maven:3.8.4-openjdk-8 as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn package -DskipTests
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN mkdir -p /app/upload
EXPOSE 21090
ENTRYPOINT ["java", "-jar", "/app/app.jar"] 