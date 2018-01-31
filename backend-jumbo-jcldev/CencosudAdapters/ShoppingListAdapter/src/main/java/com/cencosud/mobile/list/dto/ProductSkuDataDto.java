package com.cencosud.mobile.list.dto;

import java.math.BigDecimal;
import java.util.List;

import com.cencosud.mobile.list.model.ProductSkuData;
import com.cencosud.mobile.list.util.ListUtil;

public class ProductSkuDataDto {

	private String skuId;
	private String refId;
	private Integer cartLimit;
	private Boolean allowNotes;
	private Boolean allowSubstitute;
	private String measurementUnit;
	private BigDecimal unitMultiplier;
	private List<String> promotions;
	private String measurementUnitUn;
	private BigDecimal unitMultiplierUn;
	private Boolean measurementUnitSelector;

	public ProductSkuDataDto() { }

	public ProductSkuDataDto(ProductSkuData skuData) {
		if (skuData != null) {
			this.skuId = skuData.getSkuId();
			this.refId = skuData.getRefId();
			this.cartLimit = skuData.getCartLimit();
			this.allowNotes = skuData.getAllowNotes();
			this.allowSubstitute = skuData.getAllowSubstitute();
			this.measurementUnit = skuData.getMeasurementUnit();
			this.unitMultiplier = skuData.getUnitMultiplier();
			this.promotions = ListUtil.copy(skuData.getPromotions());
			this.measurementUnitUn = skuData.getMeasurementUnitUn();
			this.unitMultiplierUn = skuData.getUnitMultiplierUn();
			this.measurementUnitSelector = skuData.getMeasurementUnitSelector();
		}
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Integer getCartLimit() {
		return cartLimit;
	}

	public void setCartLimit(Integer cartLimit) {
		this.cartLimit = cartLimit;
	}

	public Boolean getAllowNotes() {
		return allowNotes;
	}

	public void setAllowNotes(Boolean allowNotes) {
		this.allowNotes = allowNotes;
	}

	public Boolean getAllowSubstitute() {
		return allowSubstitute;
	}

	public void setAllowSubstitute(Boolean allowSubstitute) {
		this.allowSubstitute = allowSubstitute;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public List<String> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<String> promotions) {
		this.promotions = promotions;
	}

	public String getMeasurementUnitUn() {
		return measurementUnitUn;
	}

	public void setMeasurementUnitUn(String measurementUnitUn) {
		this.measurementUnitUn = measurementUnitUn;
	}

	public BigDecimal getUnitMultiplier() {
		return unitMultiplier;
	}

	public void setUnitMultiplier(BigDecimal unitMultiplier) {
		this.unitMultiplier = unitMultiplier;
	}

	public BigDecimal getUnitMultiplierUn() {
		return unitMultiplierUn;
	}

	public void setUnitMultiplierUn(BigDecimal unitMultiplierUn) {
		this.unitMultiplierUn = unitMultiplierUn;
	}

	public Boolean getMeasurementUnitSelector() {
		return measurementUnitSelector;
	}

	public void setMeasurementUnitSelector(Boolean measurementUnitSelector) {
		this.measurementUnitSelector = measurementUnitSelector;
	}
}