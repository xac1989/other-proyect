package com.cencosud.middleware.recommendation.client;

import java.math.BigDecimal;
import java.util.Map;

import org.json.JSONObject;

import com.cencosud.middleware.recommendation.model.Product;

public class ChaordicProduct {

	private static final String AVAILABLE_STRING_VALUE = "available";

	private String productId;
	private String productName;
	private String image;
	private Map<String, String> images;
	private BigDecimal price;
	private BigDecimal listPrice;
	private BigDecimal discountRate;
	private boolean available;

	public ChaordicProduct(JSONObject fromObj) {
		this.productId = fromObj.getString("id");
		this.productName = fromObj.getString("name");
		this.price = fromObj.getBigDecimal("price");
		this.listPrice = fromObj.getBigDecimal("oldPrice");
		this.discountRate = calculateDiscountRate();
		this.available = AVAILABLE_STRING_VALUE.equals(fromObj.getString("status"));
		JSONObject imagesObject = fromObj.getJSONObject("images");
		if (imagesObject != null) {
			this.image = "http://" + imagesObject.getString("1000x1000");
		}
	}

	public BigDecimal calculateDiscountRate() {
		return new BigDecimal(100).subtract(price.multiply(new BigDecimal(100)).divide(listPrice, 3))
				.setScale(0, BigDecimal.ROUND_FLOOR);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Map<String, String> getImages() {
		return images;
	}

	public void setImages(Map<String, String> images) {
		this.images = images;
	}

	public Product toModelProduct() {
		return new Product(this.productId, this.productName, this.image, this.price, this.listPrice,
				this.discountRate, this.available);
	}
}
