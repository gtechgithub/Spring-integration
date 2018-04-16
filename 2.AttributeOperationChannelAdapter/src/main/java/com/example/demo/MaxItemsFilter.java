package com.example.demo;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("maxItemsFilter")
public class MaxItemsFilter {

	private static final int MAX_THRESHOLD = 10;
	
	public Boolean checkThreshold(Message <?> orderId) {
		
		
		if (orderId.getPayload()!= null) {
			{
				int orderVal = (Integer) orderId.getPayload();
				
				System.out.println("orderVal:" + orderVal);
				
				//during 11th count it will return true
				if (orderVal > MAX_THRESHOLD) {
					return true;
				}
			}
		}
		return false;
	}
}
