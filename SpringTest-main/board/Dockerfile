# 1️⃣ Build stage: Maven + JDK
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# 의존성 캐싱
COPY pom.xml .
RUN mvn dependency:go-offline

# 소스 복사 및 빌드
COPY . .
RUN mvn clean package -DskipTests

# 2️⃣ Run stage: 경량 JRE
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
