# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project's JAR file to the container
COPY target/pos-products-0.0.1.jar /app/pos-products.jar

# Make port 8080 available to the world outside this container
EXPOSE 8091

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/pos-products.jar"]
