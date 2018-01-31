package com.cencosud.mobile.product.model;

import java.math.BigDecimal;
import java.util.List;

import com.cencosud.middleware.product.model.Image;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * 
 * 
 * <h1>ProductResponse</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 27, 2017
 */
@JsonAutoDetect
public class ProductResponse {
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
	private String storeId;
	private BigDecimal price;
	private BigDecimal pum;
	private String um;
	private Boolean selloGrasas;
	private Boolean selloSodio;
	private Boolean selloAzucares;
	private Boolean selloCaloricas;
	private BigDecimal cantidad;
	private Boolean pesable;
	
	private List<Image> images;

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
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the pum
	 */
	public BigDecimal getPum() {
		return pum;
	}

	/**
	 * @param pum the pum to set
	 */
	public void setPum(BigDecimal pum) {
		this.pum = pum;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public Boolean getSelloGrasas() {
		return selloGrasas;
	}

	public void setSelloGrasas(Boolean selloGrasas) {
		this.selloGrasas = selloGrasas;
	}

	public Boolean getSelloSodio() {
		return selloSodio;
	}

	public void setSelloSodio(Boolean selloSodio) {
		this.selloSodio = selloSodio;
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
	 * @return the cantidad
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the pesable
	 */
	public Boolean getPesable() {
		return pesable;
	}

	/**
	 * @param pesable the pesable to set
	 */
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
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		ProductResponse other = (ProductResponse) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
}
