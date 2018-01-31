package com.cencosud.middleware.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemDocument {
	private String index;
	private Integer quantity;
	private String seller;
	private String id;
		
	public OrderItemDocument() {
		super();
	}

	public OrderItemDocument(Integer quantity, String seller, String id) {
		super();
		this.quantity = quantity;
		this.seller = seller;
		this.id = id;
	}

	public OrderItemDocument(String index, Integer quantity) {
		super();
		this.index = index;
		this.quantity = quantity;
	}
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "OrderItemDocument [index=" + index + ", quantity=" + quantity + ", seller=" + seller + ", id=" + id
				+ "]";
	}


}
