<h1>norder </h1>

<h2>A demonstration of microservice architechture</h2>


This project is to demonstrate different aspects of a microservice architecture 

1. apigetway -- a zuul proxy server serving as a getway
2. config -- a spring cloud config server fetching the configuration from  github
3. eureka server -- service discovery 
4. zipkin server --- anlong with sleuth used for distributed tracing 
5. customer -- a spring data rest repository microservice maintaining customer infromation 
6. product -- a simple data rest repository microservice maintaining product infromation 
7. orders  -- a simple data rest repository for orders for maintianing orders information which links to both customer and product microservice
