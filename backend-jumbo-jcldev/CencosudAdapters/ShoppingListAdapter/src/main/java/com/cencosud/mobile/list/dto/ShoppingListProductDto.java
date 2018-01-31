package com.cencosud.mobile.list.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cencosud.mobile.list.model.ProductSkuData;
import com.cencosud.mobile.list.model.Promotion;
import com.cencosud.mobile.list.model.ShoppingListProduct;
import com.cencosud.mobile.list.util.ListUtil;

public class ShoppingListProductDto {

	private String skuId;
	private int quantity;
	private BigDecimal priceSubtotal;
	private BigDecimal listPriceSubtotal;
	private String productName;
	private String productId;
	private List<String> description;
	private String productReference;
	private String brand;
	private BigDecimal price;
	private BigDecimal listPrice;
	private BigDecimal discountRate;
	private Long availableQuantity;
	private boolean available;
	private String addToCartLink;
	private List<String> images;
	private String salesChannel;
	private String sellerId;
	private List<ProductSkuDataDto> skuData;
	protected List<PromotionDto> promotions;
	private String promotionShortDescription;
	private String promotionLongDescription;
	
	
	public List<PromotionDto> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	public ShoppingListProductDto() { 
		
	}

	public ShoppingListProductDto(ShoppingListProduct shoppingListProduct) {
		if (shoppingListProduct != null) {
			this.skuId = shoppingListProduct.getSkuId();
			this.productName = shoppingListProduct.getProductName();
			this.productId = shoppingListProduct.getProductId();
			this.description = ListUtil.copy(shoppingListProduct.getDescription());
			this.productReference = shoppingListProduct.getProductReference();
			this.brand = shoppingListProduct.getBrand();
			this.price = shoppingListProduct.getPrice();
			this.listPrice = shoppingListProduct.getListPrice();
			this.discountRate = shoppingListProduct.getDiscountRate();
			this.availableQuantity = shoppingListProduct.getAvailableQuantity();
			this.available = shoppingListProduct.isAvailable();
			this.addToCartLink = shoppingListProduct.getAddToCartLink();
			this.images = ListUtil.copy(shoppingListProduct.getImages());
			this.salesChannel = shoppingListProduct.getSalesChannel();
			this.sellerId = shoppingListProduct.getSellerId();
			if (shoppingListProduct.getSkuData() != null) {
				this.skuData = new ArrayList<>();
				for (ProductSkuData skuDataDto : shoppingListProduct.getSkuData()) {
					this.skuData.add(new ProductSkuDataDto(skuDataDto));
				}
			} else {
				this.skuData = new ArrayList<>();
			}
			quantity = shoppingListProduct.getSkuQuantity().getQuantity();
			priceSubtotal = price.multiply(new BigDecimal(quantity));
			listPriceSubtotal = listPrice.multiply(new BigDecimal(quantity));
			
			this.promotions = new ArrayList<>();
			for (Promotion promotion : shoppingListProduct.getPromotions()){
				this.promotions.add(new PromotionDto(promotion, shoppingListProduct.getSkuQuantity().getQuantity()));
			}
			this.promotionLongDescription = shoppingListProduct.getPromotionLongDescription();
			this.promotionShortDescription = shoppingListProduct.getPromotionShortDescription();
		}
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPriceSubtotal() {
		return priceSubtotal;
	}

	public void setPriceSubtotal(BigDecimal priceSubtotal) {
		this.priceSubtotal = priceSubtotal;
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

	public List<ProductSkuDataDto> getSkuData() {
		return skuData;
	}

	public void setSkuData(List<ProductSkuDataDto> skuData) {
		this.skuData = skuData;
	}

	public BigDecimal getListPriceSubtotal() {
		return listPriceSubtotal;
	}

	public void setListPriceSubtotal(BigDecimal listPriceSubtotal) {
		this.listPriceSubtotal = listPriceSubtotal;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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
		builder.append("ShoppingListProductDto [skuId=");
		builder.append(skuId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", priceSubtotal=");
		builder.append(priceSubtotal);
		builder.append(", listPriceSubtotal=");
		builder.append(listPriceSubtotal);
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
		builder.append(", skuData=");
		builder.append(skuData);
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
