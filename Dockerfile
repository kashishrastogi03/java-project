# Use OpenJDK 17 slim as base image
FROM openjdk:17-jdk-slim

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y curl && \
    apt-get upgrade -y && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy WAR file instead of JAR
COPY target/*.war app.war

RUN curl -o /wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /wait-for-it.sh

EXPOSE 9091

# Run the WAR file (assumes embedded Tomcat)
ENTRYPOINT ["/wait-for-it.sh", "db:3306", "--timeout=60", "--", "java", "-jar", "app.war"]

