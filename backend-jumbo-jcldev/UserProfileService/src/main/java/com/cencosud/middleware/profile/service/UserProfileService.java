package com.cencosud.middleware.profile.service;

import com.cencosud.middleware.profile.dto.UserPreferencePostRequest;
import com.cencosud.middleware.profile.dto.WongUserProfileDto;
import com.cencosud.middleware.profile.exception.UserProfileException;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.model.WongUserProfile;

public interface UserProfileService {

	WongUserProfile saveOrUpdate(WongUserProfile c) throws UserProfileException;

	UserProfile update(UserProfile userProfile, String email) throws UserProfileException;

	void delete(String userProfileId) throws UserProfileException;

	UserProfile findById(String profileId);

	WongUserProfileDto findByIdAndFields(String profileId, String fields) throws UserProfileException;

	String getRegionId();
	
	void savePreferences(UserPreferencePostRequest request) throws UserProfileException;
}
