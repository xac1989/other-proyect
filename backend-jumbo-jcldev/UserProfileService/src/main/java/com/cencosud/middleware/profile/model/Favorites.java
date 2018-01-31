package com.cencosud.middleware.profile.model;

import java.util.List;

//@Document
public class Favorites {

	List<String> categories;
	
	public Favorites() {
		super();
	}

	public Favorites(List<String> categories) {
		super();
		this.categories = categories;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
}
