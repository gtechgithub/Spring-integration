This example portraits about JMX attribute adapter and JMX operation adapter 
in simple terms, any update or modification on MBean changed will be captured by  
attribute-polling-channel-adapter and this changes will be converted into notification message and 
dropped into ordersChannel, we have also defined filter which will look for any messages IN orderschannel operation(input channel)
and executes the filter method and places message in output-channel "reset",
putting any messages in reset will invoke operation-invoking-channel-adapter which does the reset of MBean.


	<context:component-scan base-package="com.example.demo"></context:component-scan>
	<!-- to expose @ManagedResource for JMX monitoring and component 
	we need  to expose ManagedAtrribe and ManagedOperation this can done using this tag-->
	<context:mbean-export/>

	<!--  this interface contains methods which is essential for creation registration and deletion of MBeans (MangedResource beans) 
	this is the core component of the JMX infrastructure -->
	<context:mbean-server/> 
 	
 	<!--  this broadcast message to the subscribers -->
 	<int:publish-subscribe-channel id="ordersChannel"/>

	<!-- defines  attribute polling channel adapter for JMX attribute value  
	whenever any changes or modification to this attribute "orders" then changes will be captured by 
	attribute-polling-channel-adapter and this changes will be converted into notification message and 
	dropped into ordersChannel-->
	<jmx:attribute-polling-channel-adapter channel="ordersChannel" attribute-name="Orders" 
	                 object-name="com.example.demo:type=OrderMBean,name=orderMBean">
 		<int:poller max-messages-per-poll="1" fixed-rate="5000"/>
 	</jmx:attribute-polling-channel-adapter>	
 	
 	<bean id="commonServiceActivator" class ="com.example.demo.CommonServiceActivator"/>

	<!--  this service activator will be triggered when it receive messages in the input-channel 
	thereby activating the method and puts message to be delivered in the output-channel -->
 	<int:service-activator ref="commonServiceActivator" method="attributePolled" input-channel="ordersChannel" output-channel="processedOrders"/>
 	
 	<int:channel id="processedOrders" >
 		<int:queue/>
 	</int:channel>

	<!--messages from the input channel will be filtered using the method and written message in the output channel--> 	
 	<int:filter ref="maxItemsFilter" input-channel="ordersChannel" method="checkThreshold" output-channel="reset"/>
 	
 	
 	<!-- putting a message in the channel "reset" will trigger operation exposed by MBean, here
 	any message dropped in the reset channel , then resetOrders operation and OrderMBean will be triggered-->
 	<jmx:operation-invoking-channel-adapter id="reset" operation-name="resetOrders" 
 	              object-name="com.example.demo:type=OrderMBean,name=orderMBean"/>
 	
