package com.cencosud.mobile.list.model.enums;

public enum RequestProtocolEnum {

	
	GET(1, "Get"),
	POST(2, "Post"),
	PUT(3, "Put"),
	DELETE(4, "Delete");
	
	
	private int id;
	private String name;
	
	private RequestProtocolEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
}
