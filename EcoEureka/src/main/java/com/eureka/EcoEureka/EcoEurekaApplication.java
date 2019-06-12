package com.eureka.EcoEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EcoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoEurekaApplication.class, args);
	}

}
