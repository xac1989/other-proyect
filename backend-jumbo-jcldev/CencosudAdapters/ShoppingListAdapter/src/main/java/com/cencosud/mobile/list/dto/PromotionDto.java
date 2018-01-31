package com.cencosud.mobile.list.dto;

import java.math.BigDecimal;

import com.cencosud.mobile.list.model.Promotion;

public class PromotionDto {
	private String group;
    private BigDecimal promotionPrice;
    private String discountAmount;
    private String discountRate;
    private String type;
    private BigDecimal subtotalAmount;
    private BigDecimal singleProductPrice;
    private Integer quantityM;
    private Integer quantityN;
    private String title;
    private String titleColor;
    
    public PromotionDto() {}
    
    public PromotionDto(Promotion promotion, Integer prodQuantity) {
    	this.group = promotion.getGroup();
    	this.promotionPrice = new BigDecimal(promotion.getPromotionPrice());
    	this.discountAmount = promotion.getDiscountAmount();
    	this.discountRate = promotion.getDiscountRate();
    	this.type = promotion.getType();
    	this.singleProductPrice = promotion.getSingleProductPrice();
    	this.quantityM = promotion.getQuantityM();
    	this.quantityN = promotion.getQuantityN();
    	this.title = promotion.getTitle();
    	this.titleColor = promotion.getTitleColor();
    	this.subtotalAmount = (new BigDecimal(promotion.getPromotionPrice())).multiply(new BigDecimal(prodQuantity));
    }
    
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	/**
	 * @return the singleProductPrice
	 */
	public BigDecimal getSingleProductPrice() {
		return singleProductPrice;
	}

	/**
	 * @param singleProductPrice the singleProductPrice to set
	 */
	public void setSingleProductPrice(BigDecimal singleProductPrice) {
		this.singleProductPrice = singleProductPrice;
	}

	/**
	 * @return the quantityM
	 */
	public Integer getQuantityM() {
		return quantityM;
	}

	/**
	 * @param quantityM the quantityM to set
	 */
	public void setQuantityM(Integer quantityM) {
		this.quantityM = quantityM;
	}

	/**
	 * @return the quantityN
	 */
	public Integer getQuantityN() {
		return quantityN;
	}

	/**
	 * @param quantityN the quantityN to set
	 */
	public void setQuantityN(Integer quantityN) {
		this.quantityN = quantityN;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the titleColor
	 */
	public String getTitleColor() {
		return titleColor;
	}

	/**
	 * @param titleColor the titleColor to set
	 */
	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PromotionDto [group=");
		builder.append(group);
		builder.append(", promotionPrice=");
		builder.append(promotionPrice);
		builder.append(", discountAmount=");
		builder.append(discountAmount);
		builder.append(", discountRate=");
		builder.append(discountRate);
		builder.append(", type=");
		builder.append(type);
		builder.append(", subtotalAmount=");
		builder.append(subtotalAmount);
		builder.append(", singleProductPrice=");
		builder.append(singleProductPrice);
		builder.append(", quantityM=");
		builder.append(quantityM);
		builder.append(", quantityN=");
		builder.append(quantityN);
		builder.append(", title=");
		builder.append(title);
		builder.append(", titleColor=");
		builder.append(titleColor);
		builder.append("]");
		return builder.toString();
	}
	
  
}
