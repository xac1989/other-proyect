package com.cencosud.middleware.catalog.model;

import java.math.BigDecimal;

public class CommertialOffer{
	private BigDecimal price;
	private BigDecimal listPrice;
	private BigDecimal discountRate;
	private Long availableQuantity;
	private Boolean available;

	public CommertialOffer() {
		super();
	}
	
	public CommertialOffer(BigDecimal price, BigDecimal listPrice, BigDecimal discountRate, Long availableQuantity,
			Boolean available) {
		super();
		this.price = price;
		this.listPrice = listPrice;
		this.discountRate = discountRate;
		this.availableQuantity = availableQuantity;
		this.available = available;
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
	public Long getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
}
