# Start with a base image containing Java runtime
FROM java:8

# Make port 8080 available to the world outside this container
EXPOSE 9090

ADD target/norder-api-gateway.jar norder-api-gateway.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","norder-api-gateway.jar"]