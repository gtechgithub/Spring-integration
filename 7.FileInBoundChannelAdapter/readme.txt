This example demonstrates about the file Inbound Channel Adapter and Outbound Channel Adapter to copy the file

Initially Inbound Channel Adapter specifies the directory file to copy

<file:inbound-channel-adapter id="fileIn" directory="file:${java.io.tmpdir}/spring-integration-samples/input" filename-pattern="*.txt">
	 <int:poller id="poller" fixed-delay="5000"/>
	</file:inbound-channel-adapter>

and we are using the directFieldAccessor to read the FileReadingMessageSource of "Directory" property,
basically directFieldAccessor used to read the values directly.

File inDir = (File) new DirectFieldAccessor
				        (context.getBean(FileReadingMessageSource.class)).getPropertyValue("directory");

		LiteralExpression expression= (LiteralExpression) new DirectFieldAccessor
				       (context.getBean(FileWritingMessageHandler.class)).getPropertyValue("destinationDirectoryExpression"); 
		
		File outDir = new File(expression.getValue());

		System.out.println("Input directory is: " + inDir.getAbsolutePath());
		System.out.println("Output directory is: " + outDir.getAbsolutePath());
		System.out.println("===================================================");
    
Secondly, 
we have file to string transfoer while writes the file to the output channel through service activator.

<file:file-to-string-transformer input-channel="fileIn" output-channel="strings"/>
	
	<int:channel id="strings"/>
	
	<int:service-activator input-channel="strings" output-channel="fileOut" ref="handler"/>
	
	<file:outbound-channel-adapter id="fileOut" directory="file:${java.io.tmpdir}/spring-integration-samples/output"/>
	
  
  
