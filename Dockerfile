# syntax=docker/dockerfile:1

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml ./
RUN mvn -ntp dependency:go-offline
COPY src ./src
RUN mvn -ntp package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=default
COPY --from=build /workspace/target/fonon-landing-server-1.0.0.jar app.jar
COPY init.sql ./init.sql
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
