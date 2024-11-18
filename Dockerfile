FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY src /app/src
RUN apt-get install maven -y
RUN mvn clean install
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]







#  FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
#  COPY src /app/src
#  COPY pom.xml /app
#  WORKDIR /app
#  RUN mvn clean package -DskipTests
#
#  FROM eclipse-temurin:21-jre-alpine
#  RUN addgroup -S spring && adduser -S spring -G spring
#  USER spring:spring
#  COPY --from=build /app/target/*.jar /app/app.jar
#  EXPOSE 8080
#  ENTRYPOINT ["java","-jar","/app/app.jar"]