package com.cencosud.middleware.catalog.dto.search;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cencosud.middleware.catalog.dto.productdetail.HighlightDto;
import com.cencosud.middleware.catalog.dto.productdetail.PromotionDto;
import com.cencosud.middleware.catalog.dto.productdetail.SkuDataDto;

public class SearchProductJumboDto {
	private String productName;
	private String productId;
	private String productReference;
	private BigDecimal price;
	private BigDecimal listPrice;
	private BigDecimal discountRate;
	private Long availableQuantity;
	private boolean available;
	private List<String> images;
	private String skuId;
	private String addToCartLink;
	private ArrayList<SkuDataDto> skuData;
	private String brand;
	private String salesChannel;
	private String sellerId;
	private List<HighlightDto> highlights;
	private Set<PromotionDto> promotions;
	private String promotionShortDescription;
	private String promotionLongDescription;

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the listPrice
	 */
	public BigDecimal getListPrice() {
		return listPrice;
	}

	/**
	 * @param listPrice
	 *            the listPrice to set
	 */
	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	/**
	 * @return the availableQuantity
	 */
	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity
	 *            the availableQuantity to set
	 */
	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	/**
	 * @return the images
	 */
	public List<String> getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(List<String> images) {
		this.images = images;
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

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getAddToCartLink() {
		return addToCartLink;
	}

	public void setAddToCartLink(String addToCartLink) {
		this.addToCartLink = addToCartLink;
	}

	public ArrayList<SkuDataDto> getSkuData() {
		return skuData;
	}

	public void setSkuData(ArrayList<SkuDataDto> skuData) {
		this.skuData = skuData;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public List<HighlightDto> getHighlights() {
		return highlights;
	}

	public void setHighlights(List<HighlightDto> highlights) {
		this.highlights = highlights;
	}

	public Set<PromotionDto> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	/**
	 * @return the promotionShortDescription
	 */
	public String getPromotionShortDescription() {
		return promotionShortDescription;
	}

	/**
	 * @param promotionShortDescription the promotionShortDescription to set
	 */
	public void setPromotionShortDescription(String promotionShortDescription) {
		this.promotionShortDescription = promotionShortDescription;
	}

	/**
	 * @return the promotionLongDescription
	 */
	public String getPromotionLongDescription() {
		return promotionLongDescription;
	}

	/**
	 * @param promotionLongDescription the promotionLongDescription to set
	 */
	public void setPromotionLongDescription(String promotionLongDescription) {
		this.promotionLongDescription = promotionLongDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchProductJumboDto [productName=");
		builder.append(productName);
		builder.append(", productId=");
		builder.append(productId);
		builder.append(", productReference=");
		builder.append(productReference);
		builder.append(", price=");
		builder.append(price);
		builder.append(", listPrice=");
		builder.append(listPrice);
		builder.append(", discountRate=");
		builder.append(discountRate);
		builder.append(", availableQuantity=");
		builder.append(availableQuantity);
		builder.append(", available=");
		builder.append(available);
		builder.append(", images=");
		builder.append(images);
		builder.append(", skuId=");
		builder.append(skuId);
		builder.append(", addToCartLink=");
		builder.append(addToCartLink);
		builder.append(", skuData=");
		builder.append(skuData);
		builder.append(", brand=");
		builder.append(brand);
		builder.append(", salesChannel=");
		builder.append(salesChannel);
		builder.append(", sellerId=");
		builder.append(sellerId);
		builder.append(", highlights=");
		builder.append(highlights);
		builder.append(", promotions=");
		builder.append(promotions);
		builder.append(", promotionShortDescription=");
		builder.append(promotionShortDescription);
		builder.append(", promotionLongDescription=");
		builder.append(promotionLongDescription);
		builder.append("]");
		return builder.toString();
	}
	
}
