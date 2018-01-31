package com.cencosud.middleware.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemIndexDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemSkuDto;
import com.cencosud.middleware.shoppingcart.repository.ShoppingCartItemRepository;
import com.cencosud.middleware.shoppingcart.service.ShoppingCartItemService;

@Service
public class JumboShoppingCartItemServiceImpl implements ShoppingCartItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JumboShoppingCartItemServiceImpl.class);
	
	private static final String REGION_ID = "r2";
		
	@Autowired
	private ShoppingCartItemRepository repo;
	    
    @Override
    public String getRegionId() {
        return REGION_ID;
    }

	@Override
	public ShoppingCartItemDto updateItemInCart(ShoppingCartItemIndexDto item, String shoppingCartId) {

			try {
				return repo.updateItemInCart(item, shoppingCartId);
			} catch (JSONException  e) {
				LOGGER.warn("A problem ocurred updating shopping cart item",e);
			}
			return new ShoppingCartItemDto("",0,0);
	}
	
	@Override
	public void deleteItemInCartByIndex(Integer itemId, String shoppingCartIdId) {
		try {
			repo.deleteItemInCartByIndex(itemId, shoppingCartIdId);
		} catch (JSONException e) {
			LOGGER.warn("A problem ocurred removing shopping cart item",e);
		}
	}

	@Override
	public List<ShoppingCartItemDto> addItemInCart(List<ShoppingCartItemSkuDto> items, String shoppingCartId) {
		try {
			return repo.addItemInCart(items, shoppingCartId);
		} catch (JSONException e) {
			LOGGER.warn("A problem ocurred updating shopping cart item",e);
		}
		return new ArrayList<>();
	}



}
