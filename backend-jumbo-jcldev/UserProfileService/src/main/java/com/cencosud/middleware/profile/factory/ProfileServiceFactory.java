package com.cencosud.middleware.profile.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.profile.service.UserProfileService;

@Service
public class ProfileServiceFactory {
	
	@Autowired
	private List<UserProfileService> services;

	private final Map<String, UserProfileService> servicesCache = new HashMap<>();

	@PostConstruct
	public void initMyRepositoryCache() {
		for (UserProfileService service : services) {
			servicesCache.put(service.getRegionId(), service);
			System.out.println(service.getRegionId());
		}
	}

	public UserProfileService getService(String regionId) {
		UserProfileService srv = servicesCache.get(regionId);
		if (srv == null) {
			throw new RuntimeException("Unknown implementation type: " + regionId);
		}
		return srv;
	}
}
