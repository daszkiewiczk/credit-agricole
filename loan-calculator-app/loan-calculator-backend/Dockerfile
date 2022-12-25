FROM gradle:7.6.0 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM eclipse-temurin:17
RUN mkdir /opt/app

COPY --from=build /home/gradle/src/build/libs/loan-calculator-backend-0.0.1-SNAPSHOT.jar /opt/app

CMD ["java", "-jar", "/opt/app/loan-calculator-backend-0.0.1-SNAPSHOT.jar"]
