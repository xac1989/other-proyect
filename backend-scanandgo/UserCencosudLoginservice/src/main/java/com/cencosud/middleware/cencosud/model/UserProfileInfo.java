package com.cencosud.middleware.cencosud.model;

public class UserProfileInfo {

	private String id;
	private String displayName;
	private String profilePicture;
	private String email;
	private Boolean isDefaultProfileImage;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsDefaultProfileImage() {
		return isDefaultProfileImage;
	}
	public void setIsDefaultProfileImage(Boolean isDefaultProfileImage) {
		this.isDefaultProfileImage = isDefaultProfileImage;
	}
}
