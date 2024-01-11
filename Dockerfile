# Use a multi-stage build
# Stage 1: Build stage
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Stage 2: Application stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/final_project-0.0.1-SNAPSHOT.jar my-spring-app.jar
CMD ["java", "-jar", "my-spring-app.jar"]
