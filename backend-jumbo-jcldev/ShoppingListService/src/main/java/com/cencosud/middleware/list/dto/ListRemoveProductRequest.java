package com.cencosud.middleware.list.dto;

import java.util.List;

/**
 * 
 * 
 * <h1>ListRemoveProductRequest</h1>
 * <p>
 * Objeto request para eliminar varios productos de una lista
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 27, 2017
 */
public class ListRemoveProductRequest {
	private String listId;
	private List<Integer> skuIds;
	
	public ListRemoveProductRequest() {
	}

	/**
	 * @param listId
	 * @param skuIds
	 */
	public ListRemoveProductRequest(String listId, List<Integer> skuIds) {
		super();
		this.listId = listId;
		this.skuIds = skuIds;
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
	 * @return the skuIds
	 */
	public List<Integer> getSkuIds() {
		return skuIds;
	}

	/**
	 * @param skuIds the skuIds to set
	 */
	public void setSkuIds(List<Integer> skuIds) {
		this.skuIds = skuIds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListRemoveProductRequest [listId=");
		builder.append(listId);
		builder.append(", skuIds=");
		builder.append(skuIds);
		builder.append("]");
		return builder.toString();
	}
}
