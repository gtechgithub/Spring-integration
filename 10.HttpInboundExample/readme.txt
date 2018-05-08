This example explains about the spring integration using http

Here we use inbound Gateway for Get method and inbound channel adapter for PUT/POST/DELETE
so difference between these two is that,in bound gateway we can return customized messages
such as list of Customer object or error message etc.



<!-- get all method -->
    <int-http:inbound-gateway request-channel="requestChannel" 
                              reply-channel="replyChannel" supported-methods="GET" 
                              path="/customer">
    <int-http:request-mapping  produces="application/json"/>
    </int-http:inbound-gateway>
    
    <int:service-activator input-channel="requestChannel" 
                           output-channel="replyChannel"
                           ref="inboundEndpoint" method="getAll"/>



Finally for the post and put we have used router which will route to the appropriate channel

<int-http:inbound-channel-adapter channel="routeRequest" 
                                     supported-methods="POST, PUT" 
                                     status-code-expression="T(org.springframework.http.HttpStatus).NO_CONTENT"
                                     path="/customer" 
                                     request-payload-type="com.example.demo.Customer">
   		<int-http:request-mapping consumes="application/json" />
   </int-http:inbound-channel-adapter> 
    
    <int:router  input-channel="routeRequest" expression="headers.http_requestMethod">
       <int:mapping channel="httpPostChannel" value="POST"/>
       <int:mapping channel="httpPutChannel" value="PUT"/>
     </int:router>
     
    <int:service-activator input-channel="httpPostChannel" 
                           ref="inboundEndpoint" 
                           method="insert" />
                           
    <int:service-activator input-channel="httpPutChannel" 
                           ref="inboundEndpoint" 
                           method="update" />
                           
                           
