
FROM maven:3.6.0-jdk-11-slim AS build
RUN mkdir -p /usr/src/mqtt
WORKDIR /usr/src/mqtt

#COPY ./src /usr/src/app/src
COPY ./pom.xml /usr/src/mqtt
RUN mvn dependency:go-offline

COPY ./src /usr/src/mqtt/src
RUN mvn -f /usr/src/mqtt/pom.xml clean package -DskipTests

FROM adoptopenjdk/openjdk11:jre11u-alpine-nightly

COPY --from=build /usr/src/mqtt/target/mqttnotification-0.0.1-SNAPSHOT.jar /usr/src/mqtt/app.jar

EXPOSE 9091

ENTRYPOINT ["java", "-jar", "/usr/src/mqtt/app.jar"]
