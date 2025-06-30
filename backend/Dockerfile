# 1단계: React 프론트엔드 빌드
FROM node:18 AS frontend-builder

WORKDIR /app
COPY frontend/ ./frontend

WORKDIR /app/frontend
RUN rm -rf node_modules package-lock.json && \
    npm install && \
    npm run build

# 2단계: Spring Boot 빌드
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

# React 빌드 결과 복사
COPY --from=frontend-builder /app/frontend/build ./src/main/resources/static

RUN mvn clean package -DskipTests

# 3단계: 실행 이미지
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/monolithicK8s-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

