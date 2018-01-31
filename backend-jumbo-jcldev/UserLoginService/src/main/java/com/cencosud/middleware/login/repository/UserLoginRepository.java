package com.cencosud.middleware.login.repository;

import com.cencosud.middleware.login.model.UserMigration;
import com.cencosud.middleware.login.model.UserLogin;

public interface UserLoginRepository {

	String getToken();

	UserLogin loginUser(String token, String email, String password) throws Exception;
	
	UserMigration getMigrationInfo(String rut);

}
