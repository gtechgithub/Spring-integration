package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("java.io.tmpdir:" + System.getProperty("java.io.tmpdir"));
		SpringApplication.run(Application.class, args);
	}
}
