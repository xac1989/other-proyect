package com.cencosud.mobile.list.dto;

import java.math.BigDecimal;

/**
 * totalPromotions field in ShoppingListDto
 * @author horacio
 *
 */
public class ShoppingListPromotionDto {
	private String group;
	private BigDecimal totalPrice;
	
	public ShoppingListPromotionDto() {}
	
	public ShoppingListPromotionDto(String group, BigDecimal totalPrice) {
		super();
		this.group = group;
		this.totalPrice = totalPrice;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
