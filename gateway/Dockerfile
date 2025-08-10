FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /build
COPY auth-service auth-service
COPY gateway gateway
COPY user-service user-service
COPY wallet-service wallet-service
COPY payment-gateway payment-gateway
COPY merchant-service merchant-service
COPY pom.xml .
RUN mvn -pl gateway -am -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/gateway/target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app/app.jar"]
