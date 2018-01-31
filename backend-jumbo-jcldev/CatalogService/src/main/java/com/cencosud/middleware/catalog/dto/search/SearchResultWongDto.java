package com.cencosud.middleware.catalog.dto.search;

import java.util.List;

import com.cencosud.middleware.catalog.model.SearchInfo;

public class SearchResultWongDto extends SearchResultDto {
	private List<SearchProductWongDto> products;
	private SearchInfo info;

	public List<SearchProductWongDto> getProducts() {
		return products;
	}

	public void setProducts(List<SearchProductWongDto> products) {
		this.products = products;
	}

	public SearchInfo getInfo() {
		return info;
	}

	public void setInfo(SearchInfo info) {
		this.info = info;
	}
}
