package com.cencosud.mobile.service;

import com.cencosud.middleware.category.model.UserProfile;
import com.cencosud.mobile.exceptions.NotFoundException;

public interface UserProfileApiService {
      public UserProfile profileGet(String profileId)
      throws NotFoundException;
      
      public UserProfile profilePut(UserProfile profile);

}
