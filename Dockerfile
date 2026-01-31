# Tahap 1: Build menggunakan Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Tahap 2: Jalankan menggunakan Amazon Corretto (Sangat Stabil)
FROM amazoncorretto:17-al2-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9007
ENTRYPOINT ["java","-jar","app.jar"]