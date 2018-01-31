package com.cencosud.mobile.saleschannel.dto;

import java.util.List;

import com.cencosud.mobile.saleschannel.model.Region;

public class RegionListDto {

	private List<Region> regions;

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[regions=");
		builder.append(regions);
		builder.append("]");
		return builder.toString();
	}
}
