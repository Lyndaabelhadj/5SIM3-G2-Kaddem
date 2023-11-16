FROM openjdk:11-jre-slim
EXPOSE 8089
COPY /var/lib/jenkins/workspace/houssem_DARRAGI_5SIM3/target/khaddem-4.0-SNAPSHOT.jar /khaddem-4.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/khaddem-4.0-SNAPSHOT.jar"]