package com.cencosud.middleware.login.model;

public class LoggedUser {

	private String id;
	private String displayName;
	private String profilePicture;
	private String email;
	private boolean isDefaultProfileImage;

	public LoggedUser(String id, String displayName, String profilePicture, String email, boolean isDefaultProfileImage) {
		this.id = id;
		this.displayName = displayName;
		this.profilePicture = profilePicture;
		this.email = email;
		this.isDefaultProfileImage = isDefaultProfileImage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public boolean getIsDefaultProfileImage() {
		return isDefaultProfileImage;
	}

	public void setIsDefaultProfileImage(boolean isDefaultProfileImage) {
		this.isDefaultProfileImage = isDefaultProfileImage;
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
}
