
Controlbus in the spring integration component that accepts message on the channel which is similar 
to the service-activator, adapter or transfor but with one difference the payload of the message 
indicates invocable action or operation on bean.

The input channel in control bus is used for sending control messages to invoke operation on endpoints
or other managed resources

Inorder to enable control bus, add below
 <control-bus input-channel="testControlChannel"/>

"@" symbol is used to referene the bean, the operation to be invoked is specified after the bean name 

Message incrementCounterMsg = MessageBuilder.withPayload("@sampleCounter.increment()").build();
testControlChannel.send(incrementCounterMsg);

Managed Resource is annotation that indicates to register instances of a class with a JMX server 
using the annotation @ManagedResource

@ManagedAttribute is used for getter and setter methods
@ManagedOperation is used for non getter setter methods


in the below control message is sent to start the bean InboundAdapter 

Message controlMessage = MessageBuilder.withPayload("@inboundAdapter.start()").build(); testControlChannel.send(controlMessage);


<int:inbound-channel-adapter id="inboundAdapter" channel="msgChannelAdapterOutput"  expression="'Sample message'" auto-startup="false">
   <int:poller fixed-rate="1000"/>
</int:inbound-channel-adapter>


depencies needed:
		
    <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-integration</artifactId>
    </dependency>
    
