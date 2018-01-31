package com.cencosud.middleware.cencosud.service;

import com.cencosud.middleware.cencosud.model.CencosudServiceRequest;
import com.cencosud.middleware.cencosud.model.UserProfileInfo;

public interface UserLoginCencosudService {
	UserProfileInfo loginCencosud(CencosudServiceRequest request);
}
