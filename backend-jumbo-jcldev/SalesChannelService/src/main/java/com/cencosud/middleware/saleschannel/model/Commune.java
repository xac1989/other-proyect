package com.cencosud.middleware.saleschannel.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * 
 * <h1>Commune</h1>
 * <p>
 * Entidad Commune
 * </p>
 * @author engelbert.quiroz
 * @version 1.0
 * @since Oct 09, 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commune {

	private String name;
	private String salesChannel;
	private List<Double> geoCoordinates;

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return salesChannel
	 */
	public String getSalesChannel() {
		return salesChannel;
	}

	/**
	 * 
	 * @param salesChannel
	 */
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	/**
	 * 
	 * @return geoCoordinates
	 */
	public List<Double> getGeoCoordinates() {
		return geoCoordinates;
	}

	/**
	 * 
	 * @param geoCoordinates
	 */
	public void setGeoCoordinates(List<Double> geoCoordinates) {
		this.geoCoordinates = geoCoordinates;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Commune = [name = ");
		builder.append(this.name);
		builder.append(", salesChannel = ");
		builder.append(this.salesChannel);
		builder.append(", geoCoordinates = ");
		builder.append(this.getGeoCoordinates());
		builder.append("]");
		return builder.toString();
	}

}
