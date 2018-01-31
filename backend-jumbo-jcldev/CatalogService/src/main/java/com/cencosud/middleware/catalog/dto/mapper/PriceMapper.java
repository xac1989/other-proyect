package com.cencosud.middleware.catalog.dto.mapper;

import java.math.BigDecimal;

import org.springframework.util.CollectionUtils;

import com.cencosud.middleware.catalog.client.VtexProduct;

public class PriceMapper {
	
	private PriceMapper() {
		    throw new IllegalStateException("Utility class");
	}
	
	public static final BigDecimal mapPrice(VtexProduct product){
		
		return unitMultiply(product.getItems().get(0).getSellers().get(0).getCommertialOffer().getPrice(), getUnitMultiplier(product));
	}
	
	public static final BigDecimal mapListPrice(VtexProduct product){
				
		return unitMultiply(product.getItems().get(0).getSellers().get(0).getCommertialOffer().getListPrice(), getUnitMultiplier(product));
	}	
	
	private static BigDecimal unitMultiply(BigDecimal price, BigDecimal unitMultiplier) {
		return price.multiply(unitMultiplier);
	}

	private static boolean hasUnitMultipler(VtexProduct product) {
		return !CollectionUtils.isEmpty(product.getSkuData())
				 && product.getSkuData().get(0) !=null
				 && product.getSkuData().get(0).getUnit_multiplier() != null;
			
	}
	
	private static BigDecimal getUnitMultiplier(VtexProduct product){
		return hasUnitMultipler(product) ? product.getSkuData().get(0).getUnit_multiplier() : BigDecimal.ONE;
	}
}
