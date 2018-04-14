package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControlBusExampleApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(ControlBusExampleApplicationTests.class);
    ConfigurableApplicationContext context;
    
    @Before
    public void onStart() {
    	context = new ClassPathXmlApplicationContext("spring-integ-context.xml");
    }

    @After
    public void onShut() {
    	context.close();
    }
    
	@Test
	public void testControlbusAdapter() {
		
			//control bus channel
			MessageChannel controlChannel   =  context.getBean("controlChannel",MessageChannel.class);
	
			//pollable message channel
			PollableChannel msgChannelAdapterOutput   = context.getBean("msgChannelAdapterOutput", PollableChannel.class);

			Message receivedMsg = (Message) msgChannelAdapterOutput.receive(1000);
	        assertNull(receivedMsg);
	        logger.info("message received on channel before adapter started:" + receivedMsg);
	        
	        controlChannel.send(new GenericMessage("@inboundAdapter.start()"));
	        receivedMsg = (Message) msgChannelAdapterOutput.receive(1000);
	        assertEquals(receivedMsg.getPayload(),"this is the test message");
	        logger.info("Message received on channel adapter started: " + receivedMsg);

	        controlChannel.send(new GenericMessage("@inboundAdapter.stop()"));
	        receivedMsg = (Message) msgChannelAdapterOutput.receive(1000);
	        assertNull(receivedMsg);
	        logger.info("Message received on channel adapter stopped: " + receivedMsg);

	}

	@Test
	public void testControlBusMBean() {
		
			//control bus channel
			MessageChannel controlChannel   =  context.getBean("controlChannel",MessageChannel.class);

			ManagedCounterBean mangedCounterBean = context.getBean("managedCounterBean", ManagedCounterBean.class);
			assertEquals(mangedCounterBean.getCount(), 0);

			controlChannel.send(new GenericMessage("@managedCounterBean.increment()"));
			assertEquals(mangedCounterBean.getCount(), 1);
			logger.info("Value of message counter after sending message to control bus " + mangedCounterBean.getCount());
	}
	
	
	}
