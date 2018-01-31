package com.cencosud.middleware.profile.converter;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;

public class AttributesConverter implements DynamoDBTypeConverter<String, Map> {

	@Override
	public String convert(Map map) {
		Gson gson = new Gson();
		try {
			String s = gson.toJson(map);
			return s;
		} catch(Exception e) {
			System.out.println("Unable to convert " + map.getClass() + "into JSON");
			return null;
		}
	}

	@Override
	public Map unconvert(String s) {
		Gson gson = new Gson();
		try {
			Map map = gson.fromJson(s, Map.class);
			return map;
		} catch (Exception e) {
			System.out.println("Unable to unconvert " + s + "into " + Map.class);
			return null;
		}
	}
}