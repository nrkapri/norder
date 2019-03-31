package com.nayank.norder.reservationclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin2.server.internal.EnableZipkinServer;




@SpringBootApplication
@EnableZipkinServer
public class NorderZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorderZipkinServerApplication.class, args);
	}

}
