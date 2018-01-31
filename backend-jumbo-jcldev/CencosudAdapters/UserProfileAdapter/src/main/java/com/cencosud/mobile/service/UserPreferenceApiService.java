package com.cencosud.mobile.service;

import com.cencosud.mobile.dto.profile.jumbo.UpdateProfileGenericRequest;
import com.cencosud.mobile.dto.profile.jumbo.UserPreferencePostRequest;
import com.cencosud.mobile.model.profile.jumbo.SalesChannel;
import com.cencosud.mobile.model.profile.jumbo.UserProfile;

public interface UserPreferenceApiService {

    UserProfile getUserProfile(String profileId, String region);

    UserProfile putProfile(UpdateProfileGenericRequest profile, String region, String email);
    
    void savePreferences(UserPreferencePostRequest request);
    
    SalesChannel getSalesChannel(Integer idSalesChannel);
    
    boolean getNeedsMigration(String rut, String region);
}
