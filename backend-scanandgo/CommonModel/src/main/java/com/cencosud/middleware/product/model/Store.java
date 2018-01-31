package com.cencosud.middleware.product.model;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * <h1>Store</h1>
 * <p>Modelo de objeto Store utilizado para guardar en base de datos.</p>
 * El mismo se compone por: <BR> 
 * * storeId : id de la tienda. <BR>
 * * price : precio del producto en esa tienda. <BR>
 * * PUM : Precio de unidad de medida (para productos pesables). <BR>
 * * UM : Unidad de medida, mas que nada para pesables. <BR>
 * * lastUpdate : Fecha de la ultima actualizacion. <BR>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
public class Store {
	private String storeId;
	private BigDecimal price;
	private BigDecimal pum;
	private String um;
	private Date lastUpdate;
	
	/**
	 * 
	 */
	public Store() {
	}

	/**
	 * @param storeId
	 * @param price
	 * @param pum
	 * @param um
	 */
	public Store(String storeId, BigDecimal price, BigDecimal pum, String um, Date lastUpdate) {
		super();
		this.storeId = storeId;
		this.price = price;
		this.pum = pum;
		this.um = um;
		this.lastUpdate = lastUpdate;
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((pum == null) ? 0 : pum.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
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
		Store other = (Store) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (pum == null) {
			if (other.pum != null)
				return false;
		} else if (!pum.equals(other.pum))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Store [storeId=");
		builder.append(storeId);
		builder.append(", price=");
		builder.append(price);
		builder.append(", pum=");
		builder.append(pum);
		builder.append(", um=");
		builder.append(um);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
