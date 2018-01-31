package com.cencosud.middleware.catalog.dto.productdetail;

import org.json.JSONObject;

public class ProductDto {
	private String productName;
	private String productId;
	private String description;
	private String productReference;
	private String brand;
	
	

	public ProductDto() {
		productName = "";
		productId = "";
		description = "";
		productReference = "";
		brand = "";
	}

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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setJsonObject(JSONObject jsonObject) {
	}

}
