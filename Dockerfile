FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar executable.jar
ENTRYPOINT ["java","-jar","/executable.jar"]
EXPOSE 8080