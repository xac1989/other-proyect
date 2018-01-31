package com.cencosud.middleware.list.dto;

import java.util.List;

import com.cencosud.middleware.list.model.Product;

/**
 * 
 * 
 * <h1>ListUpdateRequest</h1>
 * <p>
 * Request del servicio para actualizar lista
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 29, 2017
 */
public class ListUpdateRequest {
	private String listId;
	private List<Product> products;
	
	public ListUpdateRequest() {
	}

	/**
	 * @param listId
	 * @param products
	 */
	public ListUpdateRequest(String listId, List<Product> products) {
		super();
		this.listId = listId;
		this.products = products;
	}

	/**
	 * @return the listId
	 */
	public String getListId() {
		return listId;
	}

	/**
	 * @param listId the listId to set
	 */
	public void setListId(String listId) {
		this.listId = listId;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListUpdateRequest [listId=");
		builder.append(listId);
		builder.append(", products=");
		builder.append(products);
		builder.append("]");
		return builder.toString();
	}
	
		
}
