<h1>norder </h1>

<h2>A demonstration of microservice architechture</h2>


This project is to demonstrate different aspects of a microservice architecture 

1. apigateway -- a zuul proxy server serving as a gateway
2. config -- a spring cloud config server fetching the configuration from  github
3. eureka server -- eureka server for service discovery 
4. zipkin server --- along with sleuth used for distributed tracing 
5. customer -- a spring data rest repository microservice maintaining customer infromation 
6. product -- a simple data rest repository microservice maintaining product infromation 
7. orders  -- a simple data rest repository for orders for maintianing orders information which uses  both customer and product microservice

<h2>Design of the microservice architecture</h2>

<pre><font color="#268BD2"><b>.</b></font>
|-- <font color="#268BD2"><b>norder</b></font>
|-- <font color="#268BD2"><b>norder-api-gateway</b></font> -- a zuul proxy server serving as a gateway
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-config-server</b></font> -- a spring cloud config server fetching the configuration from  github
|   |-- <font color="#268BD2"><b>config</b></font>
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-customer-service</b></font> -- a spring data rest repository microservice maintaining customer infromation
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-eureka-server</b></font> -- eureka server for service discovery 
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-fulfilment-service</b></font>  -- responsible for fulfilling an order 
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-order-service</b></font>  -- a simple data rest repository for orders for maintianing orders information which uses  both customer and product microservice
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-product-service</b></font> -- a simple data rest repository microservice maintaining product infromation 
|   |-- <font color="#268BD2"><b>bin</b></font>
|   |   `-- <font color="#268BD2"><b>src</b></font>
|   |       |-- <font color="#268BD2"><b>main</b></font>
|   |       `-- <font color="#268BD2"><b>test</b></font>
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-reservation</b></font>
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-reservation-client</b></font>
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-reservation-service</b></font>
|   `-- <font color="#268BD2"><b>src</b></font>
|       |-- <font color="#268BD2"><b>main</b></font>
|       |   |-- <font color="#268BD2"><b>java</b></font>
|       |   `-- <font color="#268BD2"><b>resources</b></font>
|       `-- <font color="#268BD2"><b>test</b></font>
|           `-- <font color="#268BD2"><b>java</b></font>
|-- <font color="#268BD2"><b>norder-zipkin-server</b></font> --- along with sleuth used for distributed tracing 
|   `-- <font color="#268BD2"><b>src</b></font>
|       `-- <font color="#268BD2"><b>main</b></font>
|           |-- <font color="#268BD2"><b>java</b></font>
|           `-- <font color="#268BD2"><b>resources</b></font>
`-- <font color="#268BD2"><b>reservation-client-1</b></font>
    `-- <font color="#268BD2"><b>src</b></font>
        |-- <font color="#268BD2"><b>main</b></font>
        |   |-- <font color="#268BD2"><b>java</b></font>
        |   `-- <font color="#268BD2"><b>resources</b></font>
        `-- <font color="#268BD2"><b>test</b></font>
            `-- <font color="#268BD2"><b>java</b></font>
</pre>
