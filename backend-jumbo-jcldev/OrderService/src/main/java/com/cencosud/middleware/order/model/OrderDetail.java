package com.cencosud.middleware.order.model;

import java.util.List;

public class OrderDetail {

	
	private String id;
	private String date;
	private String statusDescription;
	
	private ShippingDetail shippingDetail; 
	private List<PaymentDetail> paymentDetails;
	private List<PaymentTotal> totals;
	private List<ItemDetail> items;
	
	public OrderDetail(String id, String date, ShippingDetail shippingDetail, List<PaymentDetail> paymentDetails,
			List<PaymentTotal> totals, List<ItemDetail> items) {
		super();
		this.id = id;
		this.date = date;
		this.shippingDetail = shippingDetail;
		this.paymentDetails = paymentDetails;
		this.totals = totals;
		this.items = items;
	}
	public OrderDetail() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ShippingDetail getShippingDetail() {
		return shippingDetail;
	}

	public void setShippingDetail(ShippingDetail shippingDetail) {
		this.shippingDetail = shippingDetail;
	}

	public List<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public List<PaymentTotal> getTotals() {
		return totals;
	}

	public void setTotals(List<PaymentTotal> totals) {
		this.totals = totals;
	}

	public List<ItemDetail> getItems() {
		return items;
	}

	public void setItems(List<ItemDetail> items) {
		this.items = items;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
}
