package com.cencosud.mobile.login.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class UserMigrationTest {

	private static final Logger LOG = LoggerFactory.getLogger(UserMigrationTest.class);
	UserMigration userMigration = new UserMigration();

	@Test
	public void getUserMigration() {
		int code = 1;
		Boolean needsMigration = false;
		String message = "message";
		boolean success = true;
		String email = "test@gmail.com";
		userMigration.setCode(code);
		userMigration.setNeedsMigration(needsMigration);
		userMigration.setMessage(message);
		userMigration.setSuccess(success);
		userMigration.setEmail(email);
		Assert.assertThat(userMigration.getCode(), Matchers.equalTo(code));
		Assert.assertThat(userMigration.getNeedsMigration(), Matchers.equalTo(needsMigration));
		Assert.assertThat(userMigration.getMessage(), Matchers.equalTo(message));
		Assert.assertThat(userMigration.getSuccess(), Matchers.equalTo(success));
		Assert.assertThat(userMigration.getEmail(), Matchers.equalTo(email));
		LOG.info("UserMigration : {}", userMigration);

	}
}
