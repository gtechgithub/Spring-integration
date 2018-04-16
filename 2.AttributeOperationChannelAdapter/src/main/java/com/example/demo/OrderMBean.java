package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource
public class OrderMBean {
    private final AtomicInteger orders = new AtomicInteger();

    @ManagedAttribute
	public int getOrders() {
		return orders.get();
	}
	
    @ManagedOperation
	public void increment() {
		orders.getAndIncrement();
	}

    @ManagedOperation
    public void resetOrders() {
    	orders.set(0);
    }
}
