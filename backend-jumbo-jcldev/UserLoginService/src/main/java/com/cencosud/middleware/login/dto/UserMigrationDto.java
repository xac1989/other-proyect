package com.cencosud.middleware.login.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserMigrationDto {
	private int code;
	private String message;
	private boolean success;
	@JsonInclude(Include.NON_NULL)
	private String email;
	@JsonInclude(Include.NON_NULL)
	private Boolean needsMigration;

	public UserMigrationDto(int code, String message, boolean success, String email, Boolean needsMigration) {
		this.code = code;
		this.message = message;
		this.success = success;
		this.email = email;
		this.needsMigration = needsMigration;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public Boolean getNeedsMigration() {
		return needsMigration;
	}

	public void setNeedsMigration(Boolean needsMigration) {
		this.needsMigration = needsMigration;
	}

}