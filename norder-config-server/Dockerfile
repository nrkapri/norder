# Start with a base image containing Java runtime
FROM java:8

# Make port 8080 available to the world outside this container
EXPOSE 8888

ADD target/norder-config-server.jar norder-config-server.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","norder-config-server.jar"]