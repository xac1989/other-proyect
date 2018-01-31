package com.cencosud.mobile.order.model;

import java.util.List;

public class OrderDetailR2 {

	private String id;
	private String date;
	private String statusDescription;
	private Object shippingDetail;
	private List<Object> paymentDetails;
	private List<Object> totals;
	private List<ItemDetailR2> items;
	private String paymentTicketUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaymentTicketUrl() {
		return paymentTicketUrl;
	}

	public void setPaymentTicketUrl(String paymentTicketUrl) {
		this.paymentTicketUrl = paymentTicketUrl;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Object getShippingDetail() {
		return shippingDetail;
	}

	public void setShippingDetail(Object shippingDetail) {
		this.shippingDetail = shippingDetail;
	}

	public List<Object> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<Object> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public List<Object> getTotals() {
		return totals;
	}

	public void setTotals(List<Object> totals) {
		this.totals = totals;
	}

	public List<ItemDetailR2> getItems() {
		return items;
	}

	public void setItems(List<ItemDetailR2> items) {
		this.items = items;
	}
}
