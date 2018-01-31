package com.cencosud.middleware.order.model;

import java.math.BigDecimal;

public class PaymentDetail {
	private String paymentSystem;
	private BigDecimal total;
	
	public PaymentDetail(String paymentSystem, BigDecimal total) {
		this.paymentSystem = paymentSystem;
		this.total = total;
	}

	public PaymentDetail() {
	}

	public String getPaymentSystem() {
		return paymentSystem;
	}

	public void setPaymentSystem(String paymentSystem) {
		this.paymentSystem = paymentSystem;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
