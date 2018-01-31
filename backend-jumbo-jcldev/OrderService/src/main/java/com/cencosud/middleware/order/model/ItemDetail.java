package com.cencosud.middleware.order.model;

import java.math.BigDecimal;

public class ItemDetail {

	private String productId;
	private String description;
	private String image;
	private BigDecimal price;
	private int quantity;

	public ItemDetail(String productId, String description, String image, BigDecimal price, int quantity) {
		this.productId = productId;
		this.description = description;
		this.image = image;
		this.price = price;
		this.quantity = quantity;
	}

	public ItemDetail() {
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
