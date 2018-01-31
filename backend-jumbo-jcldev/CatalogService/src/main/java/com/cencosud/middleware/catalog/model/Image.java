package com.cencosud.middleware.catalog.model;

public class Image{
	private String imageUrl;

	
	public Image(String imageUrl) {
		super();
		this.imageUrl = imageUrl;
	}

	public Image() {
		super();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}