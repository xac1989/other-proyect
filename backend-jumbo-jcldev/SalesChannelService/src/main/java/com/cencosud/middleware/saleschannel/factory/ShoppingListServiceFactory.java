package com.cencosud.middleware.saleschannel.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.saleschannel.service.SalesChannelService;

@Service
public class ShoppingListServiceFactory {

	@Autowired
	List<SalesChannelService> services;

	private final Map<String, SalesChannelService> servicesCache = new HashMap<>();

	@PostConstruct
	public void initMyRepositoryCache() {
		for (SalesChannelService service : services) {
			servicesCache.put(service.getRegionId(), service);
			System.out.println(service.getRegionId());
		}
	}

	public SalesChannelService getService(String regionId) {
		SalesChannelService service = servicesCache.get(regionId);
		if (service == null) {
			throw new RuntimeException("Unknown implementation type: " + regionId);
		}
		return service;
	}
}
