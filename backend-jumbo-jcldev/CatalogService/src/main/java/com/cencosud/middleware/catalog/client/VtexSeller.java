package com.cencosud.middleware.catalog.client;

import org.json.JSONObject;

import com.cencosud.middleware.catalog.model.Seller;

public class VtexSeller {

	
	private String sellerId;
	private String sellerName;
	private String addToCartLink;
	private boolean sellerDefault;

	private VtexCommertialOffer commertialOffer;

	
	public VtexSeller(JSONObject fromObj) {
		super();
		this.sellerId = fromObj.getString("sellerId");
		this.sellerName = fromObj.getString("sellerName");
		this.addToCartLink = fromObj.getString("addToCartLink");
		this.sellerDefault = fromObj.getBoolean("sellerDefault");
		this.commertialOffer = new VtexCommertialOffer(fromObj.getJSONObject("commertialOffer"));
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getAddToCartLink() {
		return addToCartLink;
	}

	public void setAddToCartLink(String addToCartLink) {
		this.addToCartLink = addToCartLink;
	}

	public boolean getSellerDefault() {
		return sellerDefault;
	}

	public void setSellerDefault(boolean sellerDefault) {
		this.sellerDefault = sellerDefault;
	}

	public VtexCommertialOffer getCommertialOffer() {
		return commertialOffer;
	}

	public void setCommertialOffer(VtexCommertialOffer commertialOffer) {
		this.commertialOffer = commertialOffer;
	}
	
	public Seller toModelSeller(){
		if(this.commertialOffer == null){
			return new Seller();
		}
		return new Seller(this.commertialOffer.toModelComertialOffer());
	}
	
	
}
