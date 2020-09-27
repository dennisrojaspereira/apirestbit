package br.com.apirest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.apirest.model.CustomerModel;
import br.com.apirest.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Test
	public void createShouldPersistData() {
		CustomerModel customer = new CustomerModel(null, "Test Name", "123456789");
		customerRepository.save(customer);
		Assertions.assertThat(customer.getId()).isNotNull();
		Assertions.assertThat(customer.getName()).isEqualTo("Test Name");
	}
	@Test(expected = DataIntegrityViolationException.class)
	public void cantCreateDuplicateCpf() {
		CustomerModel customer = new CustomerModel(null, "Test Name", "123456789");
		customerRepository.save(customer);
		CustomerModel customer2 = new CustomerModel(null, "Test Name", "123456789");
		customerRepository.save(customer2);
	  
	}	
	

}
