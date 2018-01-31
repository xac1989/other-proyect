package com.cencosud.mobile.service;

import com.cencosud.mobile.model.category.UserProfile;
import com.cencosud.mobile.model.profile.wong.ProfileUpdateRequest;

public interface UserProfileApiService {

    UserProfile getProfile(String profileId);
    UserProfile updateProfile(ProfileUpdateRequest profile);
}
