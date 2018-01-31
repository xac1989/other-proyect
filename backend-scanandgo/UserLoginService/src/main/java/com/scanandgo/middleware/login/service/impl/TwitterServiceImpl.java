package com.scanandgo.middleware.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scanandgo.middleware.login.exception.LoginServiceException;
import com.scanandgo.middleware.login.exception.UnauthorizedException;
import com.scanandgo.middleware.login.model.LoggedUser;
import com.scanandgo.middleware.login.repository.TwitterRepository;
import com.scanandgo.middleware.login.service.TwitterService;

@Service
public class TwitterServiceImpl implements TwitterService {

	@Autowired
	TwitterRepository repo;

	@Override
	public LoggedUser getUserInfo(String oauthKey, String oauthSecretKey, String app) throws LoginServiceException, UnauthorizedException {
		return repo.getUser(oauthKey, oauthSecretKey, app);
	}

}
