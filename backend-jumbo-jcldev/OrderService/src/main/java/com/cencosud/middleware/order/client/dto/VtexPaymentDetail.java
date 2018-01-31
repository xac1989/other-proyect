package com.cencosud.middleware.order.client.dto;

import java.util.List;

public class VtexPaymentDetail {

	private List<VtexTransactionDetail> transactions;

	public List<VtexTransactionDetail> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<VtexTransactionDetail> transactions) {
		this.transactions = transactions;
	}
	
	
}
