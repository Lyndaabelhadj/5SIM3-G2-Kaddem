FROM openjdk:11-jre-slim
EXPOSE 8089
ADD target/khaddem.jar khaddem.jar
ENTRYPOINT ["java","-jar","/kaddem.jar"]