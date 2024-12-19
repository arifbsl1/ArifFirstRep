# Use a base image with JDK
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file into the Docker container
COPY target/ArifFirstProject.jar /app/ArifFirstProject.jar

# Expose the port that the application will run on
EXPOSE 8182

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "ArifFirstProject.jar"]
