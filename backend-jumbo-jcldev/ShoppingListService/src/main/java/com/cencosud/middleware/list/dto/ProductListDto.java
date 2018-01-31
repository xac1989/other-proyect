package com.cencosud.middleware.list.dto;

import com.cencosud.middleware.list.model.ProductShoppingList;

public class ProductListDto {
	private String listId;
	private String name;
	private Integer quantity;

	public ProductListDto(ProductShoppingList list) {
		this.listId = list.getListId();
		this.name = list.getName();
		this.quantity = list.getQuantity();
	}

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
