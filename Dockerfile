# Use a base image that includes Java
FROM eclipse-temurin:17

# Set the working directory
WORKDIR /app/booking-service

# Copy the JAR file into the container
COPY target/booking-service-1.jar booking-service.jar

# Command to run your application
ENTRYPOINT ["java", "-jar", "booking-service.jar"]
