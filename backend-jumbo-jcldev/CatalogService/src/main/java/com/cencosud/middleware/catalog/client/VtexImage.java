package com.cencosud.middleware.catalog.client;

import org.json.JSONObject;

import com.cencosud.middleware.catalog.model.Image;

public class VtexImage {

	private String imageId;
	private String imageLabel;
	private String imageTag;
	private String imageUrl;
	private String imageText;
	
	public VtexImage(JSONObject fromObj) {
		super();
		this.imageId =  fromObj.getString("imageId");
		this.imageLabel = fromObj.optString("imageLabel");
		this.imageTag = fromObj.getString("imageTag");
		this.imageUrl = fromObj.getString("imageUrl");
		this.imageText = fromObj.getString("imageText");
	}
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageLabel() {
		return imageLabel;
	}
	public void setImageLabel(String imageLabel) {
		this.imageLabel = imageLabel;
	}
	public String getImageTag() {
		return imageTag;
	}
	public void setImageTag(String imageTag) {
		this.imageTag = imageTag;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageText() {
		return imageText;
	}
	public void setImageText(String imageText) {
		this.imageText = imageText;
	}

	public Image toModelImage() {
		return new Image(imageUrl);
	}
	
	
}
