package com.cencosud.mobile.profile.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCartItemResponse {
	
	private Item item;
	private Metadata metadata;
	
	public ShoppingCartItemResponse() {
		super();
	}
	
	public ShoppingCartItemResponse(Item item, Metadata metadata) {
		super();
		this.item = item;
		this.metadata = metadata;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Metadata getMetadata() {
		return metadata;
	}
	
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "ShoppingCartItemResponse [item=" + item + ", metadata=" + metadata + "]";
	}
	
	
	
}
