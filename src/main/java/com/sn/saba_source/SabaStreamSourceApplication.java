package com.sn.saba_source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SabaStreamSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabaStreamSourceApplication.class, args);
	}
}
