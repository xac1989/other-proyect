package com.cencosud.mobile.service;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.model.UserProfile;

public interface UserProfileApiService {
	
	public UserProfile profileGet(String profileId, String region, String version) throws NotFoundException;

}
