package com.cencosud.middleware.order.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VtexLogisticDetail {

	private int itemIndex;
	private String selectedSla;
	private String lockTTL;
	private String price;
	private String listPrice;
	private String sellingPrice;
	private String deliveryCompany;
	private String shippingEstimate;
	private String shippingEstimateDate;

	public int getItemIndex() {
		return itemIndex;
	}
	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}
	public String getSelectedSla() {
		return selectedSla;
	}
	public void setSelectedSla(String selectedSla) {
		this.selectedSla = selectedSla;
	}
	public String getLockTTL() {
		return lockTTL;
	}
	public void setLockTTL(String lockTTL) {
		this.lockTTL = lockTTL;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getListPrice() {
		return listPrice;
	}
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getDeliveryCompany() {
		return deliveryCompany;
	}
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	public String getShippingEstimate() {
		return shippingEstimate;
	}
	public void setShippingEstimate(String shippingEstimate) {
		this.shippingEstimate = shippingEstimate;
	}
	public String getShippingEstimateDate() {
		return shippingEstimateDate;
	}
	public void setShippingEstimateDate(String shippingEstimateDate) {
		this.shippingEstimateDate = shippingEstimateDate;
	}

	
}
