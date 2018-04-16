package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    private AbstractXmlApplicationContext context = null;

    @Before
    public  void startUp() {
    	context = new ClassPathXmlApplicationContext("spring-integ-context.xml");
    }
    
    @After
    public  void shutDown() {
    	context.registerShutdownHook();
    }

	@Test
	public void testJMXNotification() throws Exception {
		OrderMBean orderMBean = context.getBean("orderMBean", OrderMBean.class);
		QueueChannel  processedOrder = context.getBean("processedOrders", QueueChannel.class);

		System.out.println("Initial order value:"+ orderMBean.getOrders());
		Message <?> processedMsg = processedOrder.receive();
		//assertNull(processedMsg);
		
		for (int i=1; i <= 22 ; i++) {
			orderMBean.increment();

            Thread.sleep(5000);

			processedMsg = processedOrder.receive();
            assertNotNull(processedMsg);
            System.out.println(processedMsg.getPayload()); 

		}
	}

}

/*********** OUTPUT ********************

Initial order value:0
orderVal:0
orderVal:1
Order id:1 is being processed
orderVal:2
Order id:2 is being processed
orderVal:3
Order id:3 is being processed
orderVal:4
Order id:4 is being processed
orderVal:5
Order id:5 is being processed
orderVal:6
Order id:6 is being processed
orderVal:7
Order id:7 is being processed
orderVal:8
Order id:8 is being processed
orderVal:9
Order id:9 is being processed
orderVal:10
Order id:10 is being processed

orderVal:11
Order id:11 is being processed

orderVal:1
Order id:1 is being processed
orderVal:2
Order id:2 is being processed
orderVal:3
Order id:3 is being processed
orderVal:4
Order id:4 is being processed
orderVal:5
Order id:5 is being processed
orderVal:6
Order id:6 is being processed
orderVal:7
Order id:7 is being processed
orderVal:8
Order id:8 is being processed
orderVal:9
Order id:9 is being processed
orderVal:10
Order id:10 is being processed
orderVal:11
Order id:11 is being processed


***************************************************/































