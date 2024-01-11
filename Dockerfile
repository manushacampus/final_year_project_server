# Use the official OpenJDK base image
FROM openjdk:11-jre-slim

# Copy the compiled JAR file into the container
COPY target/final_project-0.0.1-SNAPSHOT.jar my-spring-app.jar

# Specify the command to run your application
CMD ["java", "-jar", "my-spring-app.jar"]
