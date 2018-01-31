package com.cencosud.middleware.login.service;

import com.cencosud.middleware.login.dto.UserLoginDto;
import com.cencosud.middleware.login.model.UserMigration;

public interface UserLoginService {

	UserLoginDto loginUser(String email, String password) throws Exception;
	
	String getRegionId();
	
	UserMigration getMigrationInfo(String rut);
}
