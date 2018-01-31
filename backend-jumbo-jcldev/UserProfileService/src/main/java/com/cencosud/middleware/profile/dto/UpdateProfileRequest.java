package com.cencosud.middleware.profile.dto;

import java.util.Map;

import com.cencosud.middleware.profile.model.Favorites;
import com.cencosud.middleware.profile.model.UserConfiguration;

public class UpdateProfileRequest extends UpdateProfileJumboRequest{

	private String displayName;
	private Map<String, String> attributes;
	private Favorites favorites;
	private UserConfiguration configurations;
	private String profilePicture;
	
	public UpdateProfileRequest(){
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
	
}
