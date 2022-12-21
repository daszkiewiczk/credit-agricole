FROM eclipse-temurin:17
RUN mkdir /opt/app

ARG JAR_FILE=build/libs/loan-calculator-backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/app

CMD ["java", "-jar", "/opt/app/japp.jar"]
