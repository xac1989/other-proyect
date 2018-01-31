package com.cencosud.middleware.category.model;

public class Configuration {

	private String id;
	private boolean enabled;
	
	public Configuration() {
		super();
	}
	
	public Configuration(String id, boolean enabled) {
		super();
		this.id = id;
		this.enabled = enabled;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
