FROM openjdk:17-alpine as build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw verify --fail-never

COPY src src
RUN ./mvnw package

FROM openjdk:17-alpine as run
WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar

ENTRYPOINT ["java","-jar","app.jar"]
