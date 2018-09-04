package pw.io.booker.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pw.io.booker.model.Customer;
import pw.io.booker.model.LoginDetails;
import pw.io.booker.model.Token;
import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.util.TokenCreator;


@RestController
@Transactional
@RequestMapping("/login")
public class AuthenticationController {
	
	Logger logger = Logger.getLogger(AuthenticationController.class);
	
	private CustomerRepository customerRepository;
	private TokenCreator tokenCreator;
	private AuthenticationRepository authenticationRepository;
	
	public AuthenticationController(CustomerRepository customerRepository, TokenCreator tokenCreator,
			AuthenticationRepository authenticationRepository) {
		super();
		this.customerRepository = customerRepository;
		this.tokenCreator = tokenCreator;
		this.authenticationRepository = authenticationRepository;
	}
	

	/// ask for user name and password, validate login details 
	@PostMapping
	public String login(@RequestBody LoginDetails login) {
		
		logger.info("Start login");
		
		String token = null; //return null if username+password no match
		
		/// find from customer repository matching username and password
		Customer cust = customerRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		
		/// if a match is found
		//if(cust != null) {
		token = tokenCreator.encode(cust);	    // create token (the string token)
		Token tokenT = new Token();				// create token model bean
		tokenT.setToken(token);					// set string token to token bean
		tokenT.setCustomer(cust);				// set customerr to token bean
		authenticationRepository.save(tokenT);	// save the token bean to repository, token id genereted 
		cust.setToken(tokenT);					// token set to customer
		//}
		
		// if nothing in the repo matches
		//else {
		//	token = "Invalid login details";
		//}
		
		logger.info("End login");
		return token;
	}
	
// brute force login
//		List<Customer> customers = (List<Customer>) customerRepository.findAll();
//		String loginCheck = null;
//		for(Customer cust : customers ) {
//			if(cust.getUsername() == login.getUsername()) {
//				if(cust.getPassword() == login.getPassword()) {
//					loginCheck = tokenCreator.encode(cust);
//				}
//			}	
//		}
//		return loginCheck;		
	
	/// Logging out
	@DeleteMapping
		//public void logout(@RequestBody Customer customer,@RequestHeader Token token)
		public void logout(@RequestHeader String token) {
		/// logout customer by deleting token associated to customer
		//authenticationRepository.deleteById(customer.getToken().getTokenId());
		authenticationRepository.deleteByToken(token);
	}
	
	
	
	

	
	
}
