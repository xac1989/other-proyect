package com.cencosud.middleware.shoppingcart.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.shoppingcart.annotation.Loggable;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemIndexDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemSkuDto;
import com.cencosud.middleware.shoppingcart.service.ShoppingCartItemService;

@RestController
@RequestMapping(path = "/{region}/v1/cart/{shoppingCartId}/items", produces = "application/json; charset=UTF-8")
public class ShoppingCartItemsController {

	@Autowired
	ShoppingCartItemService service;
	
	@Loggable
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<List<ShoppingCartItemDto>> addShoppingCartItem(@PathVariable("region") String region,
			@PathVariable String shoppingCartId,
			@Valid @NotEmpty @RequestBody(required = true) List<ShoppingCartItemSkuDto> items,
			Errors errors) {
		
		if (errors.hasErrors()) {
			throw new IllegalArgumentException( this.getValidationErrors(errors) );
        }
		
		List<ShoppingCartItemDto> updatedItem = service.addItemInCart(items, shoppingCartId);
		
		return new ResponseEntity<List<ShoppingCartItemDto>>(updatedItem, HttpStatus.OK);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.DELETE, path = "/{itemIndex}")
	public ResponseEntity<Object> deleteShoppingCartItem(@PathVariable("region") String region,
			@PathVariable String shoppingCartId,
			@PathVariable Integer itemIndex) {
		
		service.deleteItemInCartByIndex(itemIndex, shoppingCartId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		

	@Loggable
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ShoppingCartItemDto> updateShoppingCartItem(@PathVariable("region") String region,
			@PathVariable String shoppingCartId,
			@Valid @RequestBody(required = true) ShoppingCartItemIndexDto item,
			Errors errors) {
		
		if (errors.hasErrors()) {
			throw new IllegalArgumentException( this.getValidationErrors(errors) );
        }

		ShoppingCartItemDto updatedItem = service.updateItemInCart(item, shoppingCartId);
		
		return new ResponseEntity<ShoppingCartItemDto>(updatedItem, HttpStatus.OK);
	}
	
	
	@Loggable
	private String getValidationErrors(Errors errors) {
		String messageErrors = (errors.getAllErrors()
				.stream()
				.map(x1 -> x1.getDefaultMessage())
				.collect(Collectors.joining(",")));
		
		return messageErrors;
	}
}
