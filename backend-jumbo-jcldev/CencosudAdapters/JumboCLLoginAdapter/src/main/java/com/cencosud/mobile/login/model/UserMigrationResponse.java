package com.cencosud.mobile.login.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserMigrationResponse {

	private UserMigration userMigration;

	public UserMigration getUserMigration() {
		return userMigration;
	}

	public void setUserMigration(UserMigration userMigration) {
		this.userMigration = userMigration;
	}

}
