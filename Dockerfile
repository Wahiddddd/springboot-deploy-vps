# Tahap 1: Build menggunakan Maven dengan JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

# Tahap 2: Jalankan menggunakan Amazon Corretto 21 (Sangat Stabil)
FROM amazoncorretto:21-al2-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9007
ENTRYPOINT ["java","-jar","app.jar"]