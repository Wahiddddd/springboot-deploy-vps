#!/bin/bash

# Script untuk menjalankan aplikasi dalam mode development

echo "🚀 Starting application..."
echo ""

# Run Spring Boot with dev profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
