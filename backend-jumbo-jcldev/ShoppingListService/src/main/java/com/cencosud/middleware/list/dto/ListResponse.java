package com.cencosud.middleware.list.dto;

import java.util.List;

import com.cencosud.middleware.list.model.ShoppingList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class ListResponse {

	private String userId;

	@JsonProperty("lists")
	private List<ShoppingList> shoppingList;

	public ListResponse() {
	}

	/**
	 * @param userId
	 * @param email
	 * @param shoppingList
	 */
	public ListResponse(String userId, List<ShoppingList> shoppingList) {
		super();
		this.userId = userId;
		this.shoppingList = shoppingList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ShoppingList> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<ShoppingList> shoppingList) {
		this.shoppingList = shoppingList;
	}

}
