package com.cencosud.mobile.saleschannel.dto;

import java.util.List;

import com.cencosud.mobile.saleschannel.model.SalesChannel;

/**
 * 
 * 
 * <h1>SalesChannelGetResponse</h1>
 * <p>
 * Objeto respuesta de peticion de sales channel
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jul 21, 2017
 */
public class SalesChannelListDto {
	
	private List<SalesChannel> salesChannels;
	
	public SalesChannelListDto() {
	}

	/**
	 * @param salesChannels
	 */
	public SalesChannelListDto(List<SalesChannel> salesChannels) {
		super();
		this.salesChannels = salesChannels;
	}

	/**
	 * @return the salesChannels
	 */
	public List<SalesChannel> getSalesChannels() {
		return salesChannels;
	}

	/**
	 * @param salesChannels the salesChannels to set
	 */
	public void setSalesChannels(List<SalesChannel> salesChannels) {
		this.salesChannels = salesChannels;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SalesChannelGetResponse [salesChannels=");
		builder.append(salesChannels);
		builder.append("]");
		return builder.toString();
	}
}
