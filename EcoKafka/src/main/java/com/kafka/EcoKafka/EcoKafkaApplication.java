package com.kafka.EcoKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages= {"com.kafka"})

public class EcoKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoKafkaApplication.class, args);
	}

}
