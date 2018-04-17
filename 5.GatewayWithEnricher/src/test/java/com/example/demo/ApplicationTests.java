package com.example.demo;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations="/spring-integ-context.xml")
public class ApplicationTests {

	private static Logger LOGGER;
	
	@Autowired
	UserService userService;
	
	@Before
	public void start() {
		 LOGGER = LoggerFactory.getLogger(ApplicationTests.class);
	}
	
	@Test
	public void testGatewayWithEnricher() {
		
			    User user1 = new User("gopala", "password1", "gopala@email.com");
			    

				final User fullUser1 = userService.findUser(user1);
				LOGGER.info("from findUser --> " + fullUser1);

				 User user2 = new User("krishnan", "password2", "krishnan@email.com");
				   
				final User fullUser2 = userService.findByUserName(user2);
				LOGGER.info("from findByUserName --> " + fullUser2);
	}

}

/**************************** OUTPUT *********************************


o.s.i.gateway.GatewayProxyFactoryBean    : started userService

o.s.i.t.ContentEnricher$Gateway          : started org.springframework.integration.transformer.ContentEnricher$Gateway@4275c20c
o.s.i.endpoint.EventDrivenConsumer       : Adding {enricher} as a subscriber to the 'findUserInputChannel' channel
o.s.integration.channel.DirectChannel    : Channel 'application.findUserInputChannel' has 1 subscriber(s).

o.s.i.endpoint.EventDrivenConsumer       : started org.springframework.integration.config.ConsumerEndpointFactoryBean#0
o.s.i.t.ContentEnricher$Gateway          : started org.springframework.integration.transformer.ContentEnricher$Gateway@7c56e013
o.s.i.endpoint.EventDrivenConsumer       : Adding {enricher} as a subscriber to the 'findUserbyUserNameInputChannel' channel
o.s.integration.channel.DirectChannel    : Channel 'application.findUserbyUserNameInputChannel' has 1 subscriber(s).

o.s.i.endpoint.EventDrivenConsumer       : started org.springframework.integration.config.ConsumerEndpointFactoryBean#1
o.s.i.endpoint.EventDrivenConsumer       : Adding {service-activator} as a subscriber to the 'findUserServiceChannel' channel
o.s.integration.channel.DirectChannel    : Channel 'application.findUserServiceChannel' has 1 subscriber(s).

o.s.i.endpoint.EventDrivenConsumer       : started org.springframework.integration.config.ConsumerEndpointFactoryBean#2
o.s.i.endpoint.EventDrivenConsumer       : Adding {service-activator} as a subscriber to the 'findUserByUserNameServiceChannel' channel
o.s.integration.channel.DirectChannel    : Channel 'application.findUserByUserNameServiceChannel' has 1 subscriber(s).

o.s.i.endpoint.EventDrivenConsumer       : started org.springframework.integration.config.ConsumerEndpointFactoryBean#3
o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
o.s.i.channel.PublishSubscribeChannel    : Channel 'application.errorChannel' has 1 subscriber(s).
o.s.i.endpoint.EventDrivenConsumer       : started _org.springframework.integration.errorLogger

com.example.demo.ApplicationTests        : Started ApplicationTests in 1.432 seconds (JVM running for 2.617)
--------------------------------------------------------------------------------------------------------------------

com.example.demo.SystemServiceImpl       : payload of findUser:username:gopala password:password1 email:gopala@email.com
com.example.demo.SystemServiceImpl       : Calling method 'findUser' with parameter username:gopala password:password1 email:gopala@email.com
com.example.demo.ApplicationTests        : from findUser --> username:gopala password:confidential email:gopala@testmail.com

com.example.demo.SystemServiceImpl       : payload of findByUserName:krishnan
com.example.demo.SystemServiceImpl       : Calling method 'findUserByUsername' with parameter: krishnan
com.example.demo.ApplicationTests        : from findByUserName --> username:krishnan password:secret email:test2@testmail.org



***********************************************************************/