package com.cencosud.middleware.shoppingcart.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cencosud.middleware.shoppingcart.utils.MsgValidation;


public class ShoppingCartItemDto {

	@NotNull(message = MsgValidation.SKUID_NULL)
	private String skuId;

	@NotNull(message = MsgValidation.INDEX_NULL)
	@Min(value = 0, message = MsgValidation.INDEX_NEGATIVE)
	private Integer index;

	@NotNull(message = MsgValidation.QUANTITY_NULL)
	@Min(value = 0, message = MsgValidation.QUANTITY_NEGATIVE)
	private Integer quantity;
	
	public ShoppingCartItemDto() {
		super();
	}

	
	public ShoppingCartItemDto(String skuId, Integer index, Integer quantity) {
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
