package br.com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apirest.model.PurchaseModel;

public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long> {

}