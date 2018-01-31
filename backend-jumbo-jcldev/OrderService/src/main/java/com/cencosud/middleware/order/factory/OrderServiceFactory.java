package com.cencosud.middleware.order.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.order.service.OrderService;

@Service
public class OrderServiceFactory {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceFactory.class);
	
	private List<OrderService> services;
	
	@Autowired
	public void setServices(List<OrderService> services) {
		this.services = services;
	}

	private final Map<String, OrderService> servicesCache = new HashMap<>();

	@PostConstruct
	public void initMyRepositoryCache() {
		for (OrderService service : services) {
			servicesCache.put(service.getRegionId(), service);
			logger.info(service.getRegionId());
		}
	}

	public OrderService getService(String regionId) {
		OrderService srv = servicesCache.get(regionId);
		if (srv == null) {
			throw new UnsupportedOperationException("Unknown implementation type: " + regionId);
		}
		return srv;
	}
}