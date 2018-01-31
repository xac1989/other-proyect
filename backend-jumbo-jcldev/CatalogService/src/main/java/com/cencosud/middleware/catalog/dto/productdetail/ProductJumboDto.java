package com.cencosud.middleware.catalog.dto.productdetail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ProductJumboDto extends ProductDto {
	private BigDecimal price;
	private BigDecimal listPrice;
	private BigDecimal discountRate;
	private Long availableQuantity;
	private boolean available;
	private String addToCartLink;
	private List<String> images;
	private String salesChannel;
	private String sellerId;
	private List<SpecificationDto> specifications;
	private List<SkuDataDto> skuData;
	private String skuId;
	private List<Integer> nutritionalFlags;
	private List<HighlightDto> highlights;
	private String certification;
	private Set<PromotionDto> promotions;
	private String promotionShortDescription;
	private String promotionLongDescription;
	
	public List<Integer> getNutritionalFlags() {
		return nutritionalFlags;
	}

	public void setNutritionalFlags(List<Integer> nutritionalFlags) {
		this.nutritionalFlags = nutritionalFlags;
	}

	public List<SkuDataDto> getSkuData() {
		return skuData;
	}

	public void setSkuData(List<SkuDataDto> skuData) {
		this.skuData = skuData;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

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
	 * @return the addToCartLink
	 */
	public String getAddToCartLink() {
		return addToCartLink;
	}

	/**
	 * @param addToCartLink
	 *            the addToCartLink to set
	 */
	public void setAddToCartLink(String addToCartLink) {
		this.addToCartLink = addToCartLink;
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

	public List<SpecificationDto> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<SpecificationDto> specifications) {
		this.specifications = specifications;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
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
		builder.append("ProductJumboDto [price=");
		builder.append(price);
		builder.append(", listPrice=");
		builder.append(listPrice);
		builder.append(", discountRate=");
		builder.append(discountRate);
		builder.append(", availableQuantity=");
		builder.append(availableQuantity);
		builder.append(", available=");
		builder.append(available);
		builder.append(", addToCartLink=");
		builder.append(addToCartLink);
		builder.append(", images=");
		builder.append(images);
		builder.append(", salesChannel=");
		builder.append(salesChannel);
		builder.append(", sellerId=");
		builder.append(sellerId);
		builder.append(", specifications=");
		builder.append(specifications);
		builder.append(", skuData=");
		builder.append(skuData);
		builder.append(", skuId=");
		builder.append(skuId);
		builder.append(", nutritionalFlags=");
		builder.append(nutritionalFlags);
		builder.append(", highlights=");
		builder.append(highlights);
		builder.append(", certification=");
		builder.append(certification);
		builder.append(", promotions=");
		builder.append(promotions);
		builder.append(", promotionShortDescription=");
		builder.append(promotionShortDescription);
		builder.append(", promotionLongDescription=");
		builder.append(promotionLongDescription);
		builder.append("]");
		return builder.toString();
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public Set<PromotionDto> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<PromotionDto> promotions) {
		this.promotions = promotions;
	}


	
}
