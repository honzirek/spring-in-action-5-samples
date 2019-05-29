FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_FILE
ADD keystore.p12 keystore.p12
COPY ${JAR_FILE} app.jar
USER root
COPY localhost.pem $JAVA_HOME/jre/lib/security
RUN \
    cd $JAVA_HOME/jre/lib/security \
    && keytool -keystore cacerts -storepass changeit -noprompt \
    -trustcacerts -importcert -alias bootifulsecurity -file localhost.pem
ENTRYPOINT ["java",\
            "-Djava.security.egd=file:/dev/./urandom",\
            "-jar",\
            "/app.jar"]
