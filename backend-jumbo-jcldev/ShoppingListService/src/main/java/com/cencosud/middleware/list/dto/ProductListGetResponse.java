package com.cencosud.middleware.list.dto;

import java.util.ArrayList;
import java.util.List;

import com.cencosud.middleware.list.model.ProductList;
import com.cencosud.middleware.list.model.ProductShoppingList;

public class ProductListGetResponse {

	private String userId;
	private List<ProductListDto> lists;

	public ProductListGetResponse(ProductList productList) {
		userId = productList.getUserId();
		lists = new ArrayList<>();
		for (ProductShoppingList list : productList.getProductShoppingList()) {
			lists.add(new ProductListDto(list));
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ProductListDto> getLists() {
		return lists;
	}

	public void setLists(List<ProductListDto> lists) {
		this.lists = lists;
	}
}
