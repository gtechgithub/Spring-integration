package com.example.demo;

import org.springframework.messaging.Message;

public class CommonServiceActivator {

	public String attributePolled(Message <?> msg) {
		
		String processMsg = "Order id:" + msg.getPayload().toString() 
				              + " is being processed";
		return processMsg;
	}
}
