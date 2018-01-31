package com.cencosud.middleware.shoppingcart.repository;

import java.util.List;

import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemIndexDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemSkuDto;

public interface ShoppingCartItemRepository {
		
	void deleteItemInCartByIndex(Integer intemIndex, String shoppingCartId) ;

	ShoppingCartItemDto updateItemInCart(ShoppingCartItemIndexDto item, String shoppingCartId);

	List<ShoppingCartItemDto> addItemInCart(List<ShoppingCartItemSkuDto> item, String shoppingCartId);
	
}
