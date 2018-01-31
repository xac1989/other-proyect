package com.cencosud.middleware.catalog.dto.productdetail;

public class SellerWongDto {
	private CommertialOfferDto commertialOffer;
	private String addToCartLink;

	public String getAddToCartLink() {
		return addToCartLink;
	}

	public void setAddToCartLink(String addToCartLink) {
		this.addToCartLink = addToCartLink;
	}

	public CommertialOfferDto getCommertialOffer() {
		return commertialOffer;
	}

	public void setCommertialOffer(CommertialOfferDto commertialOffer) {
		this.commertialOffer = commertialOffer;
	}
}
