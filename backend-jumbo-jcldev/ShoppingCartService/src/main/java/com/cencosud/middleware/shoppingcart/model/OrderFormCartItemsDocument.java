package com.cencosud.middleware.shoppingcart.model;

import java.util.List;

public class OrderFormCartItemsDocument {
	private List<OrderItemDocument> orderItems;
	private List<String> expectedOrderFormSections;
	private Boolean noSplitItem;
	
	public OrderFormCartItemsDocument() {
		super();
	}

	public OrderFormCartItemsDocument(List<OrderItemDocument> orderItems, List<String> expectedOrderFormSections,
			Boolean noSplitItem) {
		super();
		this.orderItems = orderItems;
		this.expectedOrderFormSections = expectedOrderFormSections;
		this.noSplitItem = noSplitItem;
	}
	
	public List<OrderItemDocument> getOrderItems() {
		return orderItems;
	}
	
	public void setOrderItems(List<OrderItemDocument> orderItems) {
		this.orderItems = orderItems;
	}
	
	public List<String> getExpectedOrderFormSections() {
		return expectedOrderFormSections;
	}
	
	public void setExpectedOrderFormSections(List<String> expectedOrderFormSections) {
		this.expectedOrderFormSections = expectedOrderFormSections;
	}
	
	public Boolean getNoSplitItem() {
		return noSplitItem;
	}
	
	public void setNoSplitItem(Boolean noSplitItem) {
		this.noSplitItem = noSplitItem;
	}

	@Override
	public String toString() {
		return "VtexOrderFormCartItems [orderItems=" + orderItems + ", expectedOrderFormSections="
				+ expectedOrderFormSections + ", noSplitItem=" + noSplitItem + "]";
	}
	
}
