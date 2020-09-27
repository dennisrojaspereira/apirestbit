package br.com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apirest.model.AccountModel;

public interface AccountRepository extends JpaRepository<AccountModel, Long> {
	
	AccountModel findFirstByCustomerId(Long id); 

}