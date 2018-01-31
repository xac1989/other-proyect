package com.cencosud.middleware.order.client.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cencosud.middleware.order.model.ItemDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VtexFullOrderItem {

	private String uniqueId;
	private String id;
	private String productId;
	private String ean;
	private String lockId;
	private List<String> attachments;
	private int quantity;
	private String seller;
	private String name;
	private String description;
	private String refId;
	private int price;
	private int listPrice;
	private String manualPrice;
	private String imageUrl;
	private String detailUrl;
	private List<String> components;
	private List<String> bundleItems;
	private List<String> params;
	private List<String> offerings;
	private String sellerSku;
	private String priceValidUntil;
	private int commission;
	private int tax;
	private String preSaleDate;
	private String measurementUnit;
	private int unitMultiplier;
	private int sellingPrice;
	private boolean isGift;
	private String shippingPrice;
	private Map<String, Object> additionalInfo;
	private Map<String, Object> itemAttachment;
	private Map<String, Object> dimension;
	private List<Map<String, Object>> priceTags;
	private String rewardValue;

	public String getRewardValue() {
		return rewardValue;
	}
	public void setRewardValue(String rewardValue) {
		this.rewardValue = rewardValue;
	}
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getListPrice() {
		return listPrice;
	}

	public void setListPrice(int listPrice) {
		this.listPrice = listPrice;
	}

	public String getManualPrice() {
		return manualPrice;
	}

	public void setManualPrice(String manualPrice) {
		this.manualPrice = manualPrice;
	}

	public List<Map<String, Object>> getPriceTags() {
		return priceTags;
	}

	public void setPriceTags(List<Map<String, Object>> priceTags) {
		this.priceTags = priceTags;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}

	public List<String> getBundleItems() {
		return bundleItems;
	}

	public void setBundleItems(List<String> bundleItems) {
		this.bundleItems = bundleItems;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public List<String> getOfferings() {
		return offerings;
	}

	public void setOfferings(List<String> offerings) {
		this.offerings = offerings;
	}

	public String getSellerSku() {
		return sellerSku;
	}

	public void setSellerSku(String sellerSku) {
		this.sellerSku = sellerSku;
	}

	public String getPriceValidUntil() {
		return priceValidUntil;
	}

	public void setPriceValidUntil(String priceValidUntil) {
		this.priceValidUntil = priceValidUntil;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public String getPreSaleDate() {
		return preSaleDate;
	}

	public void setPreSaleDate(String preSaleDate) {
		this.preSaleDate = preSaleDate;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public int getUnitMultiplier() {
		return unitMultiplier;
	}

	public void setUnitMultiplier(int unitMultiplier) {
		this.unitMultiplier = unitMultiplier;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public boolean getIsGift() {
		return isGift;
	}

	public void setIsGift(boolean isGift) {
		this.isGift = isGift;
	}

	public String getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(String shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Map<String, Object> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(Map<String, Object> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Map<String, Object> getItemAttachment() {
		return itemAttachment;
	}

	public void setItemAttachment(Map<String, Object> itemAttachment) {
		this.itemAttachment = itemAttachment;
	}

	public Map<String, Object> getDimension() {
		return dimension;
	}

	public void setDimension(Map<String, Object> dimension) {
		this.dimension = dimension;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemDetail toModelItemDetail() {
		return new ItemDetail(this.productId, this.name, this.imageUrl, BigDecimal.valueOf(price).movePointLeft(2), this.quantity);
	}
}
