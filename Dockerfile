# Use the official OpenJDK base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/your-spring-boot-app.jar app.jar

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
