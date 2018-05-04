package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("customerSevice")
public class CustomerService {

	private final Logger log =  LoggerFactory.getLogger(CustomerService.class);
	Map<Integer,Customer> customerStorage = new HashMap<Integer,Customer>(5,2);
	
	@PostConstruct
	public void init() {
		customerStorage.put(1, new Customer(1, "gopal", 38));
		customerStorage.put(2, new Customer(2, "name1", 30));
	}
	
	public void insert(Customer customer) {
		customerStorage.put(customer.getId(), customer);
	}
	
	public List<Customer> getAll() {
		List<Customer> customerList  = null;

		Set<Map.Entry<Integer, Customer>> customerEntrySet = customerStorage.entrySet();
		customerList = customerEntrySet.stream().map(Entry -> Entry.getValue()).collect(Collectors.toList());
		
		return customerList;
	}
	
	public void delete(int id) {
		customerStorage.remove(id);
	}
	
	public void update(Customer customer) {
		customerStorage.put(customer.getId(), customer);
	}
	
	public Customer get(int id) {
		Customer customer =  null;
		
		customer = customerStorage.get(id);
		return customer;
	}
}
