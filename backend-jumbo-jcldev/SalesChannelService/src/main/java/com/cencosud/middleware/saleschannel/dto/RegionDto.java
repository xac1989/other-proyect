package com.cencosud.middleware.saleschannel.dto;

import java.util.Set;

import com.cencosud.middleware.saleschannel.model.SalesChannel;

/**
 * 
 * 
 * <h1>SalesChannelGetResponse</h1>
 * <p>
 * Objeto respuesta de petición de sales channel
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Jul 21, 2017
 */
public class RegionDto {

	private String name;
	private Set<SalesChannel> salesChannels;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SalesChannel> getSalesChannels() {
		return salesChannels;
	}

	public void setSalesChannels(Set<SalesChannel> salesChannels) {
		this.salesChannels = salesChannels;
	}

	@Override
	public String toString() {
		return "regionDto = [ name = " + this.name + ", salesChannels = " + this.salesChannels + " ]";
	}
}
