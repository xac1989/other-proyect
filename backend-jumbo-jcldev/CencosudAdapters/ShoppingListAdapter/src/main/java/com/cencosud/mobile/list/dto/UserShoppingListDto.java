package com.cencosud.mobile.list.dto;

import java.util.ArrayList;
import java.util.List;

import com.cencosud.mobile.list.model.Metadata;
import com.cencosud.mobile.list.model.ShoppingList;
import com.cencosud.mobile.list.model.UserShoppingList;

public class UserShoppingListDto {

	private String userId;
	private List<ShoppingListDto> lists;
	private Metadata metadata;

	public UserShoppingListDto() { }

	public UserShoppingListDto(UserShoppingList userShoppingList) {
		if (userShoppingList != null) {
			this.userId = userShoppingList.getUserId();
			this.metadata = userShoppingList.getMetadata();
			if (userShoppingList.getShoppingList() != null) {
				lists = new ArrayList<>(userShoppingList.getShoppingList().size());
				for (ShoppingList shoppingList : userShoppingList.getShoppingList()) {
					lists.add(new ShoppingListDto(shoppingList));
				}
			} else {
				lists = new ArrayList<>();
			}
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ShoppingListDto> getLists() {
		return lists;
	}

	public void setLists(List<ShoppingListDto> lists) {
		this.lists = lists;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
}
