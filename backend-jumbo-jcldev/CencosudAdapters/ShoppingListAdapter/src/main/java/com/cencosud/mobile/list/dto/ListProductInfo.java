package com.cencosud.mobile.list.dto;

/**
 * 
 * 
 * <h1>ListProductInfo</h1>
 * <p>
 * Objeto con infirmacion para consultar las listas buscando un producto
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 28, 2017
 */
public class ListProductInfo {
	private String listId;
	private String name;
	private Integer quantity;
	
	public ListProductInfo() {
	}

	/**
	 * @param listId
	 * @param name
	 * @param quantity
	 */
	public ListProductInfo(String listId, String name, Integer quantity) {
		super();
		this.listId = listId;
		this.name = name;
		this.quantity = quantity;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListProductInfo [listId=");
		builder.append(listId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}
	
}
