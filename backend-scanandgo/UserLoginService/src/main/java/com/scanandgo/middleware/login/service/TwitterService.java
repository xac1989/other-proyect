package com.scanandgo.middleware.login.service;

import com.scanandgo.middleware.login.exception.LoginServiceException;
import com.scanandgo.middleware.login.exception.UnauthorizedException;
import com.scanandgo.middleware.login.model.LoggedUser;

public interface TwitterService {

	public LoggedUser getUserInfo(String oauthKey, String oauthSecretKey, String app) throws LoginServiceException, UnauthorizedException;
}
