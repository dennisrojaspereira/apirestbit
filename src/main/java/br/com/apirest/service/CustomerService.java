package br.com.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirest.model.CustomerModel;
import br.com.apirest.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

	public CustomerModel getById(long id) {
		Optional<CustomerModel> data = customerRepository.findById(id);
		if ( data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	public CustomerModel create(CustomerModel customer) {
		CustomerModel saved = customerRepository.save(customer);
		accountService.create(saved);
		return saved;
	}

	public CustomerModel update(CustomerModel customer, long id) {
		Optional<CustomerModel> data = customerRepository.findById(id);

    	if (!data.isPresent())
    		return null;

    	customer.setId(id);
    	
    	customerRepository.save(customer);
    	
    	return customer;
    	
    	
		
	}
	
	
	
	
	
	

}
