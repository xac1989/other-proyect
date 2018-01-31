package com.scanandgo.middleware.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scanandgo.middleware.login.exception.LoginServiceException;
import com.scanandgo.middleware.login.exception.UnauthorizedException;
import com.scanandgo.middleware.login.model.LoggedUser;
import com.scanandgo.middleware.login.model.TwitterLoginInfo;
import com.scanandgo.middleware.login.service.TwitterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	TwitterService service;

	@Deprecated
	@RequestMapping(method = RequestMethod.GET, value = "/twitter/{oauthKey}/{oauthSecretKey}", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoggedUser loginWithTwitter(@PathVariable String oauthKey,
			@PathVariable String oauthSecretKey, @RequestParam(required=false) String app) throws LoginServiceException, UnauthorizedException {
		if (logger.isDebugEnabled()) {
			logger.debug("oauthKey: " + oauthKey);
			logger.debug("oauthSecretKey: " + oauthSecretKey);
			logger.debug("app: " + app);
		}
		return service.getUserInfo(oauthKey, oauthSecretKey, app);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/twitter", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoggedUser loginWithTwitter(@RequestBody TwitterLoginInfo info) throws LoginServiceException, UnauthorizedException {
		if (logger.isDebugEnabled()) {
			logger.debug("oauthKey: " + info.getAccessToken());
			logger.debug("oauthSecretKey: " + info.getAccessSecretToken());
			logger.debug("app: " + info.getApplication());
		}
		return service.getUserInfo(info.getAccessToken(), info.getAccessSecretToken(), info.getApplication());
	}
}
