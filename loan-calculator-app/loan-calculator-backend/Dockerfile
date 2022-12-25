FROM eclipse-temurin:17

ENV GRADLE_HOME /usr/local/gradle
ENV GRADLE_VERSION 6.7.1

# Install Gradle
RUN wget --no-verbose --output-document=gradle.zip "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
    && unzip gradle.zip -d /usr/local \
    && rm gradle.zip \
    && ln --symbolic "${GRADLE_HOME}/bin/gradle" /usr/local/bin/gradle \
    && gradle --version

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the project
RUN gradle build

# Set the default command to run the Spring Boot application
CMD ["java", "-jar", "build/libs/loan-calculator-backend-0.0.1-SNAPSHOT.jar"]
