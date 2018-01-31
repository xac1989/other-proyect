package com.cencosud.middleware.catalog.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.middleware.catalog.model.Item;
import com.cencosud.middleware.catalog.model.Product;

public class VtexProduct {
	private static final Logger LOGGER = LoggerFactory.getLogger(VtexProduct.class);
	
	private String productId;
	private String productName;
	private String brand;
	private String linkText;
	private String productReference;
	private String link;
	private String description;
	private String promotionShortDescription;
	private String promotionDescription;
	private List<String> allSpecifications;
	private List<VtexItem> items;
	private List<VtexSpecification> specifications;
	private Long categoryId;
	private String generalInfo;
	private String salesChannel;
	private ArrayList<VtexSkuData> skuData;
	private List<Integer> nutritionalFlags;
	private List<VtexHighlight> highlights;
	private String certification;
	private List<VtexPromotion> promotions;
	
	private static final Map<String, Integer> nutritionalFlagKeys = new HashMap<String, Integer>(){
		private static final long serialVersionUID = 1L;
		{
				put("Alto en Grasas Saturadas",1);
				put("Alto en Sodio",2);
				put("Alto en Azúcares",3);
				put("Alto en Calorías",4);
		}
	};  
	
	public VtexProduct() {

	}
	
	public VtexProduct(JSONObject fromObj){
		
		super();
		this.productId = fromObj.getString("productId");
		this.productName = fromObj.getString("productName");
		this.brand = fromObj.getString("brand");
		this.linkText = fromObj.getString("linkText");
		this.productReference = fromObj.getString("productReference");
		this.link = fromObj.getString("link");
		this.description = fromObj.getString("description");
		this.categoryId = fromObj.getLong("categoryId");

		this.nutritionalFlags = getNutritionalFlags(fromObj);
		this.certification = getCertification(fromObj);
		
		JSONArray specificationsArray;
		try{
			specificationsArray =  fromObj.getJSONArray("allSpecifications");
		}catch(JSONException e){
			specificationsArray = new JSONArray();
		}
		
		this.allSpecifications = new ArrayList<String>(specificationsArray.length());
		for(Object currentObject: specificationsArray){
			this.allSpecifications.add((String) currentObject);
		}
		
		specifications = new ArrayList<VtexSpecification>();
		for(String currentSpecification: this.allSpecifications){
			JSONArray specificationArray =  fromObj.getJSONArray(currentSpecification);
			List<String> currentSpecifications = new ArrayList<String>(specificationArray.length());
			for(Object currentSpec: specificationArray){
				currentSpecifications.add((String) currentSpec);
			}
			
			if (currentSpecification.equals("Info General")) {
				this.setGeneralInfo(currentSpecifications.get(0));
			} else {
				specifications.add(new VtexSpecification(currentSpecification, currentSpecifications.get(0)));
			}
		}
		
		
		
		JSONArray itemsArray;
		try{
			itemsArray =  fromObj.getJSONArray("items");
		}catch(JSONException e){
			itemsArray = new JSONArray();
		}
		this.items = new ArrayList<VtexItem>(itemsArray.length());
		for(int i = 0 ; i< itemsArray.length(); i++){
			this.items.add(new VtexItem(itemsArray.getJSONObject(i)));
		}
		
		
		String itemId = items.get(0).getItemId();

		this.skuData = getSkuData(fromObj, itemId);
		
		this.highlights = gatherHighlights(fromObj);
				
	}

	private List<Integer> getNutritionalFlags(JSONObject fromObj) {
		List<Integer> nutricionalFlagsArr = new ArrayList<>();
		
		JSONArray nutritionalFlags;
		try{
			nutritionalFlags =  fromObj.getJSONArray("Flag Nutricional");
		}catch(JSONException e){
			nutritionalFlags = new JSONArray();
		}
		
		for(Object nutritionalFlag: nutritionalFlags){
			Integer flagId = nutritionalFlagKeys.get(nutritionalFlag);
			if(flagId != null){
				nutricionalFlagsArr.add(flagId);
			}
		}
		return nutricionalFlagsArr;
	}
	
	private String getCertification(JSONObject fromObj) {
		String certification = "";
		try {
			certification = fromObj.getJSONArray("Certificación").getString(0);
		} catch (JSONException e) {
			LOGGER.debug("There is no certification attribute");
		}

		return certification;
	}
	
	private List<VtexHighlight> gatherHighlights(JSONObject fromObj){
		List<VtexHighlight> gatheredHighlights = new  ArrayList<VtexHighlight>();
		
		Map<String,Object> clusterHighlightsMap;
		try{
			clusterHighlightsMap =  fromObj.getJSONObject("clusterHighlights").toMap(); 
		}catch(JSONException e){
			clusterHighlightsMap = new HashMap<>();
		}
		
		gatheredHighlights.addAll(getFromClusterHighlights(clusterHighlightsMap));
		
		return gatheredHighlights;
	}

