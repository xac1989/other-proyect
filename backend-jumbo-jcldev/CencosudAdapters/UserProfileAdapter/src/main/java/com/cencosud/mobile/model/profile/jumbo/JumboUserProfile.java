package com.cencosud.mobile.model.profile.jumbo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class JumboUserProfile {

	private String id;
	private String vtexId;
	private String document;
	private String documentType;
	private String firstName;
	private String lastName;
	private String fullName;
	private String phone;
	private Integer defaultSalesChannel;
	private DeliveryMode deliveryMode;
	private Address deliveryAddress;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVtexId() {
		return vtexId;
	}

	public void setVtexId(String vtexId) {
		this.vtexId = vtexId;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		builder.append("JumboUserProfile [id=");
		builder.append(id);
		builder.append(", vtexId=");
		builder.append(vtexId);
		builder.append(", document=");
		builder.append(document);
		builder.append(", documentType=");
		builder.append(documentType);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", phone=");
		builder.append(phone);
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
