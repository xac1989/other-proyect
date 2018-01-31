package com.cencosud.mobile.model.profile.jumbo;

import com.cencosud.mobile.model.profile.wong.Metadata;

public class JumboUserProfileResponse {
	
	private JumboUserProfile profile;
	private Metadata metadata;
	
	public JumboUserProfile getProfile() {
		return profile;
	}
	public void setProfile(JumboUserProfile profile) {
		this.profile = profile;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	
	

}
