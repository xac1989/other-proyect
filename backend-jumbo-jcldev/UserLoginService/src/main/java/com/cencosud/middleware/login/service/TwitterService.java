package com.cencosud.middleware.login.service;

import com.cencosud.middleware.login.exception.LoginServiceException;
import com.cencosud.middleware.login.exception.UnauthorizedException;
import com.cencosud.middleware.login.model.LoggedUser;

public interface TwitterService {

	public LoggedUser getUserInfo(String oauthKey, String oauthSecretKey, String app) throws LoginServiceException, UnauthorizedException;
}
