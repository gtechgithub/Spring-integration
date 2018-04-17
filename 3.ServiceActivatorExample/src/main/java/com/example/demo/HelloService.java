package com.example.demo;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HelloService {
	
	private String message;
	
	public HelloService() {
		message = null;
	}

	public HelloService(String message) {
		this.message = message;
	}
	
	public String greet(Message<?> msgParam) {
		return "Hello " + message + msgParam.getPayload().toString();
	}
}
