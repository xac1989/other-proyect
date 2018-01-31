package com.cencosud.mobile.saleschannel.model;

import java.util.List;

public class Region {

	private String name;
	private List<SalesChannel> salesChannels;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SalesChannel> getSalesChannels() {
		return salesChannels;
	}

	public void setSalesChannels(List<SalesChannel> salesChannels) {
		this.salesChannels = salesChannels;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("region = [name=");
		builder.append(name);
		builder.append(", salesChannels = ");
		builder.append(salesChannels);
		builder.append("]");
		return builder.toString();
	}

}
