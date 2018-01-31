package com.cencosud.mobile.list.model;

public class ProductSpecification {

	private String key;
	private String val;

	public ProductSpecification() { }

	public ProductSpecification(ProductSpecification specificationDto) {
		this.key = specificationDto.key;
		this.val = specificationDto.val;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
}
