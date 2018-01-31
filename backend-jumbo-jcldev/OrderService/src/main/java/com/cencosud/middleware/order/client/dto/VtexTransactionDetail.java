package com.cencosud.middleware.order.client.dto;

import java.util.ArrayList;
import java.util.List;

import com.cencosud.middleware.order.model.PaymentDetail;

public class VtexTransactionDetail {

	private boolean isActive;
	private String transactionId;
	private List<VtexPaymentInfo> payments;
	
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public List<VtexPaymentInfo> getPayments() {
		return payments;
	}
	public void setPayments(List<VtexPaymentInfo> payments) {
		this.payments = payments;
	}
	
	public List<PaymentDetail> getPaymentsAsModelPaymentDetail(){
		List<PaymentDetail> paymentsDetails = new ArrayList<PaymentDetail>(this.payments.size());
		for(VtexPaymentInfo currentInfo: this.payments){
			paymentsDetails.add(currentInfo.toModelPaymentDetail());
		}
		return paymentsDetails;
		
	}
	
}
