# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Add metadata
LABEL maintainer="Chandu Ittamsetty"

# Set the working directory
WORKDIR /app

# Copy the JAR file from the target folder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
