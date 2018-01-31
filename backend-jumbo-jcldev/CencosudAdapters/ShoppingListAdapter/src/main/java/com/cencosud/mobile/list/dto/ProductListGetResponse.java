package com.cencosud.mobile.list.dto;

import java.util.ArrayList;
import java.util.List;

import com.cencosud.mobile.list.model.Metadata;
import com.cencosud.mobile.list.model.ProductList;
import com.cencosud.mobile.list.model.ProductShoppingList;

public class ProductListGetResponse {

	private String userId;
	private List<ProductListDto> lists;
	private Metadata metadata;

	public ProductListGetResponse(ProductList productList) {
		userId = productList.getUserId();
		lists = new ArrayList<>();
		this.metadata = productList.getMetadata();
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

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
}
