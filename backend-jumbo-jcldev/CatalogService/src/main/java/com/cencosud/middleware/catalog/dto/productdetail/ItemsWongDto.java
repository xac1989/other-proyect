package com.cencosud.middleware.catalog.dto.productdetail;

import java.util.List;

public class ItemsWongDto {
	private List<SellerWongDto> sellers;
	private String itemId;
	private List<ImageWongDto> images;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<SellerWongDto> getSellers() {
		return sellers;
	}

	public void setSellers(List<SellerWongDto> sellers) {
		this.sellers = sellers;
	}

	public List<ImageWongDto> getImages() {
		return images;
	}

	public void setImages(List<ImageWongDto> images) {
		this.images = images;
	}

}
