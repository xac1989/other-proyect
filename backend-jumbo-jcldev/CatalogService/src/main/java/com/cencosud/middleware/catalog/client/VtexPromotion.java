package com.cencosud.middleware.catalog.client;

import java.math.BigDecimal;
import java.util.Date;

public class VtexPromotion implements Comparable<VtexPromotion>{
	
	private String group;
    private BigDecimal promotionPrice;
    private BigDecimal discountAmount;
    private BigDecimal discountRate;
    private String type;
    private BigDecimal singleProductPrice;
    private Integer quantityM;
    private Integer quantityN; 
    private String title;
    private String titleColor;
    private VtexHighlight highlight;
    private Date expirationDate;
    private String description;
    private String shortDescription;
    
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getQuantityM() {
		return quantityM;
	}

	public void setQuantityM(Integer quantityM) {
		this.quantityM = quantityM;
	}

	public Integer getQuantityN() {
		return quantityN;
	}

	public void setQuantityN(Integer quantityN) {
		this.quantityN = quantityN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public VtexHighlight getHighlight() {
		return highlight;
	}

	public void setHighlight(VtexHighlight highlight) {
		this.highlight = highlight;
	}

	public BigDecimal getSingleProductPrice() {
		return singleProductPrice;
	}

	public void setSingleProductPrice(BigDecimal singleProductPrice) {
		this.singleProductPrice = singleProductPrice;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/*
	 * @returns: 1 this best, 0 equals, -1 this worse 
	 */
	@Override
	public int compareTo(VtexPromotion o) {
	
		int productPriceComp = compareProductPrice(o);
		
		if(productPriceComp == 0) {
			int expirationDateComp = compareExpirationDate(o);
			
			if(expirationDateComp == 0) {
				int quantityMComp = compareQuantityM(o);
				
				if(quantityMComp == 0) {
					return -1;
				}else {
					return quantityMComp;
				}
			}else {
				return expirationDateComp; 
			}
		}else {
			return  productPriceComp;
		}
	}

	/* Lower quantity to buy is best
	 * @returns: 1 this best, 0 equals, -1 this worse 
	 */
	private int compareQuantityM(VtexPromotion o) {
		return -1 * this.quantityM.compareTo(o.quantityM);
	}

	/* Nearest expiration date is best
	 * @returns: 1 this best, 0 equals, -1 this worse 
	 */
	private int compareExpirationDate(VtexPromotion o) {
		return -1 * this.expirationDate.compareTo(o.expirationDate);
	}

	/* Lower price is better
	 * @returns: 1 this best, 0 equals, -1 this worse
	 */
	private int compareProductPrice(VtexPromotion o) {
		return -1 * this.singleProductPrice.compareTo(o.singleProductPrice);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VtexPromotion [group=");
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
		builder.append(", highlight=");
		builder.append(highlight);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
		builder.append(", description=");
		builder.append(description);
		builder.append(", shortDescription=");
		builder.append(shortDescription);
		builder.append("]");
		return builder.toString();
	}


}