package com.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecotage.vo.User;

@Service
public class KafkaConsumer {
	
	 	@KafkaListener(topics = "Kafka_Example", groupId = "group_id")
	    public void consume(String message) {
	        System.out.println("Consumed message: " + message);
	    }


	    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
	            containerFactory = "userKafkaListenerFactory")
	    public void consumeJson(User user) {
	        System.out.println("Consumed JSON Message: " + user.toString());
	    }

	    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json1",
	        containerFactory = "userKafkaListenerFactory")
	    public void consumeJson2(User user) {
	        System.out.println("Consumed JSON Message: " + user.toString());
	    }

}
