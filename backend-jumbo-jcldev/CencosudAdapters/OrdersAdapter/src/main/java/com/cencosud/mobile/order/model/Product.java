package com.cencosud.mobile.order.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Product
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	protected String skuId;
	protected String productId;
	protected String addToCartLink;
	protected String salesChannel;
	protected List<Object> skuData;
	protected String sellerId;
	

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAddToCartLink() {
		return addToCartLink;
	}

	public void setAddToCartLink(String addToCartLink) {
		this.addToCartLink = addToCartLink;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public List<Object> getSkuData() {
		return skuData;
	}

	public void setSkuData(List<Object> skuData) {
		this.skuData = skuData;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	@Override
	public String toString() {
		return "{skuId = " + this.skuId + ",productId = " + this.productId + ",addToCartLink = " + this.addToCartLink
				+ ",salesChannel = " + this.salesChannel + ",sellerId = " + this.sellerId + "}";
	}
}
