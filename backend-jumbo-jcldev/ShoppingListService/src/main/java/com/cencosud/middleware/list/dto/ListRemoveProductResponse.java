package com.cencosud.middleware.list.dto;

/**
 * 
 * 
 * <h1>ListRemoveProductResponse</h1>
 * <p>
 * Respuesta para la eliminación de productos de una lista
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 27, 2017
 */
public class ListRemoveProductResponse {
	private String listId;
	private String status;
	
	public ListRemoveProductResponse() {
	}

	/**
	 * @param listId
	 * @param status
	 */
	public ListRemoveProductResponse(String listId, String status) {
		super();
		this.listId = listId;
		this.status = status;
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
}
