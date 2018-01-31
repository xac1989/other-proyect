package com.cencosud.middleware.profile.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * 
 * 
 * <h1>UserPreference</h1>
 * <p>
 * Modelo para crear la preferencia de usuario
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jul 25, 2017
 */
public class UserPreference {
	
	private Integer defaultSalesChannel;
	private String userId;
	private DeliveryMode deliveryMode;
	private Address deliveryAddress;
	
	public UserPreference() {
	}	

	/**
	 * @param defaultSalesChannel
	 * @param userId
	 * @param deliveryMode
	 * @param deliveryAddress
	 */
	public UserPreference(Integer defaultSalesChannel, String userId, DeliveryMode deliveryMode,
			Address deliveryAddress) {
		super();
		this.defaultSalesChannel = defaultSalesChannel;
		this.userId = userId;
		this.deliveryMode = deliveryMode;
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * @return the defaultSalesChannel
	 */
	public Integer getDefaultSalesChannel() {
		return defaultSalesChannel;
	}

	/**
	 * @param defaultSalesChannel the defaultSalesChannel to set
	 */
	public void setDefaultSalesChannel(Integer defaultSalesChannel) {
		this.defaultSalesChannel = defaultSalesChannel;
	}

	/**
	 * @return the userId
	 */
	@JsonGetter("id")
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	@JsonSetter("id")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the deliveryMode
	 */
	public DeliveryMode getDeliveryMode() {
		return deliveryMode;
	}

	/**
	 * @param deliveryMode the deliveryMode to set
	 */
	public void setDeliveryMode(DeliveryMode deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	/**
	 * @return the deliveryAddress
	 */
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * @param deliveryAddress the deliveryAddress to set
	 */
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserPreference [defaultSalesChannel=");
		builder.append(defaultSalesChannel);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", deliveryMode=");
		builder.append(deliveryMode);
		builder.append(", deliveryAddress=");
		builder.append(deliveryAddress);
		builder.append("]");
		return builder.toString();
	}
	
}
