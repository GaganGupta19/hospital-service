FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-DDB_USER=root", "-DDB_PASSWORD=password", "-DDB_URL=jdbc:mysql://localhost:3307/hospital_service_development?profileSQL=true","-jar","app.jar"]