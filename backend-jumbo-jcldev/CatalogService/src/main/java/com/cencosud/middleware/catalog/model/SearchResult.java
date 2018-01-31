package com.cencosud.middleware.catalog.model;

import java.util.List;

import com.cencosud.middleware.catalog.client.VtexProduct;

public class SearchResult {

	private List<VtexProduct> products;
	private SearchInfo info;

	public SearchResult(List<VtexProduct> products, SearchInfo info) {
		super();
		this.products = products;
		this.info = info;
	}

	public List<VtexProduct> getProducts() {
		return products;
	}

	public void setProducts(List<VtexProduct> products) {
		this.products = products;
	}

	public SearchInfo getInfo() {
		return info;
	}

	public void setInfo(SearchInfo info) {
		this.info = info;
	}

}
