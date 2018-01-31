package com.cencosud.mobile.list.model;

/**
 * 
 * <h1>ListUpdate</h1>
 * <p>
 * Modelo de listas con datos a actualizar.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
public class ListUpdate {
	private String listId;
	private Integer quantity;
	private String status;
	private String name;
	
	public ListUpdate() {
	}

	/**
	 * @param listId
	 * @param quantity
	 * @param status
	 * @param name
	 */
	public ListUpdate(String listId, Integer quantity, String status, String name) {
		super();
		this.listId = listId;
		this.quantity = quantity;
		this.status = status;
		this.name = name;
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListUpdate [listId=");
		builder.append(listId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", status=");
		builder.append(status);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}	
}
