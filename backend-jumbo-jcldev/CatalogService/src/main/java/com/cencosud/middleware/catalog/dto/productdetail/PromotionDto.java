package com.cencosud.middleware.catalog.dto.productdetail;

import java.math.BigDecimal;
import java.util.Date;

import com.cencosud.middleware.catalog.client.VtexHighlight;

public class PromotionDto implements Comparable<PromotionDto>{
	
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
	
	public BigDecimal getSingleProductPrice() {
		return singleProductPrice;
	}

	public void setSingleProductPrice(BigDecimal singleProductPrice) {
		this.singleProductPrice = singleProductPrice;
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

	@Override
	public int compareTo(PromotionDto o) {
		return this.getDiscountAmount().compareTo(o.discountAmount);
	}

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