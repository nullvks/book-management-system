# ==============================================================================
# STAGE 1: Build the application
# We use a Maven image based on Amazon Corretto 21 to compile the code
# ==============================================================================
FROM maven:3.9.9-amazoncorretto-21 AS build

# Set a default directory inside the container to work from.
WORKDIR /app

# 1. Copy only the pom.xml first (Copy the special Maven files that help us download dependencies.)
COPY pom.xml .

# 2. Download dependencies.
# Doing this before copying source code allows Podman to cache the dependencies.
# If you change your code but not pom.xml, it won't re-download jars.
RUN mvn dependency:go-offline -B

# 3. Copy the actual source code (Copy our actual project files (code, resources, etc.) into the container.)
COPY src ./src

# 4. Build the application (skipping tests to speed up the container build)
RUN mvn clean package -DskipTests

# ==============================================================================
# STAGE 2: Run the application
# We use a slim Amazon Corretto 21 image just for running the jar
# ==============================================================================
FROM amazoncorretto:21

WORKDIR /app

# Copy the built jar from the 'build' stage above
# Note: The name matches your artifactId and version in pom.xml
COPY --from=build /app/target/bookmanagementsystem-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Command to run the app (When the container starts)
ENTRYPOINT ["java", "-jar", "app.jar"]