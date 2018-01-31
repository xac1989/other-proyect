package com.cencosud.middleware.list.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductList {

	private String userId;
	@JsonProperty("lists")
	private List<ProductShoppingList> productShoppingList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ProductShoppingList> getProductShoppingList() {
		return productShoppingList;
	}

	public void setProductShoppingList(List<ProductShoppingList> productShoppingList) {
		this.productShoppingList = productShoppingList;
	}

}
