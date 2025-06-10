# Usar imagen oficial de Java y Maven para build
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar archivos y construir la app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Imagen para correr la app
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
