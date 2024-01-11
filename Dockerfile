# Stage 1: Build stage
FROM gradle:7.2-jdk11 AS build
WORKDIR /app
COPY build.gradle settings.gradle .
COPY src ./src
COPY dskiptest ./dskiptest
RUN gradle build --no-daemon

# Stage 2: Application stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/build/libs/final_project-0.0.1-SNAPSHOT.jar my-spring-app.jar
COPY --from=build /app/dskiptest ./dskiptest
CMD ["java", "-jar", "my-spring-app.jar"]
