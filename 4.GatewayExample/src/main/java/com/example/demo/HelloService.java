package com.example.demo;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class HelloService {

	private String message;
	
	public HelloService(String message) {
		this.message = message;
	}
	
	public String greet(Message<?> msg) {
		
		if ( msg.getPayload() != null ) {
			return "hello " + message + msg.getPayload().toString(); 
		}
		return null;
	}
	
}
