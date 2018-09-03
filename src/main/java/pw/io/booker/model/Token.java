package pw.io.booker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Token {
	@Id
	@GeneratedValue
	private Integer tokenId;
	private String token;
	@OneToOne
	private Customer customer;

	
	public Integer getTokenId() {
		return tokenId;
	}


	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String getToken() {
		return token;
	}

	
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
