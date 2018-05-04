package com.example.demo;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class InboundEndpoint {

	// similar to the Autowiried annotation, moreover
	// @Resource is used to inject bean for field and setter methods
	// by its resource name
	@Resource(name = "customerSevice")
	CustomerService service;

	private final Logger log = LoggerFactory.getLogger(InboundEndpoint.class);

	public Message<?> getAll(Message<?> msg) {
		log.info("GET ALL method");
		List<Customer> customerList = service.getAll();

		Message<List<Customer>> message = MessageBuilder.withPayload(customerList).copyHeadersIfAbsent(msg.getHeaders())
				.build();

		return message;
	}

	public Message<?> get(Message<String> msg) {
		log.info("GET method");

		String msgPayload = (String) msg.getPayload();

		if (msgPayload != null) {

			int custId = Integer.valueOf(msgPayload);
			Customer customer = service.get(custId);

			if (customer != null && customer.getId() == custId) {
				log.info("build message payload for Customer with id:" + customer.getId());
				Message<Customer> message = MessageBuilder.withPayload(customer)
						                                  .copyHeadersIfAbsent(msg.getHeaders())
						                                  .build();
				return message;
			} else {
				log.info("No Customer with id:" + msgPayload);
				
				Message<CustomerError> message = MessageBuilder.withPayload(new CustomerError("No Customer for id:" + msgPayload))
						                                .copyHeadersIfAbsent(msg.getHeaders()).build();

				return message;
			}

		} else {
			log.info("No payload Sent in the request");

			Message<CustomerError> message = MessageBuilder.withPayload(new CustomerError("No payload Sent in the request"))
					                                .copyHeadersIfAbsent(msg.getHeaders()).build();

			return message;
		}

	}
	
	public void update(Message<Customer> msg){
		log.info("update method");
		service.update(msg.getPayload());
	}
	
	public void insert(Message<Customer> msg){
		log.info("insert method");
		service.update(msg.getPayload());
	}
	
	public void delete	(Message<String> msg){
		log.info("DELETE method");
		int id = Integer.valueOf(msg.getPayload());
		service.delete(id);
	}
}
