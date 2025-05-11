# Use the OpenJDK 17 slim base image
FROM openjdk:17-jdk-slim

# Set environment variables to avoid interactive prompts
ENV DEBIAN_FRONTEND=noninteractive

# Update apt-get, upgrade packages, and install the latest security patches
RUN apt-get update && apt-get upgrade -y && apt-get install --only-upgrade apt && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Set working directory to /app
WORKDIR /app

# Copy the built JAR file from the target folder to the container
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
