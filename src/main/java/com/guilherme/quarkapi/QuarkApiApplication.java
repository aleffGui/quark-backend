package com.guilherme.quarkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuarkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuarkApiApplication.class, args);
	}

}
