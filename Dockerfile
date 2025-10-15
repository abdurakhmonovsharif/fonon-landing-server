# syntax=docker/dockerfile:1

# ---------- Stage 1: Build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /workspace

# Copy only pom.xml first for dependency caching
COPY pom.xml ./
RUN mvn -ntp dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application JAR
RUN mvn -ntp clean package -DskipTests

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21.0.3_9-jre

# Set the working directory inside the final image
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /workspace/target/*.jar app.jar

# Copy optional initialization SQL (not required if using external DB)
COPY init.sql ./init.sql

# Expose internal port
EXPOSE 9090

# Environment variables (default profile)
ENV SPRING_PROFILES_ACTIVE=default

# Run the Spring Boot JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
