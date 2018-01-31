package com.cencosud.middleware.catalog.model;

import java.util.Map;

public class PromotionInfo {
	private Map<String, Promotion> value;
	private String id;
	
	public Map<String, Promotion> getValue() {
		return value;
	}
	public void setValue(Map<String, Promotion> value) {
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
