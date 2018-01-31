package com.cencosud.middleware.catalog.dto.search;

import java.util.List;

public class SearchItemWongDto {
	private List<SearchSellerWongDto> sellers;
	private List<SearchImageWongDto> images;

	public List<SearchSellerWongDto> getSellers() {
		return sellers;
	}

	public void setSellers(List<SearchSellerWongDto> sellers) {
		this.sellers = sellers;
	}

	public List<SearchImageWongDto> getImages() {
		return images;
	}

	public void setImages(List<SearchImageWongDto> images) {
		this.images = images;
	}
}
