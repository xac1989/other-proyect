package com.cencosud.middleware.category.model;

public enum ExtensionEnum {

	EXT_SVG ("svg"),
	EXT_PNG ("png"),
	EXT_JPG ("jpg");
	
	private final String shortCode;
	  
	ExtensionEnum(String code) {
		this.shortCode = code;
	}
		  
	public String getExtensionCode() {
		return this.shortCode;
	}
}
