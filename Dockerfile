FROM eclipse-temurin:18-jdk
ARG JAR_FILE=target/*.jar
COPY ./build/libs/smartfintools-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]