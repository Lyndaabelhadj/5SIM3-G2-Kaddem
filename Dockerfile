FROM openjdk:8-jdk-alpine

ARG NEXUS_URL=http://192.168.33.10:8081/#browse/browse:maven-snapshots
ARG JAR_PATH=esprit/spring/khaddem/4.0-SNAPSHOT/4.0-20231116.024109-1/khaddem-4.0-20231116.024109-1.jar
ARG FULL_PATH=http://192.168.33.10:8081/repository/maven-snapshots/tn/esprit/spring/khaddem/4.0-SNAPSHOT/4.0-20231116.024109-1/khaddem-4.0-20231116.024109-1.jar
RUN apk add --no-cache curl \
    && curl -o Project.jar -L "http://192.168.33.10:8081/repository/maven-snapshots/tn/esprit/spring/khaddem/4.0-SNAPSHOT/4.0-20231116.024109-1/khaddem-4.0-20231116.024109-1.jar" \
    && apk del curl

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "Project.jar"]