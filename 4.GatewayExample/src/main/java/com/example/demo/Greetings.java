package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public interface Greetings {
	public void send(String message);
	public String receive();
}