	private List<VtexHighlight> getFromClusterHighlights(Map<String,Object> clusterHighlightsMap){
		List<VtexHighlight> vtexHighlights = new  ArrayList<VtexHighlight>();
		
		for(String key: clusterHighlightsMap.keySet()){
			VtexHighlight vtexHighlight = new VtexHighlight();

			vtexHighlight.setName((String)clusterHighlightsMap.get(key));
			
			vtexHighlights.add(vtexHighlight);
		}
		
		return vtexHighlights;
	}
	
	

	private ArrayList<VtexSkuData> getSkuData(JSONObject fromObj, String itemId) {
		JSONArray skusDataArray;
		try {
			skusDataArray = fromObj.getJSONArray("SkuData");
		} catch (JSONException e) {
			skusDataArray = new JSONArray();
		}
		ArrayList<VtexSkuData> skuData = new ArrayList<VtexSkuData>();
		for (Object currentObject : skusDataArray) {
			
			JSONObject jsonObject = null;
			try {
				JSONObject currentJsonSkuData = new JSONObject((String) currentObject);
				jsonObject = currentJsonSkuData.getJSONObject(itemId);
			} catch (Exception e) {
				LOGGER.error("Sku Data couldn't be found, productId : " + this.productId, e);
				break;
			}
			VtexSkuData vtexSkuData = new VtexSkuData();
			vtexSkuData.setAllow_notes(jsonObject.getBoolean("allow_notes"));
			vtexSkuData.setAllow_substitute(jsonObject.getBoolean("allow_substitute"));
			vtexSkuData.setCart_limit(jsonObject.getInt("cart_limit"));
			vtexSkuData.setMeasurement_unit(jsonObject.getString("measurement_unit"));
			vtexSkuData.setMeasurement_unit_selector(jsonObject.getBoolean("measurement_unit_selector"));
			vtexSkuData.setMeasurement_unit_un(jsonObject.toMap().get("measurement_unit_un") == null
					? vtexSkuData.getMeasurement_unit() : jsonObject.getString("measurement_unit_un"));
			ArrayList<String> promotionsArray = new ArrayList<>();
			for (Object promotionJsonObject : jsonObject.getJSONArray("promotions")) {
				promotionsArray.add((String) promotionJsonObject);
			}
			vtexSkuData.setPromotions(promotionsArray);
			vtexSkuData.setSkuId(itemId);
			vtexSkuData
					.setUnit_multiplier(jsonObject.getBigDecimal("unit_multiplier").setScale(2, BigDecimal.ROUND_DOWN));
			vtexSkuData.setUnit_multiplier_un(
					jsonObject.toMap().get("unit_multiplier_un") == null ? vtexSkuData.getUnit_multiplier()
							: jsonObject.getBigDecimal("unit_multiplier_un").setScale(4, BigDecimal.ROUND_DOWN));
			skuData.add(vtexSkuData);
		}		
		return skuData;
	}
	
	public List<Integer> getNutritionalFlags() {
		return nutritionalFlags;
	}

	public void setNutritionalFlags(List<Integer> nutritionalFlags) {
		this.nutritionalFlags = nutritionalFlags;
	}

	public ArrayList<VtexSkuData> getSkuData() {
		return skuData;
	}

	public void setSkuData(ArrayList<VtexSkuData> skuData) {
		this.skuData = skuData;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getLinkText() {
		return linkText;
	}
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
	public String getProductReference() {
		return productReference;
	}
	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getAllSpecifications() {
		return allSpecifications;
	}
	public void setAllSpecifications(List<String> allSpecifications) {
		this.allSpecifications = allSpecifications;
	}
	public List<VtexItem> getItems() {
		return items;
	}
	public void setItems(List<VtexItem> items) {
		this.items = items;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public String getGeneralInfo() {
		return generalInfo;
	}


	public void setGeneralInfo(String generalInfo) {
		this.generalInfo = generalInfo;
	}


	public List<VtexSpecification> getSpecifications() {
		return specifications;
	}


	public void setSpecifications(List<VtexSpecification> specifications) {
		this.specifications = specifications;
	}


	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	

	public List<VtexHighlight> getHighlights() {
		return highlights;
	}

	public void setHighlights(List<VtexHighlight> higlights) {
		this.highlights = higlights;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public List<VtexPromotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<VtexPromotion> promotions) {
		this.promotions = promotions;
	}


	public String getPromotionShortDescription() {
		return promotionShortDescription;
	}

	public void setPromotionShortDescription(String promotionShortDescription) {
		this.promotionShortDescription = promotionShortDescription;
	}

	public String getPromotionDescription() {
		return promotionDescription;
	}

	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

	public Product toModelProduct(){
		
		List<Item> modelItems = new ArrayList<Item>(this.items.size());
		for(VtexItem currentItem: this.items){
			modelItems.add(currentItem.toModelItem());
		}
		return new Product(this.productName, this.productId, this.productReference, 
				modelItems);
		
	}
	
}
	
