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
@Table(name = "PURCHASE")
public class PurchaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double value;
	
	@Column(nullable = false)
	private Double priceBitCoin;
	
	@Column(nullable = false)
	private Double total;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customer;
	
	public PurchaseModel() {
		super();
	}

	

	public PurchaseModel(Long id, Double value,CustomerModel customer,Double priceBitCoin,Double total) {
		super();
		this.id = id;
		this.value = value;
		this.customer = customer;
		this.priceBitCoin = priceBitCoin;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public Double getPriceBitCoin() {
		return priceBitCoin;
	}

	public void setPriceBitCoin(Double priceBitCoin) {
		this.priceBitCoin = priceBitCoin;
	}



	public Double getTotal() {
		return total;
	}



	public void setTotal(Double total) {
		this.total = total;
	}
	
	

	
}