This is a simple example of using Gateway spring integration, basically gateway does Facade which means
it hides business implementation to the client, however does perform required operation upon calling 
the exposed methods, secondly Gateway must be defined with Interface class which has no implementation.

Eg:

1) Greetings Inteface is defined in spring integration gateway using service-interface tag.  

public interface Greetings {
	public void send(String message);
	public String receive();
}

<int:channel id="inputChannel"/>
<int:channel id="outputChannel">
	<int:queue capacity="10"/>
</int:channel>

<int:gateway default-request-channel="inputChannel" 
             default-reply-channel="outputChannel"  
             service-interface="com.example.demo.Greetings" />
             

2) once gateway is defined, interface Greetings is @autowired in the Junit and called as follows

	@Autowired
	Greetings greetings;
	
	@Test
	public void testSendAndReceiveMsg() {
		greetings.send(" this is the gateway with service activator example!!!");

		System.out.println(greetings.receive());
	}
	
3)    greetings.send interface method will send message to the gateway input channel "inputChannel" and its notification message
      is consumed by the service activator and triggered method greet from HelloService.class to write notification message 
      in the output channel "outputChannel", finally interface method greetings.receive() can be used to get messages
      from the gateway output channel "outputChannel". 
      
      <int:service-activator input-channel="inputChannel" 
                       output-channel="outputChannel" 
                       ref="helloService" method="greet"/>            
             




