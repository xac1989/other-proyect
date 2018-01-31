package com.cencosud.middleware.profile.dto;

import java.util.Date;

import com.cencosud.middleware.profile.model.Address;
import com.cencosud.middleware.profile.model.DeliveryMode;
import com.cencosud.middleware.profile.model.JumboUserProfile;
import com.fasterxml.jackson.annotation.JsonFormat;

public class JumboUserProfileDto extends UserProfileDto {

	public static final String DOCUMENT_TYPE_RUT = "rutCHL"; 
	public static final String DOCUMENT_TYPE_RUT_VALUE = "RUT";

	private String vtexId;
	private String firstName;
	private String lastName;
	private Integer defaultSalesChannel;
	private String sex;
	private Date birthDate;
	private DeliveryMode deliveryMode;
	private Address deliveryAddress;

	public JumboUserProfileDto() {
	}

	public JumboUserProfileDto(JumboUserProfile jumboUserProfile) {
		super.id = jumboUserProfile.getEmail();
		super.document = jumboUserProfile.getDocument();
		super.documentType = DOCUMENT_TYPE_RUT.equals(jumboUserProfile.getDocumentType()) ? DOCUMENT_TYPE_RUT_VALUE : jumboUserProfile.getDocumentType();
		super.fullName = String.format("%s %s", jumboUserProfile.getFirstName(), jumboUserProfile.getLastName());
		super.phone = jumboUserProfile.getHomePhone();
		this.vtexId = jumboUserProfile.getId();
		this.vtexId = jumboUserProfile.getUserId();
		this.firstName = jumboUserProfile.getFirstName();
		this.lastName = jumboUserProfile.getLastName();
		this.defaultSalesChannel =jumboUserProfile.getDefaultSalesChannel(); 
		this.sex = jumboUserProfile.getGender();
		this.birthDate = jumboUserProfile.getBirthDate();
		this.deliveryAddress = jumboUserProfile.getDeliveryAddress();
		this.deliveryMode = jumboUserProfile.getDeliveryMode();
	}

	public String getVtexId() {
		return vtexId;
	}

	public void setVtexId(String vtexId) {
		this.vtexId = vtexId;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
		builder.append("JumboUserProfileDto [vtexId=");
		builder.append(vtexId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", defaultSalesChannel=");
		builder.append(defaultSalesChannel);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", deliveryMode=");
		builder.append(deliveryMode);
		builder.append(", deliveryAddress=");
		builder.append(deliveryAddress);
		builder.append("]");
		return builder.toString();
	}
}
