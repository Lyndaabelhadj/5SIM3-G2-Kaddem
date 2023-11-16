FROM openjdk:11-jre-slim
EXPOSE 8089
COPY khaddem-4.0-SNAPSHOT.jar khaddem-4.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/khaddem-4.0-SNAPSHOT.jar"]
