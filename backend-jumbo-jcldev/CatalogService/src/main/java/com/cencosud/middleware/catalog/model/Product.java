package com.cencosud.middleware.catalog.model;

import java.util.List;

public class Product{

	private String productName;
	private String productId;
	private String productReference;
	private List<Item> items;
	
	public Product() {
		super();
	}
	
	public Product(String productName, String productId, String productReference, List<Item> items) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.productReference = productReference;
		this.items = items;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getProductReference() {
		return productReference;
	}

	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}

		
}