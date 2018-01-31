package com.cencosud.middleware.saleschannel.service;

import java.util.List;

import com.cencosud.middleware.saleschannel.model.SalesChannel;
import com.cencosud.middleware.saleschannel.model.StoresInfo;

public interface SalesChannelService {
	
	/**
	 * 
	 * @return
	 */
	String getRegionId();
	
	/**
	 * Metodo de consulta para los Sales Channels
	 */
	List<SalesChannel> getSalesChannels();

	/**
	 * Metodo de consulta para los Stores Info
	 */
	StoresInfo getStoresInfo();
}
