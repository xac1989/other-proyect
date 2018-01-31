package com.cencosud.middleware.saleschannel.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSetter;

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
@JsonInclude(Include.NON_NULL)
public class SalesChannel {

	private Integer id;
	private String name;
	private String address;
	
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
	 */
	public SalesChannel(Integer id) {
		super();
		this.id = id;
	}
	

	/**
	 * @return the id
	 */
	@JsonGetter("id")
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@JsonSetter("Id")
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@JsonGetter("name")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	@JsonSetter("Name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	@JsonGetter("address")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	@JsonSetter("Address")
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		
		if (this.getClass() != obj.getClass())
		    return false;
		
		return this.getId().intValue() == ((SalesChannel) obj).getId().intValue();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
