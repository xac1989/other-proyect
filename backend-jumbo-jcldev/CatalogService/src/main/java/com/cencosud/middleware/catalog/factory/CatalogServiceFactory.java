package com.cencosud.middleware.catalog.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.catalog.service.CatalogService;

@Service
public class CatalogServiceFactory {

	
	private List<CatalogService> services;
	
	@Autowired
	public void setServices(List<CatalogService> services) {
		this.services = services;
	}

	private final Map<String, CatalogService> servicesCache = new HashMap<>();

	@PostConstruct
	public void initMyRepositoryCache() {
		for (CatalogService service : services) {
			servicesCache.put(service.getRegionId(), service);
			System.out.println(service.getRegionId());
		}
	}

	public CatalogService getService(String regionId) {
		CatalogService srv = servicesCache.get(regionId);
		if (srv == null) {
			throw new RuntimeException("Unknown implementation type: " + regionId);
		}
		return srv;
	}
}