package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations="/spring-integ-context.xml")
public class ApplicationTests {

	@Autowired
	Greetings greetings;
	
	@Test
	public void testSendAndReceiveMsg() {
		greetings.send(" this is the gateway with service activator example!!!");

		System.out.println(greetings.receive());
	}
	
}


/****************** OUTPUT ************************


hello Gopala Krishnan this is the gateway with service activator example!!!



*************************************************/