FROM openjdk:17

LABEL maintainer="tinyfingers"

WORKDIR /app

ENV SERVER_PORT=${SERVER_PORT}

COPY target/cab-price-calculator-0.0.1-SNAPSHOT.jar /app/cab-price-calculator-0.0.1-SNAPSHOT.jar

EXPOSE ${SERVER_PORT}

CMD ["java", "-jar", "cab-price-calculator-0.0.1-SNAPSHOT.jar"]
