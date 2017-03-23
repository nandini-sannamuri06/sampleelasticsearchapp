package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleElasticSearchAppApplication {

	public static void main(String[] args) {
		System.out.println("Application started");
		SpringApplication.run(SampleElasticSearchAppApplication.class, args);
	}
}
