package com.scanandgo.middleware.login.repository;

import com.scanandgo.middleware.login.exception.LoginServiceException;
import com.scanandgo.middleware.login.exception.UnauthorizedException;
import com.scanandgo.middleware.login.model.LoggedUser;

public interface TwitterRepository {

	LoggedUser getUser(String oauthKey, String oauthSecretKey, String app) throws LoginServiceException, UnauthorizedException;
	
}
