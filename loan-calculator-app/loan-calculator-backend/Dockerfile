FROM eclipse-temurin:17
RUN mkdir /opt/app

ARG JAR_FILE=build/*.jar
COPY ${JAR_FILE} /opt/app

CMD ["java", "-jar", "/opt/app/japp.jar"]