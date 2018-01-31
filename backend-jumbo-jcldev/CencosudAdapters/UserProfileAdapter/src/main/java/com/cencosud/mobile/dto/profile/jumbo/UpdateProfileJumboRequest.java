package com.cencosud.mobile.dto.profile.jumbo;

public class UpdateProfileJumboRequest extends UpdateProfileGenericRequest{

	private String email;
	private String userId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
