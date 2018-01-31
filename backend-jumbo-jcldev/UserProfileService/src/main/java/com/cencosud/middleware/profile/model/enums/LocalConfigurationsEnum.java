package com.cencosud.middleware.profile.model.enums;

public enum LocalConfigurationsEnum {

	NEARBY_STORE("NearbyStoreLocalNotification",true);
	
	private final String id;
	private final boolean value;
	
	LocalConfigurationsEnum(String id, boolean value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public boolean getValue() {
		return value;
	}

	
}
