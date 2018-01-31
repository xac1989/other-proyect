package com.cencosud.mobile.model.category;

import com.cencosud.mobile.model.profile.wong.Metadata;

public class UserProfileResponse {
	
	private UserProfile profile;
	private Metadata metadata;
	
	public UserProfile getProfile() {
		return profile;
	}
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	
	

}
