package com.cencosud.middleware.list.model;

public class Sku {

	private String id;
	private SkuQuantity skuQuantity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SkuQuantity getSkuQuantity() {
		return skuQuantity;
	}
	public void setSkuQuantity(SkuQuantity skuQuantity) {
		this.skuQuantity = skuQuantity;
	}
	
}
