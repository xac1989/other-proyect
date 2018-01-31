package com.cencosud.mobile.list.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
 * <h1>ListDocument</h1>
 * <p>
 * Modelo de datos para la actualización del documento de listas para vtex.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
@JsonInclude(Include.NON_NULL)
public class ListDocument {
	
	private String skus;
	private Integer quantity;
	private Boolean active;
	private Boolean added;
	private Boolean isStoreList;
	private String name;
	private String type;
	private String userId;
	private String id;
	
	public ListDocument() {
	}

	/**
	 * @return the skus
	 */
	public String getSkus() {
		return skus;
	}

	/**
	 * @param skus the skus to set
	 */
	public void setSkus(String skus) {
		this.skus = skus;
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
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the added
	 */
	public Boolean getAdded() {
		return added;
	}

	/**
	 * @param added the added to set
	 */
	public void setAdded(Boolean added) {
		this.added = added;
	}

	/**
	 * @return the isStoreList
	 */
	public Boolean getIsStoreList() {
		return isStoreList;
	}

	/**
	 * @param isStoreList the isStoreList to set
	 */
	public void setIsStoreList(Boolean isStoreList) {
		this.isStoreList = isStoreList;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListDocument [skus=");
		builder.append(skus);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", active=");
		builder.append(active);
		builder.append(", added=");
		builder.append(added);
		builder.append(", isStoreList=");
		builder.append(isStoreList);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}		
}
