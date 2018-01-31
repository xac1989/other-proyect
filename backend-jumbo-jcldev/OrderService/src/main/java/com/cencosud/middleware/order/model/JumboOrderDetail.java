package com.cencosud.middleware.order.model;

public class JumboOrderDetail extends OrderDetail {

	private String paymentTicketUrl;

	public String getPaymentTicketUrl() {
		return paymentTicketUrl;
	}

	public void setPaymentTicketUrl(String paymentTicketUrl) {
		this.paymentTicketUrl = paymentTicketUrl;
	}
}
