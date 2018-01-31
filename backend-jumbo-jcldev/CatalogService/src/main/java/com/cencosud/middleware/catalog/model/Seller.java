package com.cencosud.middleware.catalog.model;

public class Seller{
	private CommertialOffer commertialOffer;

	
	public Seller() {
		super();
	}
	
	public Seller(CommertialOffer commertialOffer) {
		super();
		this.commertialOffer = commertialOffer;
	}

	public CommertialOffer getCommertialOffer() {
		return commertialOffer;
	}

	public void setCommertialOffer(CommertialOffer commertialOffer) {
		this.commertialOffer = commertialOffer;
	}
	
}