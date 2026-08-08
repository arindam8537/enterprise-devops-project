## FROM eclipse-temurin:17-jdk  -- commented and used lighter version of jdk for docker image size optimization as jre 
FROM eclipse-temurin:17-jre

WORKDIR /app

# Create non-root user and application log directory
RUN useradd --system --create-home appuser && \
    mkdir -p /app/logs && \
    chown -R appuser:appuser /app

## added user creation to avoid running the application as root user in docker container for security reasons -- Container Hardening
## RUN useradd --system --create-home appuser

## COPY target/employee-service-0.0.1-SNAPSHOT.jar app.jar  -- avoid hardcoding the version of jar file in dockerfile
## COPY target/*.jar app.jar

COPY --chown=appuser:appuser target/*.jar app.jar

USER appuser

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]

