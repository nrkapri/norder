package com.nayank.norder.norderfulfilmentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NorderFulfilmentServiceApplication {

	RestTemplate restTemplate = new RestTemplate(); 
	private static final Logger LOGGER =LoggerFactory.getLogger(NorderFulfilmentServiceApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(NorderFulfilmentServiceApplication.class, args);
	}

	@KafkaListener(topics = "create-order")
	public void receive(String payload) {
		LOGGER.info("received payload='{}'", payload);
		
		Orders order = restTemplate
				.getForObject("http://localhost:9090/"+"norder-order-service"+ "/"+"fulfil"+payload, Orders.class);
		
		LOGGER.info("received order='{}'", order);
		
	}
}
