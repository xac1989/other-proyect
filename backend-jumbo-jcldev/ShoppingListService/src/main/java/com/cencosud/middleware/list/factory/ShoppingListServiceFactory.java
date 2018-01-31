package com.cencosud.middleware.list.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.list.service.ListService;

@Service
public class ShoppingListServiceFactory {

	@Autowired
	List<ListService> services;

	private final Map<String, ListService> servicesCache = new HashMap<>();

	@PostConstruct
	public void initMyRepositoryCache() {
		for (ListService service : services) {
			servicesCache.put(service.getRegionId(), service);
			System.out.println(service.getRegionId());
		}
	}

	public ListService getService(String regionId) {
		ListService service = servicesCache.get(regionId);
		if (service == null) {
			throw new RuntimeException("Unknown implementation type: " + regionId);
		}
		return service;
	}
}
