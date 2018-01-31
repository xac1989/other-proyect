package com.cencosud.middleware.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public 	class AuthCookie {

	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Value")
	private String value;
	
	private String domain;
	private String path;
	private String expirationDate;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

}