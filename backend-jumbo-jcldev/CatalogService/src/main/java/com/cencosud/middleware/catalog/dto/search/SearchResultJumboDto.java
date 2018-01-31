package com.cencosud.middleware.catalog.dto.search;

import java.util.List;

import com.cencosud.middleware.catalog.model.SearchInfo;

public class SearchResultJumboDto extends SearchResultDto {

	private List<SearchProductJumboDto> products;
	private SearchInfo info;

	public List<SearchProductJumboDto> getProducts() {
		return products;
	}

	public void setProducts(List<SearchProductJumboDto> products) {
		this.products = products;
	}

	public SearchInfo getInfo() {
		return info;
	}

	public void setInfo(SearchInfo info) {
		this.info = info;
	}
}
