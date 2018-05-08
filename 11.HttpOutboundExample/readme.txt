This example show cases about the http outbound gateway,
basically any message to the input channel subribed by gateway 
hits the url mentioned in http outbound gateway,the request will be dispatched to the 
restcontroller which computes and return pojo "ServerMsg" as response entity, finally
payload received in the reply channel "ServerMsg", same as been configured in the 
http outbound gateway with expected-response-type.

Steps to execute:
fist compile and generate build including junit modules 

i) USING clean compile test-compile ---> maven
ii) run application.java as spring boot app.
iii) finally execute applicationTests.java as  junit 

  
<int-http:outbound-gateway id="outboundGateway" request-channel="requestChannel"
                               reply-channel="replyChannel" 
                               http-method="GET" 
                               url="http://localhost:8080/serverTime" 
                               expected-response-type="com.example.demo.ServerMsg"/>
                               
	@RequestMapping(value="/serverTime")
	public ResponseEntity<?> getServerTime() {
		DateTimeFormatter datetimeformat= DateTimeFormatter.ofPattern("dd-MM-YYYY HH:MM:SS");
		LocalDateTime currentTime = LocalDateTime.now();
		
		String servermsgTime = datetimeformat.format(currentTime);
	    ServerMsg servermsg = new ServerMsg();
	    servermsg.setCurrentTime(servermsgTime);
	    
	    return new ResponseEntity<ServerMsg>(servermsg,HttpStatus.OK);
	}


Junit Test invocation:


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

