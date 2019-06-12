package com.ecotage.Payment;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.ecotage.Payment.JpaConfiguration;
import com.ecotage.exception.RestTemplateResponseErrorHandler;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages= {"com.ecotage"})
@EnableEurekaClient
@EnableFeignClients
@Import(JpaConfiguration.class)
@EnableSwagger2
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.errorHandler(new RestTemplateResponseErrorHandler()).build();
	}

	@Bean(name = "template2")
	public RestTemplate restTemplate2(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofSeconds(3)).setReadTimeout(Duration.ofSeconds(4)).build();
	}

}
