package com.cencosud.middleware.login.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.login.service.UserLoginService;

@Service
public class LoginServiceFactory {

	@Autowired
	private List<UserLoginService> userLoginServices;

	private final Map<String, UserLoginService> servicesCache = new HashMap<>();

	@PostConstruct
	public void initMyRepositoryCache() {
		for (UserLoginService service : userLoginServices) {
			servicesCache.put(service.getRegionId(), service);
		}
	}

	public UserLoginService getService(String regionId) {
		UserLoginService userLoginService = servicesCache.get(regionId);
		if (userLoginService == null) {
			throw new RuntimeException("Unknown implementation type: " + regionId);
		}
		return userLoginService;
	}
}
