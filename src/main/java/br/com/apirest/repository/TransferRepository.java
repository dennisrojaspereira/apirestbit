package br.com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apirest.model.TransferModel;

public interface TransferRepository extends JpaRepository<TransferModel, Long> {

}