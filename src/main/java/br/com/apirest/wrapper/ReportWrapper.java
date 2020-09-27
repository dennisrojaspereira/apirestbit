package br.com.apirest.wrapper;

import java.util.List;

import br.com.apirest.model.TransferModel;

public class ReportWrapper {
	
	private Double balance;
	
	private Double balanceBitcoin;
	
	private Double totalInvestment;
	
	private Double totalProfit;
	
	private Double bitcoinValue;
	
	private List<TransferModel> history5LastTransactions;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getBalanceBitcoin() {
		return balanceBitcoin;
	}

	public void setBalanceBitcoin(Double balanceBitcoin) {
		this.balanceBitcoin = balanceBitcoin;
	}

	public Double getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(Double totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Double getBitcoinValue() {
		return bitcoinValue;
	}

	public void setBitcoinValue(Double bitcoinValue) {
		this.bitcoinValue = bitcoinValue;
	}

	public List<TransferModel> getHistory5LastTransactions() {
		return history5LastTransactions;
	}

	public void setHistory5LastTransactions(List<TransferModel> history5LastTransactions) {
		this.history5LastTransactions = history5LastTransactions;
	}
	
	

}
