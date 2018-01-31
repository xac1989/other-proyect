package com.cencosud.middleware.recommendation.model;

import java.math.BigDecimal;

public class Product {
	
	private String productId;
	private String productName;
	private String image;
	private BigDecimal price;
	private BigDecimal listPrice;
	private BigDecimal discountRate;
	private boolean available;
	
	public Product() {
	}

	public Product(String productId, String productName, String image, BigDecimal price, BigDecimal listPrice, BigDecimal discountRate,
			boolean available) {
		this.productId = productId;
		this.productName = productName;
		this.image = image;
		this.price = price;
		this.listPrice = listPrice;
		this.discountRate = discountRate;
		this.available = available;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}


}
