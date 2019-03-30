package com.nayank.norder.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NorderEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorderEurekaServerApplication.class, args);
	}

}
