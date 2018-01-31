package com.cencosud.middleware.catalog.model;

import java.util.List;

public class Item{
	private List<Seller> sellers;	
	private List<Image> images;
	

	public Item() {
		super();
	}
	
	public Item(List<Seller> sellers, List<Image> images) {
		super();
		this.sellers = sellers;
		this.images = images;
	}
	
	public List<Seller> getSellers() {
		return sellers;
	}
	public void setSellers(List<Seller> sellers) {
		this.sellers = sellers;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
}