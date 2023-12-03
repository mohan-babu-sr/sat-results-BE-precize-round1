# Fetching the latest version of Java
FROM openjdk:11
 
# Setting up the work directory
WORKDIR /app

# Copy the JAR file into our app
COPY target/spring-0.0.1-SNAPSHOT.jar /app/

# Display the contents of the /app directory
RUN ls -la /app

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "spring-0.0.1-SNAPSHOT.jar"]
