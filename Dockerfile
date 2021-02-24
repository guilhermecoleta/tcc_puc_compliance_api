FROM openjdk:12-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/\logistics-1.0.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=production","-jar","/app.jar"]
