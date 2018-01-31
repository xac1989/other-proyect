package com.cencosud.middleware.profile.mapper;

import com.cencosud.middleware.profile.dto.JumboUserProfileDto;
import com.cencosud.middleware.profile.dto.UpdateProfileGenericRequest;
import com.cencosud.middleware.profile.dto.UserProfileDto;
import com.cencosud.middleware.profile.dto.WongUserProfileDto;
import com.cencosud.middleware.profile.model.JumboUserProfile;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.model.WongUserProfile;

public class UserProfileMapper {

	public static UserProfileDto mapToUserProfileDto(UserProfile userProfile, String region) {
		//FIXME codigo muy sucio. Requiere una implementacion mas desacoplada.
		UserProfileDto result = null;
		switch (region) {
		case "r1":
			result = new WongUserProfileDto((WongUserProfile)userProfile);
			break;
		case "r2":
			result = new JumboUserProfileDto((JumboUserProfile)userProfile);
			break;
		}
		return result;
	}

	public static UserProfile mapToUserProfile(UpdateProfileGenericRequest profile, String region) {
		UserProfile result = null;
		switch (region) {
		case "r1":
			result = new WongUserProfile(profile);
			break;
		case "r2":
			result = new JumboUserProfile(profile);
			break;
		}
		return result;
	}
}
