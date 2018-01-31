package com.cencosud.middleware.catalog.dto.search;

import java.util.List;

public class SearchProductWongDto {
	private String productName;
	private String productId;
	private String productReference;
	private List<SearchItemWongDto> items;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductReference() {
		return productReference;
	}

	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}

	public List<SearchItemWongDto> getItems() {
		return items;
	}

	public void setItems(List<SearchItemWongDto> items) {
		this.items = items;
	}
}
