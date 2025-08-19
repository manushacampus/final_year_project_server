# Stage 1: Build stage
FROM gradle:7.2-jdk11 AS build
WORKDIR /app

# Copy Gradle files first for caching
COPY build.gradle settings.gradle ./

# Copy source code
COPY src ./src

# Build the project
RUN gradle build --no-daemon

# Stage 2: Runtime stage
FROM openjdk:11-jre-slim
WORKDIR /app

# Copy the JAR from the build stage
# Using wildcard in case version changes
COPY --from=build /app/build/libs/*.jar my-spring-app.jar

# Expose the port in the runtime image
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "my-spring-app.jar"]
