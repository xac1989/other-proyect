package com.cencosud.mobile.service;

import java.util.List;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.profile.model.Item;
import com.cencosud.mobile.profile.model.ShoppingCartItemResponse;
import com.cencosud.mobile.profile.model.ShoppingCartItemsResponse;

public interface ShoppingCartApiService {

	String deleteShoppingCartItem(String shoppingCartId, String itemId, String region,String version) throws NotFoundException;

	ShoppingCartItemResponse editShoppingCartItem(String shoppingCartId, String region, String version, Item item) throws NotFoundException;

	ShoppingCartItemsResponse addShoppingCartItem(String shoppingCartId, String region, String version, List<Item> items) throws NotFoundException;
		
}
