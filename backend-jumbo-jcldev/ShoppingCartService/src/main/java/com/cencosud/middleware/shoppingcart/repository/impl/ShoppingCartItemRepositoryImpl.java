package com.cencosud.middleware.shoppingcart.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemIndexDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemSkuDto;
import com.cencosud.middleware.shoppingcart.model.OrderFormCartItemsDocument;
import com.cencosud.middleware.shoppingcart.model.OrderItemDocument;
import com.cencosud.middleware.shoppingcart.model.VtexItem;
import com.cencosud.middleware.shoppingcart.model.VtexOrderForm;
import com.cencosud.middleware.shoppingcart.repository.ShoppingCartItemRepository;
import com.cencosud.middleware.shoppingcart.utils.VtexUtilClient;

@Repository
public class ShoppingCartItemRepositoryImpl implements ShoppingCartItemRepository {
	
	private static final String PATH_ORDERFORM_PROFILE = "/api/checkout/pub/orderForm/";
	private static final String ITEMS = "/items";
	
	static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartItemRepositoryImpl.class);
	
	@Autowired
	@Qualifier("vTexUtilClient")
	private VtexUtilClient clientUtil;	

	@Override
	public ShoppingCartItemDto updateItemInCart(ShoppingCartItemIndexDto item, String shoppingCartId){
		OrderFormCartItemsDocument document = buildDocumentWithIndexes(item);
		
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(PATH_ORDERFORM_PROFILE);
		pathBuilder.append(shoppingCartId);
		pathBuilder.append(ITEMS);
		
		VtexOrderForm result = clientUtil.executeService(pathBuilder.toString(), document, VtexOrderForm.class, HttpMethod.PATCH);
		LOGGER.info(result.toString());
		
		return updatedItemFromResult(result,item.getSkuId());
	}


	private ShoppingCartItemDto updatedItemFromResult(VtexOrderForm result, String skuId) {
		ShoppingCartItemDto itemDto = new ShoppingCartItemDto();
		
		itemDto.setSkuId(skuId);
		itemDto.setIndex(0);
		itemDto.setQuantity(0);
		
		for(int i=0; i<result.getItems().size(); i++){
			VtexItem vtexItem = result.getItems().get(i);
			if(vtexItem.getId().equals(skuId)){
				itemDto.setQuantity(vtexItem.getQuantity());
				itemDto.setIndex(i);
				break;
			}
		}
		
		return itemDto;
	}
	
	private List<ShoppingCartItemDto> updatedItemsFromResult(VtexOrderForm result) {
		List<ShoppingCartItemDto> itemsDto = new ArrayList<>();

		
		for(int i=0; i<result.getItems().size(); i++){
			VtexItem vtexItem = result.getItems().get(i);
			
			ShoppingCartItemDto itemDto = new ShoppingCartItemDto();
			itemDto.setQuantity(vtexItem.getQuantity());
			itemDto.setSkuId(vtexItem.getId());
			itemDto.setIndex(i);
			itemsDto.add(itemDto);
		}
		
		return itemsDto;
	}


	

	@Override
	public void deleteItemInCartByIndex(Integer itemIndex, String shoppingCartId) {
		ShoppingCartItemIndexDto item = new ShoppingCartItemIndexDto();
		item.setIndex(itemIndex);
		item.setQuantity(0);
		updateItemInCart(item, shoppingCartId);
	}

	@Override
	public List<ShoppingCartItemDto> addItemInCart(List<ShoppingCartItemSkuDto> items, String shoppingCartId) {
	OrderFormCartItemsDocument document = buildDocumentWithSkus(items);
		
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(PATH_ORDERFORM_PROFILE);
		pathBuilder.append(shoppingCartId);
		pathBuilder.append(ITEMS);
		
		VtexOrderForm result = clientUtil.executeService(pathBuilder.toString(), document, VtexOrderForm.class, HttpMethod.PATCH);
		LOGGER.info(result.toString());
		
		return updatedItemsFromResult(result);
	}
	
	

	private OrderFormCartItemsDocument buildDocumentWithIndexes(ShoppingCartItemIndexDto item) {
		List<OrderItemDocument> orderItemDocumentList = new ArrayList<>();
		OrderItemDocument orderItemDocument = new OrderItemDocument();
		orderItemDocument.setIndex(String.valueOf(item.getIndex()));
		orderItemDocument.setQuantity(item.getQuantity());
		orderItemDocumentList.add(orderItemDocument);
		
		return buildDocument(orderItemDocumentList);
	}


	private OrderFormCartItemsDocument buildDocumentWithSkus(List<ShoppingCartItemSkuDto> items) {
		List<OrderItemDocument> orderItemDocumentList = new ArrayList<>();
		
		for(ShoppingCartItemSkuDto itemSku: items){
			OrderItemDocument orderItemDocument = new OrderItemDocument();
			orderItemDocument.setSeller("1");
			orderItemDocument.setId(itemSku.getSkuId());
			orderItemDocument.setQuantity(itemSku.getQuantity());
			orderItemDocumentList.add(orderItemDocument);
		}
	
		return buildDocument(orderItemDocumentList);
	}

	private OrderFormCartItemsDocument buildDocument(List<OrderItemDocument> orderItemDocumentList) {
		OrderFormCartItemsDocument document = new OrderFormCartItemsDocument();		
		List<OrderItemDocument> orderItems = new ArrayList<>();
		orderItems.addAll(orderItemDocumentList);
		
		document.setOrderItems(orderItems);
		document.setExpectedOrderFormSections(Arrays.asList("items"));
		document.setNoSplitItem(true);
		return document;
	}	
	
	
}
