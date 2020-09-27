package br.com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apirest.model.CustomerModel;

public interface CustomerRepository  extends JpaRepository<CustomerModel,Long> {


}