package com.cencosud.middleware.recommendation.client;

import java.util.List;

public class ChaordicRecommendation {

	private String id; // User email
	private List<ChaordicProduct> products;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ChaordicProduct> getProducts() {
		return products;
	}

	public void setProducts(List<ChaordicProduct> products) {
		this.products = products;
	}
	
}
