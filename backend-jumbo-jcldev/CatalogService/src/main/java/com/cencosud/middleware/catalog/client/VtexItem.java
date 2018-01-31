package com.cencosud.middleware.catalog.client;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cencosud.middleware.catalog.model.Image;
import com.cencosud.middleware.catalog.model.Item;
import com.cencosud.middleware.catalog.model.Seller;

public class VtexItem {

	
	private String itemId;
	private String name;
	private String nameComplete;
	private String complementName;
	private String ean;
	private String measurementUnit;
	private int unitMultiplier;

	private List<Object> referenceId;
	private List<VtexImage> images;
	private List<String> imagesURLList;
	private List<VtexSeller> sellers;
	
	public VtexItem(JSONObject fromObj){
		
		super();
		this.itemId = fromObj.getString("itemId");
		this.name = fromObj.getString("name");
		this.nameComplete = fromObj.getString("nameComplete");
		this.complementName = fromObj.getString("complementName");
		this.ean = fromObj.getString("ean");
		this.measurementUnit = fromObj.getString("measurementUnit");
		this.unitMultiplier = fromObj.getInt("unitMultiplier");

		JSONArray imagesArray;
		try{
			imagesArray = fromObj.getJSONArray("images");
		}catch(JSONException e){
			imagesArray = new JSONArray();
		}
		
		this.imagesURLList = new ArrayList<String>();
		this.images = new ArrayList<VtexImage>(imagesArray.length());
		for(int i = 0; i< imagesArray.length(); i++){
			VtexImage vtexImage = new VtexImage(imagesArray.getJSONObject(i));
			this.images.add(vtexImage);
			imagesURLList.add(vtexImage.getImageUrl());
		}

		JSONArray sellersArray;
		try{
			sellersArray = fromObj.getJSONArray("sellers");
		}catch(JSONException e){
			sellersArray = new JSONArray();
		}
		this.sellers = new ArrayList<VtexSeller>(sellersArray.length());
		for(int i = 0; i< sellersArray.length(); i++){
			this.sellers.add(new VtexSeller(sellersArray.getJSONObject(i)));
		}
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameComplete() {
		return nameComplete;
	}
	public void setNameComplete(String nameComplete) {
		this.nameComplete = nameComplete;
	}
	public String getComplementName() {
		return complementName;
	}
	public void setComplementName(String complementName) {
		this.complementName = complementName;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
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
	public List<Object> getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(List<Object> referenceId) {
		this.referenceId = referenceId;
	}

	public List<VtexImage> getImages() {
		return images;
	}

	public void setImages(List<VtexImage> images) {
		this.images = images;
	}

	public List<VtexSeller> getSellers() {
		return sellers;
	}

	public void setSellers(List<VtexSeller> sellers) {
		this.sellers = sellers;
	}

	public List<String> getImagesURLList() {
		return imagesURLList;
	}

	public void setImagesURLList(List<String> imagesURLList) {
		this.imagesURLList = imagesURLList;
	}

	public Item toModelItem(){
		if(this.sellers == null){
			this.sellers = new ArrayList<>(0);
		}
		if(this.images == null){
			this.images = new ArrayList<>(0);
		}
		
		List<Seller> modelSellers = new ArrayList<Seller>(this.sellers.size());
		List<Image> modelImages = new ArrayList<Image>(this.images.size());
		
		for(VtexSeller currentSeller: this.sellers){
			modelSellers.add(currentSeller.toModelSeller());
		}
		for(VtexImage currentImage: this.images){
			modelImages.add(currentImage.toModelImage());
		}
		
		return new Item(modelSellers, modelImages);
	}
	
}
