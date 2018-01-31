package com.cencosud.middleware.profile.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.cencosud.middleware.profile.model.Favorites;
import com.google.gson.Gson;

public class FavoritesConverter implements DynamoDBTypeConverter<String, Favorites> {

	@Override
	public String convert(Favorites favorites) {
		Gson gson = new Gson();
		try {
			String s = gson.toJson(favorites);
			return s;
		} catch (Exception e) {
			System.out.println("Unable to convert " + favorites.getClass() + "into JSON");
			return null;
		}
	}

	@Override
	public Favorites unconvert(String s) {
		Gson gson = new Gson();
		try {
			Favorites favorites = gson.fromJson(s, Favorites.class);
			return favorites;
		} catch (Exception e) {
			System.out.println("Unable to unconvert " + s + "into " + Favorites.class);
			return null;
		}
	}
}