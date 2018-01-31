package com.cencosud.mobile.profile.model;

public class Item {

	private String skuId;
	private Integer index;
	private Integer quantity;

		
	public Item() {
		super();
	}

	public Item(String skuId, Integer index, Integer quantity) {
		super();
		this.skuId = skuId;
		this.index = index;
		this.quantity = quantity;
	}
	
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [skuId=" + skuId + ", index=" + index + ", quantity=" + quantity + "]";
	}
	
	

}
