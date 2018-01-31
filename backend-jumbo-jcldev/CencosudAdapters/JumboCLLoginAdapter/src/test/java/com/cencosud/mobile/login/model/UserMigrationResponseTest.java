package com.cencosud.mobile.login.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class UserMigrationResponseTest {

	private UserMigrationResponse userMigrationResponse = new UserMigrationResponse();

	@Test
	public void getUserMigrationResponse() {

		UserMigration userMigration = new UserMigration();
		userMigration.setCode(1);
		userMigration.setEmail("test@gmail.com");
		userMigration.setMessage(null);
		userMigration.setNeedsMigration(false);
		userMigration.setSuccess(true);

		userMigrationResponse.setUserMigration(userMigration);

		Assert.assertThat(userMigrationResponse, Matchers.notNullValue());
		Assert.assertThat(userMigrationResponse.getUserMigration().getCode(), Matchers.is(1));
		Assert.assertThat(userMigrationResponse.getUserMigration().getEmail(), Matchers.is("test@gmail.com"));
		Assert.assertThat(userMigrationResponse.getUserMigration().getMessage(), Matchers.nullValue());
		Assert.assertThat(userMigrationResponse.getUserMigration().getNeedsMigration(), Matchers.is(false));
		Assert.assertThat(userMigrationResponse.getUserMigration().getSuccess(), Matchers.is(true));

	}

}
