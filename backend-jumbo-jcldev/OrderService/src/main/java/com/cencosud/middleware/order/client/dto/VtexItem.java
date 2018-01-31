package com.cencosud.middleware.order.client.dto;

import java.math.BigDecimal;

import com.cencosud.middleware.order.model.Item;

public class VtexItem {

	private String seller;
	private int quantity;
	private String description;
	private String ean;
	private String refId;
	private String id;
	private String productId;
	private int sellingPrice;
	private int price;

	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Item toModelItem(){
		return new Item(
				this.productId, 
				this.description, 
				null, 
				BigDecimal.valueOf(price).movePointLeft(2));
	}
	
	
	
}
