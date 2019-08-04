# Start with a base image containing Java runtime
FROM java:8

# Make port 8080 available to the world outside this container
EXPOSE 8761

ADD target/norder-eureka-server.jar norder-eureka-server.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","norder-eureka-server.jar"]