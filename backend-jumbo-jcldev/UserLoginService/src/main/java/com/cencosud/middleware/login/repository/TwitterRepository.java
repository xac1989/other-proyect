package com.cencosud.middleware.login.repository;

import com.cencosud.middleware.login.exception.LoginServiceException;
import com.cencosud.middleware.login.exception.UnauthorizedException;
import com.cencosud.middleware.login.model.LoggedUser;

public interface TwitterRepository {

	LoggedUser getUser(String oauthKey, String oauthSecretKey, String app) throws LoginServiceException, UnauthorizedException;
	
}
