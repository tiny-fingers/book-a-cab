FROM openjdk:17

LABEL maintainer="tinyfingers"

WORKDIR /app

ENV SERVER_PORT=${SERVER_PORT}

COPY target/distance-0.0.1-SNAPSHOT.jar /app/distance-0.0.1-SNAPSHOT.jar

EXPOSE ${SERVER_PORT}

CMD ["java", "-jar", "distance-0.0.1-SNAPSHOT.jar"]
