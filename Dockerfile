# Dockerfile — versão que funciona  - apanhando por calsa do alpine
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Instala ferramentas básicas que o mvnw precisa para baixar o Maven
RUN apk add --no-cache \
    curl \
    tar \
    dos2unix

# Copia o wrapper
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Corrige line endings do Windows e dá permissão - isso aqui estava me matando
RUN dos2unix mvnw && chmod +x mvnw

# Força o download do Maven com curl (o mvnw às vezes falha no Alpine sem isso)
# Ou simplesmente roda — agora vai funcionar porque tem curl
RUN ./mvnw -version || true  # força download do Maven na primeira execução
RUN ./mvnw clean package -DskipTests

# Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=20s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
