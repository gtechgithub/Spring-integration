package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:http-outbound-gateway.xml")
@SpringBootTest
public class ApplicationTests {

	@Resource(name="replyChannel")
	PollableChannel replyChannel;
	
	@Resource(name="requestChannel")
	MessageChannel inputChannel;
	
	@Test
	public void contextLoads() {
		inputChannel.send(MessageBuilder.withPayload("").build());
		Message<ServerMsg> msg = (Message<ServerMsg>) replyChannel.receive();
		
		ServerMsg servermsg = msg.getPayload();
		
		System.out.println("message received:" + servermsg);
	}

}

/**************** OUTPUT *************************************


Initializing ExecutorService  'taskScheduler'
Starting beans in phase 0
Adding {http:outbound-gateway:outboundGateway} as a subscriber to the 'requestChannel' channel
Channel 'application.requestChannel' has 1 subscriber(s).
started outboundGateway
Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
Channel 'application.errorChannel' has 1 subscriber(s).
started _org.springframework.integration.errorLogger
Started ApplicationTests in 1.908 seconds (JVM running for 2.901)


message received:Welcome To Spring Integration Http Outbound Example! 
Time:08-05-2018 13:05:68


****************************************************************/
