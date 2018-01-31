package com.cencosud.middleware.category.model;

import com.cencosud.mobile.profile.model.Metadata;

public class CategoryResponse {
	private Category category;
	private Metadata metadata;

	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	
	
}
