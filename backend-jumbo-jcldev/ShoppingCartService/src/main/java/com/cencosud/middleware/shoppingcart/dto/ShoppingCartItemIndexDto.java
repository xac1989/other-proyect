package com.cencosud.middleware.shoppingcart.dto;

public class ShoppingCartItemIndexDto extends ShoppingCartItemDto{

	public ShoppingCartItemIndexDto() {
		super();
	}

	public ShoppingCartItemIndexDto(String skuId, Integer index, Integer quantity) {
		super(skuId, index, quantity);
	}
}
