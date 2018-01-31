package com.cencosud.middleware.profile.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cencosud.middleware.profile.dto.UpdateProfileGenericRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "user_profile")
public class WongUserProfile extends UserProfile {

	private String displayName;
	private String fullName;
	private Date birthDate;
	private String sex;
	private String phone;
	@JsonIgnore
	private Date creationDate;
	@JsonIgnore
	private boolean modified;
	private Map<String, String> attributes;
	private Favorites favorites;
	private UserConfiguration configurations;

	public WongUserProfile() {
	}

	public WongUserProfile(String id, String displayName, Map<String, String> attributes, Favorites favorites) {
		this.id = id;
		this.displayName = displayName;
		this.attributes = attributes;
		this.favorites = favorites;
	}

	public WongUserProfile(String id, String displayName, String document, String documentType,
			Map<String, String> attributes, Favorites favorites, UserConfiguration configurations) {
		this.id = id;
		this.displayName = displayName;
		this.document = document;
		this.documentType = documentType;
		this.attributes = attributes;
		this.favorites = favorites;
		this.configurations = configurations;
	}
	
	public WongUserProfile(UpdateProfileGenericRequest genericRequest){
		this.id = genericRequest.getId();
		this.document = genericRequest.getDocument();
		this.documentType = genericRequest.getDocumentType();
		this.displayName = genericRequest.getDisplayName();
		this.fullName = genericRequest.getFullName();
		this.birthDate = com.cencosud.middleware.profile.utils.StringUtils.convertToDate(genericRequest.getBirthDate());
		this.sex = genericRequest.getSex();
		this.phone = genericRequest.getPhone();
		this.attributes = genericRequest.getAttributes();
		this.favorites = genericRequest.getFavorites();
		this.configurations = genericRequest.getConfigurations();
	}

	/*
	 * Overriding getter and setter for id field because it needs to be decorated with @Id for spring data and
	 * mongodb use.
	 */
	@Override
	@Id
	public String getId() {
		return id;
	}

	@Override
	@Id
	public void setId(String id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean isModified) {
		this.modified = isModified;
	}
}
