FROM openjdk:8-jdk-alpine

ARG NEXUS_URL=http://192.168.33.10:8081/repository/maven-releases
ARG JAR_PATH=esprit/spring/khaddem/1.0/khaddem-4.0.jar
ARG FULL_PATH=http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/khaddem/1.0/khaddem-1.0-SNAPSHOT.jar
RUN apk add --no-cache curl \
    && curl -o Project.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/khaddem/1.0/khaddem-4.0.jar" \
    && apk del curl

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "Project.jar"]