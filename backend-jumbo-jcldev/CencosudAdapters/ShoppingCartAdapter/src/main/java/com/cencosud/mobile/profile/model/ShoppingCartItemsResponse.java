package com.cencosud.mobile.profile.model;

import java.util.List;

public class ShoppingCartItemsResponse {
	
	private List<Item> items;
	private Metadata metadata;
	
	public ShoppingCartItemsResponse() {
	}

	public ShoppingCartItemsResponse(List<Item> items, Metadata metadata) {
		this.items = items;
		this.metadata = metadata;
	}

	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "ShoppingCartItemsResponse [items=" + items + ", metadata=" + metadata + "]";
	}
	
	
}
