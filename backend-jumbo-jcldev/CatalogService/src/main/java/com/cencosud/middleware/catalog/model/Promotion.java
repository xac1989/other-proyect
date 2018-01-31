package com.cencosud.middleware.catalog.model;

import java.util.Date;
import java.util.List;

public class Promotion {
	
	private String name;
	private String group;
	private String type;
	private String discountType;
	private String promotionType;
	private String value;
	private Date start;
	private Date end;
	private List<String> availableDays;
	private List<String> salesChannels;
	private String description;
	private String short_description;
	private String banner;
	private String priority;
	private String cumulative;
	private String enableBuyTogetherPerSku;
	private String quantity;
	private String quantityAffected;
	private Highlight highlight;
	
	
	public Promotion() {
		super();
	}


	public Promotion(String name, String group, String type, String discountType, String promotionType, String value,
			Date start, Date end, List<String> availableDays, List<String> salesChannels, String description,
			String short_description, String banner, String priority, String cumulative, String enableBuyTogetherPerSku,
			String quantity, String quantityAffected, Highlight highlight) {
		super();
		this.name = name;
		this.group = group;
		this.type = type;
		this.discountType = discountType;
		this.promotionType = promotionType;
		this.value = value;
		this.start = start;
		this.end = end;
		this.availableDays = availableDays;
		this.salesChannels = salesChannels;
		this.description = description;
		this.short_description = short_description;
		this.banner = banner;
		this.priority = priority;
		this.cumulative = cumulative;
		this.enableBuyTogetherPerSku = enableBuyTogetherPerSku;
		this.quantity = quantity;
		this.quantityAffected = quantityAffected;
		this.highlight = highlight;
	}


	public String getName() {
		return name;
	}

	public Promotion setName(String name) {
		this.name = name;
		return this;
	}

	public String getGroup() {
		return group;
	}

	public Promotion setGroup(String group) {
		this.group = group;
		return this;
	}

	public String getType() {
		return type;
	}

	public Promotion setType(String type) {
		this.type = type;
		return this;
	}

	public String getDiscountType() {
		return discountType;
	}

	public Promotion setDiscountType(String discountType) {
		this.discountType = discountType;
		return this;
	}

	public String getValue() {
		return value;
	}

	public Promotion setValue(String value) {
		this.value = value;
		return this;
	}

	public Date getStart() {
		return start;
	}

	public Promotion setStart(Date start) {
		this.start = start;
		return this;
	}

	public Date getEnd() {
		return end;
	}

	public Promotion setEnd(Date end) {
		this.end = end;
		return this;
	}

	public List<String> getAvailableDays() {
		return availableDays;
	}

	public Promotion setAvailableDays(List<String> availableDays) {
		this.availableDays = availableDays;
		return this;
	}

	public List<String> getSalesChannels() {
		return salesChannels;
	}

	public Promotion setSalesChannels(List<String> salesChannels) {
		this.salesChannels = salesChannels;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Promotion setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getShort_description() {
		return short_description;
	}

	public Promotion setShort_description(String short_description) {
		this.short_description = short_description;
		return this;
	}

	public String getBanner() {
		return banner;
	}

	public Promotion setBanner(String banner) {
		this.banner = banner;
		return this;
	}

	public String getPriority() {
		return priority;
	}

	public Promotion setPriority(String priority) {
		this.priority = priority;
		return this;
	}

	public String getCumulative() {
		return cumulative;
	}

	public Promotion setCumulative(String cumulative) {
		this.cumulative = cumulative;
		return this;
	}

	public String getEnableBuyTogetherPerSku() {
		return enableBuyTogetherPerSku;
	}

	public Promotion setEnableBuyTogetherPerSku(String enableBuyTogetherPerSku) {
		this.enableBuyTogetherPerSku = enableBuyTogetherPerSku;
		return this;
	}

	public String getQuantity() {
		return quantity;
	}

	public Promotion setQuantity(String quantity) {
		this.quantity = quantity;
		return this;
	}

	public String getQuantityAffected() {
		return quantityAffected;
	}

	public Promotion setQuantityAffected(String quantityAffected) {
		this.quantityAffected = quantityAffected;
		return this;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public Promotion setPromotionType(String promotionType) {
		this.promotionType = promotionType;
		return this;
	}

	
	public Highlight getHighlight() {
		return highlight;
	}


	public Promotion setHighlight(Highlight highlight) {
		this.highlight = highlight;
		return this;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Promotion [name=");
		builder.append(name);
		builder.append(", group=");
		builder.append(group);
		builder.append(", type=");
		builder.append(type);
		builder.append(", discountType=");
		builder.append(discountType);
		builder.append(", promotionType=");
		builder.append(promotionType);
		builder.append(", value=");
		builder.append(value);
		builder.append(", start=");
		builder.append(start);
		builder.append(", end=");
		builder.append(end);
		builder.append(", availableDays=");
		builder.append(availableDays);
		builder.append(", salesChannels=");
		builder.append(salesChannels);
		builder.append(", description=");
		builder.append(description);
		builder.append(", short_description=");
		builder.append(short_description);
		builder.append(", banner=");
		builder.append(banner);
		builder.append(", priority=");
		builder.append(priority);
		builder.append(", cumulative=");
		builder.append(cumulative);
		builder.append(", enableBuyTogetherPerSku=");
		builder.append(enableBuyTogetherPerSku);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", quantityAffected=");
		builder.append(quantityAffected);
		builder.append(", highlight=");
		builder.append(highlight);
		builder.append("]");
		return builder.toString();
	}


	
}