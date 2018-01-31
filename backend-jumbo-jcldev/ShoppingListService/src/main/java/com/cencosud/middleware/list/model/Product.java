package com.cencosud.middleware.list.model;

/**
 * 
 * 
 * <h1>Product</h1>
 * <p>
 * Modelo Con datos de producto
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 29, 2017
 */
public class Product {
	private String skuId;
	private Integer quantity;
	
	public Product() {
	}

	/**
	 * @param skuId
	 * @param quantity
	 */
	public Product(String skuId, Integer quantity) {
		super();
		this.skuId = skuId;
		this.quantity = quantity;
	}

	/**
	 * @return the skuId
	 */
	public String getSkuId() {
		return skuId;
	}

	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [skuId=");
		builder.append(skuId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}
	
	
}
