package com.example.demo;

public class ServerMsg {

	private final String welcomeMsg  = "Welcome To Spring Integration Http Outbound Example!";
	private String currentTime;
	
	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() {
		return welcomeMsg + " \nTime:" + currentTime;
	}
}
