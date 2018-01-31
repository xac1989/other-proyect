package com.cencosud.middleware.login.model;

public class TwitterLoginInfo {

	private String application;
	private String accessToken;
	private String accessSecretToken;
	
	
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessSecretToken() {
		return accessSecretToken;
	}
	public void setAccessSecretToken(String accessSecretToken) {
		this.accessSecretToken = accessSecretToken;
	}
	
	
}
