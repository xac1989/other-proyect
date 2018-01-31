package com.cencosud.middleware.profile.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.cencosud.middleware.profile.model.Favorites;
import com.cencosud.middleware.profile.model.UserConfiguration;
import com.google.gson.Gson;

public class UserConfigurationConverter implements DynamoDBTypeConverter<String, UserConfiguration> {

	@Override
	public String convert(UserConfiguration configuration) {
		Gson gson = new Gson();
		try {
			String s = gson.toJson(configuration);
			return s;
		} catch (Exception e) {
			System.out.println("Unable to convert " + configuration.getClass() + "into JSON");
			return null;
		}
	}

	@Override
	public UserConfiguration unconvert(String s) {
		Gson gson = new Gson();
		try {
			UserConfiguration configuration = gson.fromJson(s, UserConfiguration.class);
			return configuration;
		} catch (Exception e) {
			System.out.println("Unable to unconvert " + s + "into " + Favorites.class);
			return null;
		}
	}
}
