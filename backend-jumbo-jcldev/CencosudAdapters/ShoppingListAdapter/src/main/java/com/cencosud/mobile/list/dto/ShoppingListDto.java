package com.cencosud.mobile.list.dto;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cencosud.mobile.list.model.ShoppingList;
import com.cencosud.mobile.list.model.ShoppingListProduct;
import com.cencosud.mobile.list.util.PromotionUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonAutoDetect
@JsonInclude(Include.NON_NULL)
public class ShoppingListDto {
	private String listId;
	private String name;
	private Integer skusQuantity;
	private Integer quantity;
	private BigDecimal totalListPrice;
	private BigDecimal totalPrice;
	private List<ShoppingListProductDto> products;
	private List<ShoppingListPromotionDto> totalPromotions;

	public ShoppingListDto() { }

	public ShoppingListDto(ShoppingList shoppingList) {
		if (shoppingList != null) {
			this.listId = shoppingList.getListId();
			this.name = shoppingList.getName();
			this.quantity = 0;
			this.totalPrice = ZERO;
			this.totalListPrice = ZERO;
			this.totalPromotions = new ArrayList<>();
			
			if (shoppingList.getSkus() != null) {
				this.products = new ArrayList<>(shoppingList.getSkus().size());
				for (ShoppingListProduct product : shoppingList.getSkus()) {
					if (product != null && product.getPrice() != null) {
						ShoppingListProductDto productDto = new ShoppingListProductDto(product);
						this.products.add(productDto);
						totalPrice = totalPrice.add(productDto.getPriceSubtotal()); 
						totalListPrice = totalListPrice.add(productDto.getListPriceSubtotal());
						this.quantity += product.getSkuQuantity().getQuantity();
					}
					
				}
				
				this.totalPromotions = PromotionUtil.calculateTotalPromotions(this.products);

			} else {
				this.products = Collections.<ShoppingListProductDto>emptyList();
			}
			this.skusQuantity = products.size();
		}
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSkusQuantity() {
		return skusQuantity;
	}

	public void setSkusQuantity(Integer skusQuantity) {
		this.skusQuantity = skusQuantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ShoppingListProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ShoppingListProductDto> products) {
		this.products = products;
	}

	public BigDecimal getTotalListPrice() {
		return totalListPrice;
	}

	public void setTotalListPrice(BigDecimal totalListPrice) {
		this.totalListPrice = totalListPrice;
	}

	public List<ShoppingListPromotionDto> getTotalPromotions() {
		return totalPromotions;
	}

	public void setTotalPromotions(List<ShoppingListPromotionDto> totalPromotions) {
		this.totalPromotions = totalPromotions;
	}
	
	
}
