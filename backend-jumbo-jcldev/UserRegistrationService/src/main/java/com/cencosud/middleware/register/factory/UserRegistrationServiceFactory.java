package com.cencosud.middleware.register.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.register.service.UserRegistrationService;


@Service
public class UserRegistrationServiceFactory {
	@Autowired
	List<UserRegistrationService> services;

	private final Map<String, UserRegistrationService> servicesCache = new HashMap<>();

	@PostConstruct
	public void initMyRepositoryCache() {
		for (UserRegistrationService service : services) {
			servicesCache.put(service.getRegionId(), service);
			System.out.println(service.getRegionId());
		}
	}

	public UserRegistrationService getService(String regionId) {
		UserRegistrationService service = servicesCache.get(regionId);
		if (service == null) {
			throw new RuntimeException("Unknown implementation type: " + regionId);
		}
		return service;
	}
}
