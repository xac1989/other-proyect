package com.cencosud.mobile.saleschannel.service;

import com.cencosud.mobile.saleschannel.dto.RegionListDto;
import com.cencosud.mobile.saleschannel.dto.SalesChannelListDto;

/**
 * 
 * 
 * <h1>SalesChannelApiService</h1>
 * <p>
 * Funciones del api sobre el adapter
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jul 24, 2017
 */
public interface SalesChannelApiService {
	
	/**
	 * Metodo para extraer la lista de Sales Channel
	 * @return {@link SalesChannelListDto}
	 */
	SalesChannelListDto getSalesChannels();

	/**
	 * Metodo para extraer la lista de Regiones
	 * @return {@link RegionListDto}
	 */
	RegionListDto getRegions();
}
