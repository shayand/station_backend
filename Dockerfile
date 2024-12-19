# Use the official OpenJDK 21 as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application jar file
COPY target/*.jar app.jar

# Copy the application.yml file
COPY src/main/resources/application-prod.yml /app/application.yml.template

# Install dependencies to handle .env file
RUN apt-get update && apt-get install -y \
    gettext-base \
    && rm -rf /var/lib/apt/lists/*

# Copy .env file to the container
COPY .env /app/.env

RUN export $(cat /app/.env | xargs) && envsubst < /app/application.yml.template > /app/application.yml

# Expose the port that the application runs on
EXPOSE ${TOMCAT_PORT:-9096}

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]