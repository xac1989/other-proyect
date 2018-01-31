package com.cencosud.mobile.dto.profile.jumbo;

import java.util.List;
import java.util.Map;

import com.cencosud.mobile.model.profile.jumbo.Address;
import com.cencosud.mobile.model.profile.jumbo.DeliveryMode;
import com.cencosud.mobile.model.profile.jumbo.SalesChannel;

/**
 * 
 * 
 * <h1>JumboUserProfileDto</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 19, 2017
 */
public class JumboUserProfileDto extends GenericUserProfile{
	private String id;
	private String vtexId;
	private String document;
	private String documentType;
	private String firstName;
	private String lastName;
	private String fullName;
	private String phone;
	private SalesChannel defaultSalesChannel;
	private List<Map<String,String>> cookies;
	private Address deliveryAddress;
	private DeliveryMode deliveryMode;
	private String sex;
	private String birthDate;
	private boolean needsMigration;
	
	public JumboUserProfileDto() {
	}

	/**
	 * @param id
	 * @param vtexId
	 * @param document
	 * @param documentType
	 * @param firstName
	 * @param lastName
	 * @param fullName
	 * @param phone
	 * @param defaultSalesChannel
	 * @param cookies
	 * @param deliveryAddress
	 * @param deliveryMode
	 * @param sex
	 * @param birthDate
	 * @param needsMigration
	 */
	public JumboUserProfileDto(String id, String vtexId, String document, String documentType, String firstName,
			String lastName, String fullName, String phone, SalesChannel defaultSalesChannel,
			List<Map<String, String>> cookies, Address deliveryAddress, DeliveryMode deliveryMode, String sex,
			String birthDate, boolean needsMigration) {
		super();
		this.id = id;
		this.vtexId = vtexId;
		this.document = document;
		this.documentType = documentType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.phone = phone;
		this.defaultSalesChannel = defaultSalesChannel;
		this.cookies = cookies;
		this.deliveryAddress = deliveryAddress;
		this.deliveryMode = deliveryMode;
		this.sex = sex;
		this.birthDate = birthDate;
		this.needsMigration = needsMigration;
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

	/**
	 * @return the vtexId
	 */
	public String getVtexId() {
		return vtexId;
	}

	/**
	 * @param vtexId the vtexId to set
	 */
	public void setVtexId(String vtexId) {
		this.vtexId = vtexId;
	}

	/**
	 * @return the document
	 */
	public String getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(String document) {
		this.document = document;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the defaultSalesChannel
	 */
	public SalesChannel getDefaultSalesChannel() {
		return defaultSalesChannel;
	}

	/**
	 * @param defaultSalesChannel the defaultSalesChannel to set
	 */
	public void setDefaultSalesChannel(SalesChannel defaultSalesChannel) {
		this.defaultSalesChannel = defaultSalesChannel;
	}

	/**
	 * @return the cookies
	 */
	public List<Map<String, String>> getCookies() {
		return cookies;
	}

	/**
	 * @param cookies the cookies to set
	 */
	public void setCookies(List<Map<String, String>> cookies) {
		this.cookies = cookies;
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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the needsMigration
	 */
	public boolean isNeedsMigration() {
		return needsMigration;
	}

	/**
	 * @param needsMigration the needsMigration to set
	 */
	public void setNeedsMigration(boolean needsMigration) {
		this.needsMigration = needsMigration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JumboUserProfileDto [id=");
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
		builder.append(", cookies=");
		builder.append(cookies);
		builder.append(", deliveryAddress=");
		builder.append(deliveryAddress);
		builder.append(", deliveryMode=");
		builder.append(deliveryMode);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", needsMigration=");
		builder.append(needsMigration);
		builder.append("]");
		return builder.toString();
	}
	
}
