package com.cencosud.mobile.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

	private String description;
	private String userId;
	private List<Map<String,String>> cookies;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Map<String,String>> getCookies() {
		return cookies;
	}

	public void setCookies(List<Map<String,String>> cookies) {
		this.cookies = cookies;
	}

	@Override
	public String toString() {
		return "{ description = " + this.description + ", userId = " + this.userId + ", cookies = "+cookies+"}";
	}

}
