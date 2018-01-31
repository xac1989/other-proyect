package com.cencosud.middleware.shoppingcart.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VtexOrderForm {
	 private String orderFormId;
	 private String salesChannel;
	 private LinkedList<VtexItem> items;
	 
	public VtexOrderForm() {
		super();
	}

	public VtexOrderForm(String orderFormId, String salesChannel, LinkedList<VtexItem> items) {
		super();
		this.orderFormId = orderFormId;
		this.salesChannel = salesChannel;
		this.items = items;
	}

	public String getOrderFormId() {
		return orderFormId;
	}

	public void setOrderFormId(String orderFormId) {
		this.orderFormId = orderFormId;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public LinkedList<VtexItem> getItems() {
		return items;
	}

	public void setItems(LinkedList<VtexItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "VtexOrderForm [orderFormId=" + orderFormId + ", salesChannel=" + salesChannel + ", items=" + items
				+ "]";
	}
	 
	
}
