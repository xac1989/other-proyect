package com.cencosud.mobile.list.model;

import java.util.List;

public class ShoppingList {

	private String listId;
	private String name;
	private Integer skusQuantity;
	private Integer quantity;
	private List<ShoppingListProduct> skus;

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSkusQuantity() {
		return skusQuantity;
	}

	public void setSkusQuantity(Integer skusQuantity) {
		this.skusQuantity = skusQuantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<ShoppingListProduct> getSkus() {
		return skus;
	}

	public void setSkus(List<ShoppingListProduct> skus) {
		this.skus = skus;
	}
}
