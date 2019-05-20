FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh wait-for-it.sh
CMD ["java",\
            "-Djava.security.egd=file:/dev/./urandom",\
            "-jar",\
            "/app.jar"]
