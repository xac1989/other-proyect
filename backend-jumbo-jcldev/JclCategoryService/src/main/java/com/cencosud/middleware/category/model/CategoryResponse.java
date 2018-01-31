package com.cencosud.middleware.category.model;

import java.util.List;

public class CategoryResponse {

	private Deals deals;
	private List<Category> categories;
	
	public Deals getDeals() {
		return deals;
	}
	public void setDeals(Deals deals) {
		this.deals = deals;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
