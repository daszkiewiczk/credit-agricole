FROM gradle:6.7.1-jdk17 as build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM adoptopenjdk/openjdk17:alpine
COPY --from=build /app/build/libs/loan-calculator-backend-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "/app/loan-calculator-backend-0.0.1-SNAPSHOT.jar"]