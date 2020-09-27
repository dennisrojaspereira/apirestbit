package br.com.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirest.model.AccountModel;
import br.com.apirest.model.CustomerModel;
import br.com.apirest.model.PurchaseModel;
import br.com.apirest.model.TransferModel;
import br.com.apirest.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
	
	
	public AccountModel create(CustomerModel customer) {
		AccountModel accountModel = new AccountModel(null, 0D, customer,0D);
		AccountModel saved = accountRepository.save(accountModel);
		return saved;
	}


	public Double getBalance(CustomerModel customer) {
		AccountModel findFirstByCustomerId = accountRepository.findFirstByCustomerId(customer.getId());
		return findFirstByCustomerId.getBalance();
	}


	public void purchaseUpdate(PurchaseModel purchase) {
		AccountModel account = accountRepository.findFirstByCustomerId(purchase.getCustomer().getId());
		account.setBalance(account.getBalance()-purchase.getTotal());
		account.setBitcoins(account.getBalance()+purchase.getValue());
		accountRepository.save(account);
	}
	
	public void transferUpdate(TransferModel transfer) {
		AccountModel account = accountRepository.findFirstByCustomerId(transfer.getCustomer().getId());
		account.setBalance(account.getBalance()+transfer.getValue());
		accountRepository.save(account);
	}

	
	
	
	
	
	

}
