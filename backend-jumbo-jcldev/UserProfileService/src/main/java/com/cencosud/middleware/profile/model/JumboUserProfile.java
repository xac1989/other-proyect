package com.cencosud.middleware.profile.model;

import java.util.Date;

import com.cencosud.middleware.profile.dto.UpdateProfileGenericRequest;
import com.cencosud.middleware.profile.utils.StringUtils;

public class JumboUserProfile extends UserProfile {

	private String firstName;
	private String lastName;
	private String email;
	private String homePhone;
	private String userId;
	private Integer defaultSalesChannel;
	private DeliveryMode deliveryMode;
	private Address deliveryAddress;
	private String gender;
	private Date birthDate;
	
	public JumboUserProfile(){
		super();
	}
	
	public JumboUserProfile(UpdateProfileGenericRequest genericProfile){
		this.id = genericProfile.getId();
		this.document = genericProfile.getDocument();
		this.documentType = genericProfile.getDocumentType();
		this.firstName = genericProfile.getFirstName();
		this.lastName = genericProfile.getLastName();
		this.email = genericProfile.getId();
		this.homePhone = genericProfile.getPhone();
		this.userId = genericProfile.getVtexId();
		this.defaultSalesChannel = genericProfile.getDefaultSalesChannel();
		this.gender = genericProfile.getSex();
		this.birthDate = StringUtils.convertToDate(genericProfile.getBirthDate());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

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
		builder.append("JumboUserProfile [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", homePhone=");
		builder.append(homePhone);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", defaultSalesChannel=");
		builder.append(defaultSalesChannel);
		builder.append(", deliveryMode=");
		builder.append(deliveryMode);
		builder.append(", deliveryAddress=");
		builder.append(deliveryAddress);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append("]");
		return builder.toString();
	}

}
