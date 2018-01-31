package com.cencosud.mobile.model;

public class UserProfileResponse {
	UserProfile profile;
	Object metadata;
	
	public UserProfileResponse() {
		super();
	}
	public UserProfileResponse(UserProfile profile, Object metadata) {
		super();
		this.profile = profile;
		this.metadata = metadata;
	}
	public UserProfile getProfile() {
		return profile;
	}
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	public Object getMetadata() {
		return metadata;
	}
	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}
	
	
}
