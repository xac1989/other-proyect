package com.cencosud.middleware.profile.service;

import com.cencosud.middleware.profile.model.UserWhiteList;

public interface UserWhiteListMongoService {
	
	UserWhiteList findUserByEmail(String email);
	
	UserWhiteList findUserByRut(String rut);
}
