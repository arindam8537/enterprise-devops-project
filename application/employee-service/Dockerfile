## FROM eclipse-temurin:17-jdk  -- commented and used lighter version of jdk for docker image size optimization as jre 
FROM eclipse-temurin:17-jre

WORKDIR /app

## COPY target/employee-service-0.0.1-SNAPSHOT.jar app.jar  -- avoid hardcoding the version of jar file in dockerfile
COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]