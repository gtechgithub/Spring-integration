This is the simple example of service Activator which get message from the input channel
and once it receives message from it,
service method defined in the service activator gets triggered, finally processed message 
will be written into the output channel mentioned in the service activator.

 <int:channel id="inBoundChannel"/>
 <int:channel id="outBoundChannel">
 	<int:queue/>
 </int:channel>

<int:service-activator ref="helloService" input-channel="inBoundChannel" 
             method="greet"  output-channel="outBoundChannel"/>

1) junit test includes the spring-integ-context.xml using ContextConfiguration(location="..")
   the bean definition can be loaded here or autowired inside the junit test class

2) Autowire inputchannel and output channel using the Qualifier

  @Autowired
	@Qualifier("inBoundChannel")
	MessageChannel inBoundChannel;
	
	@Autowired
  @Qualifier("outBoundChannel")
	PollableChannel outBoundChannel;
	
3) Send message to the input channel  

Message<?> msg = new GenericMessage(" this is Service activator example");
		inBoundChannel.send(msg);
    
4) sending message to the input-channel (inBoundChannel) will trigger method defined in the service activitator

5) processed message by the service activitator method "greet" writes into öutput channel "outBoundChannel"

6) print message from the output channel  "outBoundChannel"

		System.out.println(outBoundChannel.receive().getPayload().toString());



/*********** OUTPUT ******************************

Hello Gopala Krishnan this is Service activator example

****************************************************/
