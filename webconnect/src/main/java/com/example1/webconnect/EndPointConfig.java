package com.example1.webconnect;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class EndPointConfig {
	   @Bean
	    public MyWebSocket echoEndpoint() {
	        return new MyWebSocket();
	    }

	    @Bean
	    public ServerEndpointExporter endpointExporter() {
	        return new ServerEndpointExporter();
	    }
	    
	    @Bean
	    public ServletContextAware endpointExporterInitializer(final ApplicationContext applicationContext) {
	        return new ServletContextAware() {

	            @Override
	            public void setServletContext(ServletContext servletContext) {
	                ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
	                serverEndpointExporter.setApplicationContext(applicationContext);
	                try {
	                    serverEndpointExporter.afterPropertiesSet();
	                } catch (Exception e) {
	                    throw new RuntimeException(e);
	                }               
	            }           
	        };
	    }
}
