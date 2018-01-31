package com.cencosud.middleware.list.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListCreateResponse {

	@JsonProperty("Id")
	private String id;
	@JsonProperty("Href")
	private String href;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
}
