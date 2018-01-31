package com.cencosud.mobile.saleschannel.model;

import java.util.List;

/**
 * 
 * 
 * <h1>SalesChannel</h1>
 * <p>
 * Entidad SalesChannel
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jul 21, 2017
 */
public class SalesChannel {

	private Integer id;
	private String name;
	private String address;
	private List<DeliveryType> deliveryType;
	
	public SalesChannel() {
	}

	/**
	 * @param id
	 * @param name
	 */
	public SalesChannel(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	

	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param deliveryType
	 */
	public SalesChannel(Integer id, String name, String address, List<DeliveryType> deliveryType) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.deliveryType = deliveryType;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the deliveryType
	 */
	public List<DeliveryType> getDeliveryType() {
		return deliveryType;
	}

	/**
	 * @param deliveryType the deliveryType to set
	 */
	public void setDeliveryType(List<DeliveryType> deliveryType) {
		this.deliveryType = deliveryType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SalesChannel [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", address=");
		builder.append(address);
		builder.append(", deliveryType=");
		builder.append(deliveryType);
		builder.append("]");
		return builder.toString();
	}
}
