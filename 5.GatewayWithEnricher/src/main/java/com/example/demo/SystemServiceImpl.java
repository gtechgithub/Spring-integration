package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class SystemServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemServiceImpl.class);

	/*
	public User findUser(User user) {

		LOGGER.info(String.format("Calling method 'findUser' with parameter %s", user));

		user.setUserName("test1");
		final User fullUser = new User(user.getUserName(), "confidential", user.getUserName() + "@testmail.com");
		return fullUser;
	}
	
	public User findByUserName(String userName) {

		LOGGER.info(String.format("Calling method 'findUserByUsername' with parameter: %s", userName));
		
		userName = "test2";
		return new User(userName, "secret", userName + "@testmail.org");
		
	}
	*/
	
	public User findUser(Message<?> message) {

		LOGGER.info("payload of findUser:"+ 	message.getPayload().toString());
		User user =  (User) message.getPayload();
		
		LOGGER.info(String.format("Calling method 'findUser' with parameter %s", user));

		
		/*
		 * 
		 * this itself will directly modify the payload
		 * 
		user.setUserName("test1");
		user.setPassword("confidential");
		user.setEmail(user.getUserName() + "@testmail.com");
		*/
		
		final User fullUser = new User("test122", "confidential", user.getUserName() + "@testmail.com");
		
		return fullUser;
	}
	
	public User findByUserName(Message<?> message) {


		LOGGER.info("payload of findByUserName:"+ 	message.getPayload().toString());
		String userName =  (String) message.getPayload();
		
		LOGGER.info(String.format("Calling method 'findUserByUsername' with parameter: %s", userName));
		
		userName = "test2";
		final User fullUser = new User(userName, "secret", userName + "@testmail.org");
		
		return fullUser;
	}

}
