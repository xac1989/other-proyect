package com.cencosud.mobile.model.profile.jumbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesChannel {

	private Integer id;
	private String name;
	private String address;
	
	public SalesChannel() {
	}

	/**
	 * @param id
	 * @param name
	 * @param address
	 */
	public SalesChannel(Integer id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
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

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
		builder.append("]");
		return builder.toString();
	}
	
}
