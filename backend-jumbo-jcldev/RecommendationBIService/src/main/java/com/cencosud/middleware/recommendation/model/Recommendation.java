package com.cencosud.middleware.recommendation.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.cencosud.middleware.recommendation.client.ChaordicProduct;
import com.cencosud.middleware.recommendation.client.ChaordicRecommendation;

public class Recommendation {

	@Id
	private String id; // User email
	private List<Product> products;
	private Product starredProduct;
	
	public Recommendation(){
		
	}
	
	public Recommendation(ChaordicRecommendation chaordicRecommendation, String serverPath){
		this.id = chaordicRecommendation.getId();
		if( chaordicRecommendation.getProducts() == null ){
			chaordicRecommendation.setProducts(new ArrayList<ChaordicProduct>());
		}
		List<Product> products = new ArrayList<Product>(chaordicRecommendation.getProducts().size());
		for (ChaordicProduct chaProduct : chaordicRecommendation.getProducts()){
			Product product = new Product();
			product.setProductName( chaProduct.getProductName() );
			product.setImage( chaProduct.getImage() );
			product.setPrice( chaProduct.getPrice() );
			product.setListPrice( chaProduct.getListPrice() );
			products.add( product );
		}
		this.products = products;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getStarredProduct() {
		return starredProduct;
	}

	public void setStarredProduct(Product starredProduct) {
		this.starredProduct = starredProduct;
	}
}