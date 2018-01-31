package com.cencosud.mobile.model.profile.wong;

import java.util.Date;
import java.util.Objects;

import com.cencosud.mobile.model.category.Favorites;
import com.cencosud.mobile.model.category.UserConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileUpdateRequest {

	private Favorites favorites = null;
	private String id = null;
	private UserConfiguration configurations = null;
	private String document;
	private String fullName;
	private Date birthDate;
    private String sex;
    private String phone;
    
    	
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public ProfileUpdateRequest favorites(Favorites favorites) {
		this.favorites = favorites;
		return this;
	}

	@JsonProperty("favorites")
	public Favorites getFavorites() {
		return favorites;
	}

	public void setFavorites(Favorites favorites) {
		this.favorites = favorites;
	}

	public ProfileUpdateRequest id(String id) {
		this.id = id;
		return this;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserConfiguration getConfigurations() {
		return configurations;
	}

	public void setConfigurations(UserConfiguration configurations) {
		this.configurations = configurations;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProfileUpdateRequest profileUpdateRequest = (ProfileUpdateRequest) o;
		return Objects.equals(favorites, profileUpdateRequest.favorites)
				&& Objects.equals(id, profileUpdateRequest.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(favorites, id, configurations);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProfileUpdateRequest {\n");

		sb.append("    favorites: ").append(toIndentedString(favorites)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    document: ").append(toIndentedString(document)).append("\n");
		sb.append("    fullname: ").append(toIndentedString(fullName)).append("\n");
		sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
		sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
		sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
		sb.append("    configuration: ").append(toIndentedString(configurations)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
