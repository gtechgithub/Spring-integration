This example portraits about Gateway with Enricher along with Service activator.

1. Basically business logic of Gateway Interface method is mapped RequestChannel

	<int:channel id="findUserInputChannel"/>
	<int:channel id="findUserbyUserNameInputChannel"/>
	<int:channel id="findUserServiceChannel"/>
	<int:channel id="findUserByUserNameServiceChannel"/>
  
	<int:gateway service-interface="com.example.demo.UserService">
		<int:method name="findUser" request-channel="findUserInputChannel"/>
		<int:method name="findByUserName" request-channel="findUserbyUserNameInputChannel"/>
	</int:gateway>
  
2. and enricher listens to the input channel "findUserInputChannel" "findUserbyUserNameInputChannel" and adds payload respectively.
   if request-payload-expression is mentioned then only the particular payload is added 
   in the request-channel="findUserByUserNameServiceChannel
   if not entire in our case User object such as userName, password, email is added
   to the request channel request-channel="findUserServiceChannel"

   Another important thing only property set in the enricher will be allowed to modified.

<int:enricher input-channel="findUserInputChannel" request-channel="findUserServiceChannel"> 
		<int:property name="email" expression="payload.email"/>
		<int:property name="password" expression="payload.password"/>
	</int:enricher>

	<int:enricher input-channel="findUserbyUserNameInputChannel" request-channel="findUserByUserNameServiceChannel"  
	              request-payload-expression="payload.userName">
		<int:property name="email" expression="payload.email"/>
		<int:property name="password" expression="payload.password"/>
	</int:enricher>
  
3. service activator will trigger the below methods once it receives messages 
    in the input-channel   "findUserServiceChannel" and "findUserByUserNameServiceChannel"
  
    	<int:service-activator ref="systemService" method="findUser" input-channel="findUserServiceChannel"/>
	   <int:service-activator ref="systemService" method="findByUserName" input-channel="findUserByUserNameServiceChannel"/>
     
     
