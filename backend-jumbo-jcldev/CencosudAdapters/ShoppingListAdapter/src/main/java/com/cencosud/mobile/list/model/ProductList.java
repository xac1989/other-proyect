package com.cencosud.mobile.list.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductList {

	private String userId;
	@JsonProperty("lists")
	private List<ProductShoppingList> productShoppingList;
	
	private Metadata metadata;

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

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}
