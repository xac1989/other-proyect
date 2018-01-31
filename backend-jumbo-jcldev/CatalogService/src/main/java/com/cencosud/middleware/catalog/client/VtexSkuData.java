package com.cencosud.middleware.catalog.client;

import java.math.BigDecimal;
import java.util.List;

public class VtexSkuData {

	private String skuId;
    private Integer cart_limit;
    private Boolean allow_notes;
    private Boolean allow_substitute;
    private String measurement_unit;
    private BigDecimal unit_multiplier;
    private List<String> promotions;
    private String measurement_unit_un;
    private BigDecimal unit_multiplier_un;
    private Boolean measurement_unit_selector;
    
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public Integer getCart_limit() {
		return cart_limit;
	}
	public void setCart_limit(Integer cart_limit) {
		this.cart_limit = cart_limit;
	}
	public Boolean getAllow_notes() {
		return allow_notes;
	}
	public void setAllow_notes(Boolean allow_notes) {
		this.allow_notes = allow_notes;
	}
	public Boolean getAllow_substitute() {
		return allow_substitute;
	}
	public void setAllow_substitute(Boolean allow_substitute) {
		this.allow_substitute = allow_substitute;
	}
	public String getMeasurement_unit() {
		return measurement_unit;
	}
	public void setMeasurement_unit(String measurement_unit) {
		this.measurement_unit = measurement_unit;
	}

	public List<String> getPromotions() {
		return promotions;
	}
	public void setPromotions(List<String> promotions) {
		this.promotions = promotions;
	}
	public String getMeasurement_unit_un() {
		return measurement_unit_un;
	}
	public void setMeasurement_unit_un(String measurement_unit_un) {
		this.measurement_unit_un = measurement_unit_un;
	}
	public BigDecimal getUnit_multiplier() {
		return unit_multiplier;
	}
	public void setUnit_multiplier(BigDecimal unit_multiplier) {
		this.unit_multiplier = unit_multiplier;
	}
	public BigDecimal getUnit_multiplier_un() {
		return unit_multiplier_un;
	}
	public void setUnit_multiplier_un(BigDecimal unit_multiplier_un) {
		this.unit_multiplier_un = unit_multiplier_un;
	}
	public Boolean getMeasurement_unit_selector() {
		return measurement_unit_selector;
	}
	public void setMeasurement_unit_selector(Boolean measurement_unit_selector) {
		this.measurement_unit_selector = measurement_unit_selector;
	}
	
 
}
