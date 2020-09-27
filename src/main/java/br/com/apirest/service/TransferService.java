package br.com.apirest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirest.model.TransferModel;
import br.com.apirest.repository.TransferRepository;

@Service
public class TransferService {

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private AccountService accountService;

	@Autowired
	public TransferService(TransferRepository transferRepository) {
		this.transferRepository = transferRepository;
	}

	@Transactional
	public TransferModel create(TransferModel transfer) {

		TransferModel saved = transferRepository.save(transfer);
		accountService.transferUpdate(saved);

		return saved;

	}

}
