package com.cencosud.mobile.list.model;

/**
 * 
 * 
 * <h1>SkuQuantity</h1>
 * <p>
 * Cantidad de elementos por SKU
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
public class SkuQuantity {
	
	private Integer quantity;
	
	public SkuQuantity() {
	}

	/**
	 * @param quantity
	 */
	public SkuQuantity(Integer quantity) {
		super();
		this.quantity = quantity;
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
		builder.append("SkuQuantity [quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}
	
	
}
