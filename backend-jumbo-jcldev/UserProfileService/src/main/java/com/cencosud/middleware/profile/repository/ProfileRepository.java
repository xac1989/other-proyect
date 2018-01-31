package com.cencosud.middleware.profile.repository;

import com.cencosud.middleware.profile.model.JumboUserProfile;
import com.cencosud.middleware.profile.model.UserPreference;

public interface ProfileRepository {

	JumboUserProfile findById(String id);
	
	void postPreferences(UserPreference request);
	
	UserPreference getUserPreference(String idUsuario);

	void updateUserProfile(JumboUserProfile profile, String email);

}
