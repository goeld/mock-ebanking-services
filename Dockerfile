FROM openjdk:14-ea-12-jdk-alpine3.10
ARG JAR_FILE=app/target/app-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=k8s"]