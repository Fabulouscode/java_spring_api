# Use a lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy your built jar file into the container
COPY target/spring-crud-app-0.0.1-SNAPSHOT.jar app.jar

# Expose port (same as your app port)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
