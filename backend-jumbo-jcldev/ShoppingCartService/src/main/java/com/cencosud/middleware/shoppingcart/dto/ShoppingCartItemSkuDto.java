package com.cencosud.middleware.shoppingcart.dto;

public class ShoppingCartItemSkuDto extends ShoppingCartItemDto{
	
	public ShoppingCartItemSkuDto() {
		super();
	}

	public ShoppingCartItemSkuDto(String skuId, Integer index, Integer quantity) {
		super(skuId, index, quantity);
	}
}
