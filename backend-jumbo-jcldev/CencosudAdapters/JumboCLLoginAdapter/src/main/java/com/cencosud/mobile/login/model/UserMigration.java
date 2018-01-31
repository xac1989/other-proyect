package com.cencosud.mobile.login.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserMigration {

	private int code;
	private String message;
	private boolean success;
	private String email;
	private boolean needsMigration;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean getNeedsMigration() {
		return needsMigration;
	}

	public void setNeedsMigration(boolean needsMigration) {
		this.needsMigration = needsMigration;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "{code = " + this.code + ", email =" + this.email + "}";
	}
}
