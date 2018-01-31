package com.cencosud.middleware.address.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.cencosud.middleware.shoppingcart.controller.ShoppingCartItemsController;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemIndexDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemSkuDto;
import com.cencosud.middleware.shoppingcart.service.ShoppingCartItemService;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartItemsControllerTest {

	private static final String SHOPPING_CART_ID = "e69373ce972d4ac499a2b24f88146f24";

	private static final String R2_REGION = "r2";

	@Before
	public void setUp() throws Exception {
	}
	
	@InjectMocks
	private ShoppingCartItemsController controller;
	
	@Mock
	ShoppingCartItemService service;
	
	private ShoppingCartItemIndexDto indexItem;
	private ShoppingCartItemSkuDto skuItem;

	@Test
	public void testUpdateItemInCart() {
		indexItem = buildIndexItem();
		
		ShoppingCartItemDto value = new ShoppingCartItemDto("436",0, 4);
		when(service.updateItemInCart(indexItem, SHOPPING_CART_ID)).thenReturn(value);
		
		ResponseEntity<ShoppingCartItemDto> result = controller.updateShoppingCartItem(R2_REGION, SHOPPING_CART_ID, indexItem, new BeanPropertyBindingResult(skuItem, "skuId"));
		
		verify(service, times(1)).updateItemInCart(indexItem, SHOPPING_CART_ID);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void testAddItemInCart() {
		List<ShoppingCartItemSkuDto> skuItemList = new ArrayList<>();
		skuItem = buildSkuItem();
		skuItemList.add(skuItem);
		
		List<ShoppingCartItemDto> value = new ArrayList<>();
		value.add(new ShoppingCartItemDto("436", 0, 4));
		when(service.addItemInCart(skuItemList, SHOPPING_CART_ID)).thenReturn(value);
		
		ResponseEntity<List<ShoppingCartItemDto>> result = controller.addShoppingCartItem(R2_REGION, SHOPPING_CART_ID, skuItemList, new BeanPropertyBindingResult(skuItem, "skuId"));
		
		verify(service, times(1)).addItemInCart(skuItemList, SHOPPING_CART_ID);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	private ShoppingCartItemSkuDto buildSkuItem() {
		ShoppingCartItemSkuDto item = new ShoppingCartItemSkuDto("436",0, 4);
		return item;
	}

	@Test
	public void testRemoveItemInCart() {
		indexItem = buildIndexItem();
		
		ShoppingCartItemDto value = new ShoppingCartItemDto("436",0, 4);
		when(service.updateItemInCart(indexItem, SHOPPING_CART_ID)).thenReturn(value);
		
		ResponseEntity<Object> result = controller.deleteShoppingCartItem(R2_REGION, SHOPPING_CART_ID, 0);
		
		verify(service, times(1)).deleteItemInCartByIndex(0, SHOPPING_CART_ID);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	private ShoppingCartItemIndexDto buildIndexItem() {
		ShoppingCartItemIndexDto item = new ShoppingCartItemIndexDto("436",0, 4);
		return item;
	}

}
