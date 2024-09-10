FROM maven:3.8.4-eclipse-temurin-17-alpine AS build
COPY ../src /app/src
COPY ../pom.xml /app
WORKDIR /app
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17.0.8.1_1-jdk-jammy
COPY --from=build /app/target/pubonline-api-0.0.1.jar /app/target/pubonline-api-0.0.1.jar
WORKDIR /app/target
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pubonline-api-0.0.1.jar"]