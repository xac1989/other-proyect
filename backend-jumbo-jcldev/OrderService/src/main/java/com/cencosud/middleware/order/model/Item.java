package com.cencosud.middleware.order.model;

import java.math.BigDecimal;

public class Item {

	private String productId;
	private String name;
	private String image;
	private BigDecimal price;

	public Item() { }

	public Item(String productId, String name, String image, BigDecimal price) {
		super();
		this.productId = productId;
		this.name = name;
		this.image = image;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
