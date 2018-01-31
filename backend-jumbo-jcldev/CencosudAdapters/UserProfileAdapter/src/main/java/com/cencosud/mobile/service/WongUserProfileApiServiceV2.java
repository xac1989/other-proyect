package com.cencosud.mobile.service;

import com.cencosud.mobile.dto.profile.jumbo.UpdateProfileGenericRequest;
import com.cencosud.mobile.model.profile.jumbo.SalesChannel;
import com.cencosud.mobile.model.profile.jumbo.UserProfile;

public interface WongUserProfileApiServiceV2 {

	UserProfile getUserProfile(String profileId, String region);

    UserProfile putProfile(UpdateProfileGenericRequest profile, String region, String email);
    
    SalesChannel getSalesChannel(Integer idSalesChannel);
}
