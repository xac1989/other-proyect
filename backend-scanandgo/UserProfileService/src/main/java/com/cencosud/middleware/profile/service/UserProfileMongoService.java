package com.cencosud.middleware.profile.service;

import com.cencosud.middleware.profile.model.UserProfile;

public interface UserProfileMongoService {
	
	UserProfile save(UserProfile userProfile);
	
	UserProfile update(UserProfile userProfile);
	
	void delete(String userProfileId);
	
	UserProfile findById(String categoryId);
}
