FROM openjdk:8-jdk-alpine
COPY target/service-0.0.1-SNAPSHOT.jar hospital-service.jar
ENTRYPOINT ["java","-jar","/hospital-service.jar"]