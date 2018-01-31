package com.cencosud.mobile.list.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;

/**
 * Product
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Product")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class Product {

	protected String skuId;
	protected String productName;
	protected String productId;
	protected List<String> description;
	protected String productReference;
	protected String brand;
	protected BigDecimal price;
	protected BigDecimal listPrice;
	protected BigDecimal discountRate;
	protected Long availableQuantity;
	protected boolean available;
	protected String addToCartLink;
	protected List<String> images;
	protected String salesChannel;
	protected String sellerId;
	protected List<ProductSpecification> specifications;
	protected List<ProductSkuData> skuData;
	protected List<Integer> nutritionalFlags;	
	protected List<Promotion> promotions;
	protected String promotionShortDescription;
	protected String promotionLongDescription;
	
	public List<Integer> getNutritionalFlags() {
		return nutritionalFlags;
	}

	public void setNutritionalFlags(List<Integer> nutritionalFlags) {
		this.nutritionalFlags = nutritionalFlags;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
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

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public String getProductReference() {
		return productReference;
	}

	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getAddToCartLink() {
		return addToCartLink;
	}

	public void setAddToCartLink(String addToCartLink) {
		this.addToCartLink = addToCartLink;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public List<ProductSkuData> getSkuData() {
		return skuData;
	}

	public void setSkuData(List<ProductSkuData> skuData) {
		this.skuData = skuData;
	}

	public List<ProductSpecification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<ProductSpecification> specifications) {
		this.specifications = specifications;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
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
		builder.append("Product [skuId=");
		builder.append(skuId);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", productId=");
		builder.append(productId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", productReference=");
		builder.append(productReference);
		builder.append(", brand=");
		builder.append(brand);
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
		builder.append(", nutritionalFlags=");
		builder.append(nutritionalFlags);
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
