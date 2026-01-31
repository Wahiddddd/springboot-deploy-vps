# Tahap 1: Build menggunakan Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Salin pom.xml dulu untuk download library (biar lebih cepat)
COPY pom.xml .
RUN mvn dependency:go-offline
# Baru salin kodingan sisanya
COPY . .
RUN mvn clean package -DskipTests

# Tahap 2: Jalankan menggunakan Amazon Corretto
FROM amazoncorretto:17-al2-jdk
WORKDIR /app
# Gunakan wildcard * untuk memastikan file .jar terpilih
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9007
ENTRYPOINT ["java","-jar","app.jar"]