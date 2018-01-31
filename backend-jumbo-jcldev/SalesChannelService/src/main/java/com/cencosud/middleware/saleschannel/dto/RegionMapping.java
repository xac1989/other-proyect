package com.cencosud.middleware.saleschannel.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cencosud.middleware.saleschannel.model.Commune;
import com.cencosud.middleware.saleschannel.model.SalesChannel;
import com.cencosud.middleware.saleschannel.model.StoresInfo;

/**
 * 
 * 
 * <h1>RegionsGetResponse</h1>
 * <p>
 * Objeto respuesta de petición de Regiones
 * </p>
 * 
 * @author engelbert.quiroz
 * @version 1.0
 * @since Oct 09, 2017
 */
public class RegionMapping {
	Map<String, List<Commune>> storesInfo;

	public RegionMapping(StoresInfo storesInfo) {
		this.storesInfo = storesInfo.getStores();

	}

	public List<RegionDto> getRegionDtoList() {
		List<RegionDto> regionDtoList = new ArrayList<>();
		for (Map.Entry<String, List<Commune>> store : storesInfo.entrySet()) {
			Set<SalesChannel> salesChannels = new HashSet<>();
			for (Commune commune : store.getValue()) {
				salesChannels.add(new SalesChannel(Integer.valueOf(commune.getSalesChannel())));
			}

			RegionDto regionDto = new RegionDto();
			regionDto.setName(store.getKey());
			regionDto.setSalesChannels(salesChannels);
			regionDtoList.add(regionDto);
		}
		return regionDtoList;
	}

}
