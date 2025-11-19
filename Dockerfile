// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /workspace/app
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src src
RUN ./mvnw -DskipTests package -DskipITs

FROM eclipse-temurin:21-jre-jammy
ARG JAR_FILE=/workspace/app/target/*.jar
COPY --from=build /workspace/app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]