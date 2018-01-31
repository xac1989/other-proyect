package com.cencosud.middleware.profile.dto;

import com.cencosud.middleware.profile.model.Address;
import com.cencosud.middleware.profile.model.DeliveryMode;

public class UserPreferencePostRequest {
	
	private String userId;
	private Integer defaultSalesChannel;
	private DeliveryMode deliveryMode;
	private Address deliveryAddress;
	
	public UserPreferencePostRequest() {
	}

	/**
	 * @param userId
	 * @param defaultSalesChannel
	 * @param deliveryMode
	 * @param deliveryAddress
	 */
	public UserPreferencePostRequest(String userId, Integer defaultSalesChannel, DeliveryMode deliveryMode,
			Address deliveryAddress) {
		super();
		this.userId = userId;
		this.defaultSalesChannel = defaultSalesChannel;
		this.deliveryMode = deliveryMode;
		this.deliveryAddress = deliveryAddress;
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
		builder.append("UserPreferencePostRequest [userId=");
		builder.append(userId);
		builder.append(", defaultSalesChannel=");
		builder.append(defaultSalesChannel);
		builder.append(", deliveryMode=");
		builder.append(deliveryMode);
		builder.append(", deliveryAddress=");
		builder.append(deliveryAddress);
		builder.append("]");
		return builder.toString();
	}

	

}
