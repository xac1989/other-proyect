package com.cencosud.middleware.login.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLogin {

	@JsonProperty("authStatus")
	private String description;
	private String userId;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private AuthCookie authCookie;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private AccountAuthCookie accountAuthCookie;
	
	private String expirationDate;
	private String path;
	private String domain;

	public UserLogin() {
	}

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

	public AuthCookie getAuthCookie() {
		return authCookie;
	}

	public void setAuthCookie(AuthCookie authCookie) {
		this.authCookie = authCookie;
	}

	public AccountAuthCookie getAccountAuthCookie() {
		return accountAuthCookie;
	}

	public void setAccountAuthCookie(AccountAuthCookie accountAuthCookie) {
		this.accountAuthCookie = accountAuthCookie;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "{description:" + this.getDescription() + ", userId : " + this.userId + "}";
	}

}
