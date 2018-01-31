package com.cencosud.middleware.delivery.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.delivery.service.DeliveryService;

@Service
public class DeliveryServiceFactory {

	@Autowired
	private List<DeliveryService> services;

	private final Map<String, DeliveryService> servicesCache = new HashMap<>();

	@PostConstruct
	public void initMyRepositoryCache() {
		for (DeliveryService service : services) {
			servicesCache.put(service.getRegionId(), service);
			System.out.println(service.getRegionId());
		}
	}

	public DeliveryService getService(String regionId) {
		DeliveryService srv = servicesCache.get(regionId);
		if (srv == null) {
			throw new RuntimeException("Unknown implementation type: " + regionId);
		}
		return srv;
	}
}
