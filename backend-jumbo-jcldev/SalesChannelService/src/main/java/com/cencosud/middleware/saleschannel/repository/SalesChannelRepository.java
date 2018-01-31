package com.cencosud.middleware.saleschannel.repository;

import java.util.List;

import com.cencosud.middleware.saleschannel.model.SalesChannel;
import com.cencosud.middleware.saleschannel.model.StoresInfo;

/**
 * 
 * 
 * <h1>SalesChannelRepository</h1>
 * <p>
 * Contrato de clase encargada de consultar a Vtex
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jul 21, 2017
 */
public interface SalesChannelRepository {
	
	/**
	 * Metodo de consulta para los Sales Channels
	 */
	List<SalesChannel> getSalesChannels();
	
	/**
	 * Metodo de consulta para las comunas por Región
	 */

	StoresInfo getStoresInfo();	
}
