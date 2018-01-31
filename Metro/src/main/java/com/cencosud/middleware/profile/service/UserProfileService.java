package com.cencosud.middleware.profile.service;

import com.cencosud.middleware.profile.model.UserProfileMetro;

public interface UserProfileService {

	UserProfileMetro save(UserProfileMetro userProfile);

	UserProfileMetro updateDNI(UserProfileMetro userProfile);

	void delete(String userProfileId);

	UserProfileMetro findById(String userProfileId);

}
