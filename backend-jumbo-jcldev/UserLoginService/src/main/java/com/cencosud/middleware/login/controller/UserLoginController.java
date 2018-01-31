package com.cencosud.middleware.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.login.annotation.Loggable;
import com.cencosud.middleware.login.dto.UserLoginDto;
import com.cencosud.middleware.login.dto.UserMigrationDto;
import com.cencosud.middleware.login.factory.LoginServiceFactory;
import com.cencosud.middleware.login.model.UserLoginRequest;
import com.cencosud.middleware.login.model.UserMigration;

@RestController
@RequestMapping("/{region}/v1")
public class UserLoginController {

	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

	@Autowired
	private LoginServiceFactory loginServiceFactory;

	@Loggable
	@RequestMapping(method = RequestMethod.POST,value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserLoginDto loginUser(@RequestBody UserLoginRequest request, @PathVariable("region") String region)
			throws Exception {
		logger.info("Login to " + request.getUserName());

		UserLoginDto response = loginServiceFactory.getService(region).loginUser(request.getUserName(),
				request.getPassword());
		return response;
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/rut", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserMigrationDto getMigrationInfo(@RequestParam(required = true) String rut,
			@PathVariable("region") String region) {
		UserMigration janis = loginServiceFactory.getService(region).getMigrationInfo(rut);
		return new UserMigrationDto(janis.getCode(), janis.getMessage(), janis.getSuccess(), janis.getEmail(),
				janis.getNeedsMigration());

	}
}
