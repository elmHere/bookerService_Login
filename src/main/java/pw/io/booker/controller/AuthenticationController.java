package pw.io.booker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pw.io.booker.model.Customer;
import pw.io.booker.model.Login;
import pw.io.booker.repo.CustomerRepository;

public class AuthenticationController {
	
	private CustomerRepository customerRepository;
	
	public AuthenticationController(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	

	/// ask for user name and password
	@PostMapping
	public String getUsernamePword(@RequestBody Login login) {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		for(String usename : )
		
		
		return null;
		
	}

	
	
}
