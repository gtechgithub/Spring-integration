package com.example.demo;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndEntry;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("/spring-integ-context.xml")
public class ApplicationTests {

	@Autowired
	PollableChannel feedChannel;

	@Test
	public void feedReader() {
		//ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-integ-context.xml");
		
		Message<?> msg;

		 try {
	           //PollableChannel feedChannel = context.getBean("feedChannel", PollableChannel.class);
	            for (int i = 0; i < 10; i++) {
	                Message message = (Message) feedChannel.receive(10000);
	                if (message != null){
	                    SyndEntryImpl entry = (SyndEntryImpl) message.getPayload();
	                    System.out.println(entry.getPublishedDate() + " - " + entry.getTitle());
	                }
	                else {
	                    break;
	                }
	            }            
	        } finally {
	            //context.close();
	        }
	}
	
}


/************** OUTPUT *********************************





o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:log.adapter} as a subscriber to the 'log' channel
o.s.integration.channel.DirectChannel    : Channel 'application:-1.log' has 1 subscriber(s).
o.s.i.endpoint.EventDrivenConsumer       : started log.adapter
o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
o.s.i.channel.PublishSubscribeChannel    : Channel 'application:-1.errorChannel' has 1 subscriber(s).
o.s.i.endpoint.EventDrivenConsumer       : started _org.springframework.integration.errorLogger
o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 1073741823
o.s.i.e.SourcePollingChannelAdapter      : started feedInBoundChannelAdapter

com.example.demo.ApplicationTests        : Started ApplicationTests in 1.21 seconds (JVM running for 2.024)

Mon Apr 09 23:59:45 IST 2018 - Danish silo demolition goes wrong
Tue Apr 10 04:32:29 IST 2018 - Aya Cissoko: Boxer and author on feminism and fighting back
Tue Apr 10 06:15:35 IST 2018 - How Trump could respond to the Syria 'chemical attack'
Tue Apr 10 21:29:48 IST 2018 - Girls jump from balcony to escape blaze
Tue Apr 10 21:41:37 IST 2018 - Israeli soldier shoots Palestinian on Gaza border
Tue Apr 10 23:18:42 IST 2018 - Driver slams into motorcyclist in suspected road rage scuffle
Wed Apr 11 01:59:22 IST 2018 - Trying to be a mother while behind bars
Wed Apr 11 04:33:17 IST 2018 - Being a male make-up artist in Senegal
Wed Apr 11 04:36:12 IST 2018 - The 'abducted grooms' forced to marry
Wed Apr 11 06:02:33 IST 2018 - Key moments from Zuckerberg hearing









**********************************************************/