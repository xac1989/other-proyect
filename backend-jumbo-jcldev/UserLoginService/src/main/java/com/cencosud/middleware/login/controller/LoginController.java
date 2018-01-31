package com.cencosud.middleware.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.login.annotation.Loggable;
import com.cencosud.middleware.login.exception.LoginServiceException;
import com.cencosud.middleware.login.exception.UnauthorizedException;
import com.cencosud.middleware.login.model.LoggedUser;
import com.cencosud.middleware.login.model.TwitterLoginInfo;
import com.cencosud.middleware.login.service.TwitterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	TwitterService service;

	@Deprecated
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/twitter/{oauthKey}/{oauthSecretKey}", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoggedUser loginWithTwitter(@PathVariable String oauthKey, @PathVariable String oauthSecretKey,
			@RequestParam(required = false) String app) throws LoginServiceException, UnauthorizedException {
		logger.debug("oauthKey: " + oauthKey);
		logger.debug("oauthSecretKey: " + oauthSecretKey);
		logger.debug("app: " + app);
		return service.getUserInfo(oauthKey, oauthSecretKey, app);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.POST, value = "/twitter", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoggedUser loginWithTwitter(@RequestBody TwitterLoginInfo info)
			throws LoginServiceException, UnauthorizedException {
		logger.debug("oauthKey: " + info.getAccessToken());
		logger.debug("oauthSecretKey: " + info.getAccessSecretToken());
		logger.debug("app: " + info.getApplication());
		return service.getUserInfo(info.getAccessToken(), info.getAccessSecretToken(), info.getApplication());
	}
}
