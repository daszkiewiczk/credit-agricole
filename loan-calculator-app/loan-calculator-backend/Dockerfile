FROM gradle:7.6.0-jdk17 as build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/build/libs/loan-calculator-backend-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "/app/loan-calculator-backend-0.0.1-SNAPSHOT.jar"]