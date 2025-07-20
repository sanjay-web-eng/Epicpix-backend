# Use official OpenJDK base image
FROM maven:3.8.5-openjdk-17 AS build

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/epicpix-0.0.1-SNAPSHOT.jar epicpix.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "epicpix.jar"]
