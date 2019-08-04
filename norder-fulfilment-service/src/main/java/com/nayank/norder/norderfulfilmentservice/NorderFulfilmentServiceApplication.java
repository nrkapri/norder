package com.nayank.norder.norderfulfilmentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NorderFulfilmentServiceApplication {

	RestTemplate restTemplate = new RestTemplate(); 
	private static final Logger LOGGER =LoggerFactory.getLogger(NorderFulfilmentServiceApplication.class);

	@Autowired
	private RabbitTemplate template;

	static final String topicExchangeName = "norder-createorder-exchange";

	static final String queueName = "norder";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("order.fulfil.#");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NorderFulfilmentServiceApplication.class, args);
	}

//	@KafkaListener(topics = "create-order")
	@RabbitListener(queues = "norder")
	public void receive(String payload) {
		LOGGER.info("received payload='{}'", payload);
		
		Orders order = restTemplate
				.getForObject("http://localhost:9090/"+"norder-order-service"+ "/"+"fulfil"+payload, Orders.class);
		
		LOGGER.info("received order='{}'", order);
		
	}
}
