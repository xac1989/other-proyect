package com.cencosud.middleware.product.model;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static ch.lambdaj.Lambda.*;

/**
 * 
 * 
 * <h1>Product</h1>
 * <p>
 * Modelo de objeto producto utilizado para guardar en base de datos
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
@Document(collection = "product")
public class Product {

	@Id
	private String id;
	private Integer productId;
	private String productName;
	private String nameComplete;
	private String productDescription;
	private String skuName;
	private String imageUrl;
	private String detailUrl;
	private String brandId;
	private String brandName;
	private String ean;
	private Boolean selloGrasas;
	private Boolean selloSodio;
	private Boolean selloAzucares;
	private Boolean selloCaloricas;
	private Boolean pesable;

	private List<Image> images;
	private List<Store> stores = new ArrayList<Store>();

	public Product() {
	}

	/**
	 * @param id
	 * @param productId
	 * @param productName
	 * @param nameComplete
	 * @param productDescription
	 * @param skuName
	 * @param imageUrl
	 * @param detailUrl
	 * @param brandId
	 * @param brandName
	 * @param ean
	 * @param selloGrasas
	 * @param selloSodio
	 * @param selloAzucares
	 * @param selloCaloricas
	 * @param images
	 * @param stores
	 */
	public Product(String id, Integer productId, String productName, String nameComplete, String productDescription,
			String skuName, String imageUrl, String detailUrl, String brandId, String brandName, String ean,
			Boolean selloGrasas, Boolean selloSodio, Boolean selloAzucares, Boolean selloCaloricas, Boolean pesable, 
			List<Image> images, List<Store> stores) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.nameComplete = nameComplete;
		this.productDescription = productDescription;
		this.skuName = skuName;
		this.imageUrl = imageUrl;
		this.detailUrl = detailUrl;
		this.brandId = brandId;
		this.brandName = brandName;
		this.ean = ean;
		this.selloGrasas = selloGrasas;
		this.selloSodio = selloSodio;
		this.selloAzucares = selloAzucares;
		this.selloCaloricas = selloCaloricas;
		this.pesable = pesable;
		this.images = images;
		this.stores = stores;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the nameComplete
	 */
	public String getNameComplete() {
		return nameComplete;
	}

	/**
	 * @param nameComplete the nameComplete to set
	 */
	public void setNameComplete(String nameComplete) {
		this.nameComplete = nameComplete;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the skuName
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * @param skuName the skuName to set
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the detailUrl
	 */
	public String getDetailUrl() {
		return detailUrl;
	}

	/**
	 * @param detailUrl the detailUrl to set
	 */
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	/**
	 * @return the brandId
	 */
	public String getBrandId() {
		return brandId;
	}

	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the ean
	 */
	public String getEan() {
		return ean;
	}

	/**
	 * @param ean the ean to set
	 */
	public void setEan(String ean) {
		this.ean = ean;
	}

	/**
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/**
	 * @return the stores
	 */
	public List<Store> getStores() {
		return stores;
	}

	/**
	 * @param stores the stores to set
	 */
	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public void addStore(Store store) {
		this.stores.add(store);
	}

	public void removeStore(Store otherStore) {
		 List<Store> tmp = filter(having(on(Store.class).getStoreId(), Matchers.equalTo(otherStore.getStoreId()) ), stores);
		 stores.removeAll(tmp);
	}

	public Boolean getSelloGrasas() {
		return selloGrasas;
	}

	public void setSelloGrasas(Boolean selloGrasas) {
		this.selloGrasas = selloGrasas;
	}

	public Boolean getSelloAzucares() {
		return selloAzucares;
	}

	public void setSelloAzucares(Boolean selloAzucares) {
		this.selloAzucares = selloAzucares;
	}

	public Boolean getSelloCaloricas() {
		return selloCaloricas;
	}

	public void setSelloCaloricas(Boolean selloCaloricas) {
		this.selloCaloricas = selloCaloricas;
	}

	public Boolean getSelloSodio() {
		return selloSodio;
	}

	public void setSelloSodio(Boolean selloSodio) {
		this.selloSodio = selloSodio;
	}

	public Boolean getPesable() {
		return pesable;
	}

	public void setPesable(Boolean pesable) {
		this.pesable = pesable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", productId=");
		builder.append(productId);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", nameComplete=");
		builder.append(nameComplete);
		builder.append(", productDescription=");
		builder.append(productDescription);
		builder.append(", skuName=");
		builder.append(skuName);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", detailUrl=");
		builder.append(detailUrl);
		builder.append(", brandId=");
		builder.append(brandId);
		builder.append(", brandName=");
		builder.append(brandName);
		builder.append(", ean=");
		builder.append(ean);
		builder.append(", selloGrasas=");
		builder.append(selloGrasas);
		builder.append(", selloSodio=");
		builder.append(selloSodio);
		builder.append(", selloAzucares=");
		builder.append(selloAzucares);
		builder.append(", selloCaloricas=");
		builder.append(selloCaloricas);
		builder.append(", images=");
		builder.append(images);
		builder.append(", stores=");
		builder.append(stores);
		builder.append("]");
		return builder.toString();
	}

}