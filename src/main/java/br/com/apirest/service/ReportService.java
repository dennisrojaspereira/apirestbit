package br.com.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirest.model.AccountModel;
import br.com.apirest.repository.AccountRepository;
import br.com.apirest.wrapper.ReportWrapper;

@Service
public class ReportService {

	@Autowired
	private AccountRepository accountRepository;

	public ReportWrapper report(Long customerId) {
		ReportWrapper reportWrapper = new ReportWrapper();
		Optional<AccountModel> findById = accountRepository.findById(customerId);
		if (findById.isPresent() ) {
			AccountModel accountModel = findById.get();
			reportWrapper.setBalance(accountModel.getBalance());
			reportWrapper.setBitcoinValue(accountModel.getBitcoins());
//			reportWrapper.setTotalInvestment(totalInvestment);
//			reportWrapper.setTotalProfit(totalProfit);
			return reportWrapper;
		}
		return null;
	}

}
