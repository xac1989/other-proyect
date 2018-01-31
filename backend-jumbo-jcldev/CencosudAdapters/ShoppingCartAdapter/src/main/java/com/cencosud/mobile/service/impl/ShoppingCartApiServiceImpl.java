package com.cencosud.mobile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.profile.model.Item;
import com.cencosud.mobile.profile.model.ShoppingCartItemResponse;
import com.cencosud.mobile.profile.model.ShoppingCartItemsResponse;
import com.cencosud.mobile.profile.model.enums.RequestProtocolEnum;
import com.cencosud.mobile.service.ShoppingCartApiService;
import com.cencosud.mobile.util.RestServiceUtil;

@Service
public class ShoppingCartApiServiceImpl implements ShoppingCartApiService {
    
	private static final String ITEMS = "/items";

	private static final String BASE_SHOPPING_CART_PATH = "/shoppingcart";
	private static final String RESOURCE_SHOPING_CART_PATH = "/cart";
	
	
	private RestServiceUtil restServiceUtil;
	
	
	/**
	 * @return the restServiceUtil
	 */
	public RestServiceUtil getRestServiceUtil() {
		return restServiceUtil;
	}

	/**
	 * @param restServiceUtil the restServiceUtil to set
	 */
	public void setRestServiceUtil(RestServiceUtil restServiceUtil) {
		this.restServiceUtil = restServiceUtil;
	}

	

	@Override
	public String deleteShoppingCartItem(String shoppingCartId, String itemId, String region,String version) throws NotFoundException {
		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("itemId", itemId);
		String path = BASE_SHOPPING_CART_PATH.concat("/").concat(region).concat("/").concat(version).concat(RESOURCE_SHOPING_CART_PATH);
		String shoppingCartPath = path.concat("/").concat(shoppingCartId).concat(ITEMS);
		return restServiceUtil.executeService(shoppingCartPath, null, String.class, queryParam, RequestProtocolEnum.DELETE);
	}


	@Override
	public ShoppingCartItemResponse editShoppingCartItem(String shoppingCartId, String region,String version, Item item) throws NotFoundException {
		String path = BASE_SHOPPING_CART_PATH.concat("/").concat(region).concat("/").concat(version).concat(RESOURCE_SHOPING_CART_PATH);
		String shoppingCartPath = path.concat("/").concat(shoppingCartId).concat(ITEMS);
		return restServiceUtil.executeService(shoppingCartPath, item, ShoppingCartItemResponse.class, null, RequestProtocolEnum.PUT);
	}

	@Override
	public ShoppingCartItemsResponse addShoppingCartItem(String shoppingCartId, String region,String version, List<Item> items) throws NotFoundException {
		String path = BASE_SHOPPING_CART_PATH.concat("/").concat(region).concat("/").concat(version).concat(RESOURCE_SHOPING_CART_PATH);
		String shoppingCartPath = path.concat("/").concat(shoppingCartId).concat(ITEMS);
		return restServiceUtil.executeService(shoppingCartPath, items, ShoppingCartItemsResponse.class, null, RequestProtocolEnum.POST);
	}

}
