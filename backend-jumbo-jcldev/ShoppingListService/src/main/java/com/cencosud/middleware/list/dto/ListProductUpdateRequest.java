package com.cencosud.middleware.list.dto;

import java.util.List;

import com.cencosud.middleware.list.model.ListUpdate;

/**
 * 
 * 
 * <h1>ListUpdateRequest</h1>
 * <p>
 * Objeto request para actualizar las listas
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
public class ListProductUpdateRequest {
	private String skuId;
	private List<ListUpdate> lists;
	
	public ListProductUpdateRequest() {
	}

	/**
	 * @param skuId
	 * @param lists
	 */
	public ListProductUpdateRequest(String skuId, List<ListUpdate> lists) {
		super();
		this.skuId = skuId;
		this.lists = lists;
	}

	/**
	 * @return the skuId
	 */
	public String getSkuId() {
		return skuId;
	}

	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	/**
	 * @return the lists
	 */
	public List<ListUpdate> getLists() {
		return lists;
	}

	/**
	 * @param lists the lists to set
	 */
	public void setLists(List<ListUpdate> lists) {
		this.lists = lists;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListUpdateRequest [skuId=");
		builder.append(skuId);
		builder.append(", lists=");
		builder.append(lists);
		builder.append("]");
		return builder.toString();
	}
}
