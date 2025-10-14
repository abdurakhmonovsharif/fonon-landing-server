# syntax=docker/dockerfile:1

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml ./
RUN mvn -ntp dependency:go-offline
COPY src ./src
RUN mvn -ntp package -DskipTests

FROM eclipse-temurin:21.0.3_9-jre
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=default
ARG JAR_FILE=target/*.jar
COPY --from=build /workspace/${JAR_FILE} app.jar
COPY init.sql ./init.sql
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]
