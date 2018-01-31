package com.cencosud.middleware.list.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserList {

	private String id;
	private String shoppingList;
	private List<String> shoppingListFromJson;

	public UserList() {
		
	}

	public UserList(String id, String shoppingList, List<String> shoppingListFromJson) {
		this.id = id;
		this.shoppingList = shoppingList;
		this.shoppingListFromJson = shoppingListFromJson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(String shoppingList) {
		this.shoppingList = shoppingList;
	}

	public List<String> getShoppingListFromJson() {
		return shoppingListFromJson;
	}

	public void setShoppingListFromJson(List<String> shoppingListFromJson) {
		this.shoppingListFromJson = shoppingListFromJson;
	}
}
