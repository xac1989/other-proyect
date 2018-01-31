package com.cencosud.mobile.list.model;

/**
 * 
 * 
 * <h1>ProductUpdate</h1>
 * <p>
 * Objetp producto para actualizar listas
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 29, 2017
 */
public class ProductUpdate {

	private String skuId;
	private Integer quantity;
	
	public ProductUpdate() {
	}

	/**
	 * @param skuId
	 * @param quantity
	 */
	public ProductUpdate(String skuId, Integer quantity) {
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
		builder.append("ProductUpdate [skuId=");
		builder.append(skuId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}
	
	
}
