# Use OpenJDK 11 JRE base image
FROM openjdk:11-jre-slim

# Install curl
RUN apt-get update && apt-get install -y curl

# Set a working directory
WORKDIR /app

# Download the .jar file from Nexus
RUN curl -o app.jar "http://192.168.1.185:8081/repository/maven-snapshots/tn/esprit/khaddem/4.0-SNAPSHOT/khaddem-4.0-SNAPSHOT.jar"

# Expose the port the app runs on
EXPOSE 8089

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
