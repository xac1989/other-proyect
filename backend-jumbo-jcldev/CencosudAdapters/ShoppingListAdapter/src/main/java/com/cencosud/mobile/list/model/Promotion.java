package com.cencosud.mobile.list.model;

import java.math.BigDecimal;

public class Promotion {

	private String group;
    private String promotionPrice;
    private String discountAmount;
    private String discountRate;
    private String type;
    private BigDecimal singleProductPrice;
    private Integer quantityM;
    private Integer quantityN;
    private String title;
    private String titleColor;
    
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
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
		builder.append("Promotion [group=");
		builder.append(group);
		builder.append(", promotionPrice=");
		builder.append(promotionPrice);
		builder.append(", discountAmount=");
		builder.append(discountAmount);
		builder.append(", discountRate=");
		builder.append(discountRate);
		builder.append(", type=");
		builder.append(type);
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
