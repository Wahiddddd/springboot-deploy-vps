# Gunakan maven untuk build
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Gunakan OpenJDK yang lebih stabil namanya
FROM openjdk:17-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 9007
ENTRYPOINT ["java","-jar","/app.jar"]