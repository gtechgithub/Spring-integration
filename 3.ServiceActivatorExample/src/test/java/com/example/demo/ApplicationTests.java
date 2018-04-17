package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations="/spring-integ-context.xml")
public class ApplicationTests {

	@Autowired
	@Qualifier("inBoundChannel")
	MessageChannel inBoundChannel;
	
	@Autowired
    @Qualifier("outBoundChannel")
	PollableChannel outBoundChannel;
	
	@Test
	public void testSendAndReceive() {
	
		//inBoundChannel = context.getBean("inBoundChannel",MessageChannel.class);
		//outBoundChannel = context.getBean("outBoundChannel",PollableChannel.class);
		
		Message<?> msg = new GenericMessage(" this is Service activator example");
		inBoundChannel.send(msg);
		
		System.out.println(outBoundChannel.receive().getPayload().toString());
		
	}

	/**
	private AbstractApplicationContext context;
	
	
	@Before
	public void load() {
		context = new ClassPathXmlApplicationContext("spring-integ-context.xml");	
	}

	@After
	public void shut() {
		context.registerShutdownHook();
	}
	*/

}


/*********** OUTPUT ******************************

Hello Gopala Krishnan this is Service activator example

****************************************************/