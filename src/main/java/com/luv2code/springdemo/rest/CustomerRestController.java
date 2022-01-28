package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> customers (){
		
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
	
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("customer with id "+customerId+" not found ");
		}
		
		return customer;
	}
	
	//to insert
	@PostMapping("/customers")
	public Customer addCustomer (@RequestBody Customer customer) {
		// if id = 0  object insert
		customer.setId(0);
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	// to update
	@PutMapping("/customers")
	public Customer updateCustomer (@RequestBody Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	//to delete
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomerString (@PathVariable int customerId) {
		
		//if customer not found    
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("customer with id "+customerId+" not found ");
		}
		
		customerService.deleteCustomer(customerId);
		return "customer id "+customerId+" is deleted";
	}
	
	
	
	
	
	
}
