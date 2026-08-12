# Use official OpenJDK image
FROM openjdk:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy built JAR file into container
COPY target/Employee-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
