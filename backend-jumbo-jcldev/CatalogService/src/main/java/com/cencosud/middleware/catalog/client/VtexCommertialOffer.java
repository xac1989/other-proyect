package com.cencosud.middleware.catalog.client;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cencosud.middleware.catalog.model.CommertialOffer;

public class VtexCommertialOffer {

	private BigDecimal price;
	private BigDecimal listPrice;
	private long availableQuantity;
	private BigDecimal tax;
	private String cacheVersionUsedToCallCheckout;
	private BigDecimal discountRate;
	private BigDecimal priceWithoutDiscount;
	private List<String> discountHighlights;

	public VtexCommertialOffer(JSONObject fromObj) {
		this.price = fromObj.getBigDecimal("Price").setScale(2, RoundingMode.HALF_EVEN);
		this.listPrice = fromObj.getBigDecimal("ListPrice").setScale(2, RoundingMode.HALF_EVEN);
		this.priceWithoutDiscount = fromObj.getBigDecimal("PriceWithoutDiscount").setScale(2, RoundingMode.HALF_EVEN);
		this.availableQuantity = fromObj.getInt("AvailableQuantity");
		this.tax = fromObj.getBigDecimal("Tax").setScale(2, RoundingMode.HALF_EVEN);
		this.cacheVersionUsedToCallCheckout = fromObj.getString("CacheVersionUsedToCallCheckout");
		discountRate = BigDecimal.ZERO;
		if (!(price.compareTo(BigDecimal.ZERO) == 0 && listPrice.compareTo(BigDecimal.ZERO) == 0)) {
			discountRate = new BigDecimal("100").subtract(
					price.multiply(new BigDecimal("100")).divide(listPrice, 2, RoundingMode.HALF_EVEN));
		}
		
		this.discountHighlights = getDiscountHighlights(fromObj);
	}

	
		
	private List<String> getDiscountHighlights(JSONObject fromObj) {
		List<String> discountHighlightList = new ArrayList<>();
		
		JSONArray discountHighlightArr;
		try{
			discountHighlightArr =  fromObj.getJSONArray("DiscountHighLight");
			
		}catch(JSONException e){
			discountHighlightArr = new JSONArray();
		}
		
		for(Object discountHighlightObj: discountHighlightArr){
			Map<String,Object> discountHighlightMap = ((JSONObject)discountHighlightObj).toMap();
			if(discountHighlightMap != null){
				for(String key : discountHighlightMap.keySet()){
					discountHighlightList.add((String)discountHighlightMap.get(key));
				}
			}
			
		}
		return discountHighlightList;
	}



	public List<String> getDiscountHighlights() {
		return discountHighlights;
	}

	public void setDiscountHighlights(List<String> discountHighlights) {
		this.discountHighlights = discountHighlights;
	}

	public BigDecimal getPriceWithoutDiscount() {
		return priceWithoutDiscount;
	}

	public void setPriceWithoutDiscount(BigDecimal priceWithoutDiscount) {
		this.priceWithoutDiscount = priceWithoutDiscount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getCacheVersionUsedToCallCheckout() {
		return cacheVersionUsedToCallCheckout;
	}

	public void setCacheVersionUsedToCallCheckout(String cacheVersionUsedToCallCheckout) {
		this.cacheVersionUsedToCallCheckout = cacheVersionUsedToCallCheckout;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public CommertialOffer toModelComertialOffer() {
		return new CommertialOffer(price, priceWithoutDiscount, discountRate, availableQuantity,
				(availableQuantity > 0));
	}
}
