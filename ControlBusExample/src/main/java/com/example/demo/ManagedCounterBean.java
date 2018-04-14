package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource
public class ManagedCounterBean {

	private final AtomicInteger count = new AtomicInteger();

	@ManagedAttribute
	public int getCount() {
		return this.count.get();
	}

	@ManagedAttribute
	public void setCount(int count) {
		this.count.set(count);
	}
	
	@ManagedOperation
	public void increment() {
		this.count.incrementAndGet();
	}
}
