package br.com.apirest.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class AccountModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double balance;
	
	@Column(nullable = false)
	private Double bitcoins;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customer;
	

	public AccountModel() {
		super();
	}

	public AccountModel(Long id, Double balance,CustomerModel customer,Double bitcoins) {
		super();
		this.id = id;
		this.balance = balance;
		this.bitcoins = bitcoins;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public Double getBitcoins() {
		return bitcoins;
	}

	public void setBitcoins(Double bitcoins) {
		this.bitcoins = bitcoins;
	}
	
	

	
}