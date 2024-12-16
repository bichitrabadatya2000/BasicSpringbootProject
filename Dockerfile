FROM openjdk:11

COPY target/kafka-0.0.1-SNAPSHOT.jar  /usr/app/

WORKDIR /usr/app/

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "kafka-0.0.1-SNAPSHOT.jar" ]