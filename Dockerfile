FROM openjdk:11-jre-slim
EXPOSE 8089
ADD target/kaddem.jar kaddem.jar
ENTRYPOINT ["java","-jar","/kaddem.jar"]