package com.cencosud.middleware.profile.dto;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cencosud.middleware.profile.model.WongUserProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class WongUserProfileDto extends UserProfileDto {

//	private String id;
	private String displayName;
//	private String document;
//	private String documentType;
//	private String fullName;
	private Date birthDate;
	private String sex;
//	private String phone;
	private Map<String, String> attributes;
	private FavoritesDto favorites;
	private UserConfigurationDto configurations;

	public WongUserProfileDto() {
	}

	public WongUserProfileDto(WongUserProfile userProfile) {
		if (userProfile != null) {
			super.id = userProfile.getId();
			this.displayName = userProfile.getDisplayName();
			this.document = userProfile.getDocument();
			this.documentType = userProfile.getDocumentType();
			this.fullName = userProfile.getFullName();
			this.birthDate = userProfile.getBirthDate();
			this.sex = userProfile.getSex();
			this.phone = userProfile.getPhone();
			if (userProfile.getAttributes() != null) {
				this.attributes = new LinkedHashMap<>(userProfile.getAttributes());
			}
			if (userProfile.getFavorites() != null) {
				this.favorites = new FavoritesDto(userProfile.getFavorites());
			}
			if (userProfile.getConfigurations() != null) {
				this.configurations = new UserConfigurationDto(userProfile.getConfigurations());
			}
		}
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

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

	public FavoritesDto getFavorites() {
		return favorites;
	}

	public void setFavorites(FavoritesDto favorites) {
		this.favorites = favorites;
	}

	public UserConfigurationDto getConfigurations() {
		return configurations;
	}

	public void setConfigurations(UserConfigurationDto configurations) {
		this.configurations = configurations;
	}

//	public String getDocument() {
//		return document;
//	}
//
//	public void setDocument(String document) {
//		this.document = document;
//	}
//
//	public String getDocumentType() {
//		return documentType;
//	}
//
//	public void setDocumentType(String documentType) {
//		this.documentType = documentType;
//	}
//
//	public String getFullName() {
//		return fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}

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

//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
}
