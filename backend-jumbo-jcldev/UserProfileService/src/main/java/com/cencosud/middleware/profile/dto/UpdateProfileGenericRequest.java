package com.cencosud.middleware.profile.dto;

import java.util.Map;

import com.cencosud.middleware.profile.model.Favorites;
import com.cencosud.middleware.profile.model.UserConfiguration;

public class UpdateProfileGenericRequest{

	private String displayName;
	private Map<String, String> attributes;
	private Favorites favorites;
	private UserConfiguration configurations;
	private String profilePicture;
	
	private String id;
	private String vtexId;
	private String document;
	private String documentType;
	private String firstName;
	private String lastName;
	private String fullName;
	private String phone;
	private String birthDate;
	private String sex;
	
	private Integer defaultSalesChannel;
	
	public UpdateProfileGenericRequest(){
		super();
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public Favorites getFavorites() {
		return favorites;
	}
	public void setFavorites(Favorites favorites) {
		this.favorites = favorites;
	}
	public UserConfiguration getConfigurations() {
		return configurations;
	}
	public void setConfigurations(UserConfiguration configurations) {
		this.configurations = configurations;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getDefaultSalesChannel() {
		return defaultSalesChannel;
	}

	public void setDefaultSalesChannel(Integer defaultSalesChannel) {
		this.defaultSalesChannel = defaultSalesChannel;
	}
	
}
