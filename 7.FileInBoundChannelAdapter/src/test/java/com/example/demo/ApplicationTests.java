package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(locations="/spring-integ-context.xml")
public class ApplicationTests {

	@Test
	public void contextLoads() {
	
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:fileCopyDemo-text.xml");
		FileCopyDemoCommon.displayDirectories(context);
	}

}


/****************** OUTPUT **************************************






o.s.s.c.ThreadPoolTaskScheduler          : Initializing ExecutorService  'taskScheduler'
o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
o.s.i.endpoint.EventDrivenConsumer       : Adding {transformer} as a subscriber to the 'fileIn' channel

o.s.integration.channel.DirectChannel    : Channel 'org.springframework.context.support.ClassPathXmlApplicationContext@65f00478.fileIn' has 1 subscriber(s).
o.s.i.endpoint.EventDrivenConsumer       : started org.springframework.integration.config.ConsumerEndpointFactoryBean#0

o.s.i.endpoint.EventDrivenConsumer       : Adding {service-activator} as a subscriber to the 'strings' channel
o.s.integration.channel.DirectChannel    : Channel 'org.springframework.context.support.ClassPathXmlApplicationContext@65f00478.strings' has 1 subscriber(s).
o.s.i.endpoint.EventDrivenConsumer       : started org.springframework.integration.config.ConsumerEndpointFactoryBean#1

o.s.i.endpoint.EventDrivenConsumer       : Adding {message-handler:fileOut.adapter} as a subscriber to the 'fileOut' channel
o.s.integration.channel.DirectChannel    : Channel 'org.springframework.context.support.ClassPathXmlApplicationContext@65f00478.fileOut' has 1 subscriber(s).

o.s.i.endpoint.EventDrivenConsumer       : started fileOut.adapter
o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
o.s.i.channel.PublishSubscribeChannel    : Channel 'org.springframework.context.support.ClassPathXmlApplicationContext@65f00478.errorChannel' has 1 subscriber(s).
o.s.i.endpoint.EventDrivenConsumer       : started _org.springframework.integration.errorLogger
o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 1073741823
o.s.i.e.SourcePollingChannelAdapter      : started fileIn.adapter

Input directory is: C:\Users\gopalpc\AppData\Local\Temp\spring-integration-samples\input
Output directory is: C:\Users\gopalpc\AppData\Local\Temp\spring-integration-samples\output

copying text:test file








*******************************************************************/