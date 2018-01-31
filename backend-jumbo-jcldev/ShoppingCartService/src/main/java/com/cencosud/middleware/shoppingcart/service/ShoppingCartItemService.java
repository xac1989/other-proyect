package com.cencosud.middleware.shoppingcart.service;

import java.util.List;

import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemIndexDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemSkuDto;

public interface ShoppingCartItemService {
	
	String getRegionId();
	
	void deleteItemInCartByIndex(Integer itemIndex, String shoppongCartId);

	List<ShoppingCartItemDto> addItemInCart(List<ShoppingCartItemSkuDto> item, String shoppingCartId);

	ShoppingCartItemDto updateItemInCart(ShoppingCartItemIndexDto item, String shoppingCartId);
}
