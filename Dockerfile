FROM adoptopenjdk/openjdk11:ubuntu-jre-nightly
EXPOSE 8089
ADD target/kaddem.jar kaddem.jar
ENTRYPOINT ["java","-jar","/kaddem.jar"]