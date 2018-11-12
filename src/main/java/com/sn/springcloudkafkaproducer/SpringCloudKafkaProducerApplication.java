package com.sn.springcloudkafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringCloudKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudKafkaProducerApplication.class, args);
	}
}
