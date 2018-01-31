package com.cencosud.middleware.saleschannel.repository.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.saleschannel.client.VtexClient;
import com.cencosud.middleware.saleschannel.model.SalesChannel;
import com.cencosud.middleware.saleschannel.model.StoresInfo;
import com.cencosud.middleware.saleschannel.repository.SalesChannelRepository;

@Repository
public class SalesChannelRepositoryImpl implements SalesChannelRepository {
	
	private Logger logger = LoggerFactory.getLogger(SalesChannelRepositoryImpl.class);

	@Autowired
	@Qualifier("vTexClient")
	private VtexClient client;

	@Autowired
	@Qualifier("vTexEntitiesClient")
	private VtexClient entitiesClient;
	private static final String PATH_SALES_CHANNEL = "/api/catalog_system/pvt/saleschannel/list";
	private static final String PATH_STORES_INFO = "/dataentities/SL/documents/storesinfo?_fields=data,id";

	@Override
	public List<SalesChannel> getSalesChannels() {
		logger.info("Inicia consulta de Sales Channel");
		SalesChannel[] salesChannelarray = client.executeService(PATH_SALES_CHANNEL, null, SalesChannel[].class, HttpMethod.GET);
		logger.info("Finaliza consulta de Sales Channel");
		
		return Arrays.asList(salesChannelarray);
	}

	@Override
	public StoresInfo getStoresInfo() {
		logger.info("Inicia consulta de Regions");
		StoresInfo storesInfo = entitiesClient.executeService(PATH_STORES_INFO, null, StoresInfo.class, HttpMethod.GET);
		logger.info("Finaliza consulta de Regions");
		return storesInfo;
	}
}
