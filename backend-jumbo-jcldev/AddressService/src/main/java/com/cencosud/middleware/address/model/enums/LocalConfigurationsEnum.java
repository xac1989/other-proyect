package com.cencosud.middleware.address.model.enums;

public enum LocalConfigurationsEnum {

	NEARBY_STORE("NearbyStoreLocalNotification",true);
	
	private String id;
	private boolean value;
	
	private LocalConfigurationsEnum(String id, boolean value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	
	
}
