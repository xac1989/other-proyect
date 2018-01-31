package com.cencosud.mobile.list.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSkuData {

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

	public ProductSkuData() { }

	public ProductSkuData(ProductSkuData skuDataDto) {
		this.skuId = skuDataDto.skuId;
		this.refId = skuDataDto.refId;
		this.cartLimit = skuDataDto.cartLimit;
		this.allowNotes = skuDataDto.allowNotes;
		this.allowSubstitute = skuDataDto.allowSubstitute;
		this.measurementUnit = skuDataDto.measurementUnit;
		this.unitMultiplier = skuDataDto.unitMultiplier;
		this.promotions = skuDataDto.promotions != null ? new ArrayList<>(skuDataDto.promotions) : null;
		this.measurementUnitUn = skuDataDto.measurementUnitUn;
		this.unitMultiplierUn = skuDataDto.unitMultiplierUn;
		this.measurementUnitSelector = skuDataDto.measurementUnitSelector;
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