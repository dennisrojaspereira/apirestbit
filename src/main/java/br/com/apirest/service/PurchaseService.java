package br.com.apirest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.apirest.model.PurchaseModel;
import br.com.apirest.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private AccountService accountService;

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	public PurchaseService(PurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}

	@Transactional
	public PurchaseModel create(PurchaseModel purchase) {

		if (purchase.getValue() > 0) {
			JsonNode currency = restTemplate.getForObject("https://api.coinbase.com/v2/prices/spot?currency=BRL",
					JsonNode.class);
			JsonNode data = currency.get("data");
			double price = data.get("amount").asDouble();

			double total = price * purchase.getValue();
			if (accountService.getBalance(purchase.getCustomer()) >= total) {
				purchase.setTotal(total);
				purchase.setPriceBitCoin(price);
				PurchaseModel saved = purchaseRepository.save(purchase);
				accountService.purchaseUpdate(purchase);
				return saved;
			}
		}
		return purchase;

	}

}
