package com.cencosud.middleware.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.profile.exception.UserProfileException;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.model.UserWhiteList;
import com.cencosud.middleware.profile.service.UserProfileMongoService;
import com.cencosud.middleware.profile.service.UserWhiteListMongoService;

@RestController
public class UserProfileController {
	
	@Autowired
	private UserProfileMongoService mongoService;
	
	@Autowired
	private UserWhiteListMongoService mongoWhiteListService;
	
	@RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, path="/profile")
	public UserProfile save(@RequestBody UserProfile profile) {
		return mongoService.save(profile);		
	}

	@RequestMapping(method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE, path="/profile")
	public ResponseEntity<Object> delete(@RequestParam(required=true) String profileId) {		
		mongoService.delete(profileId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, path="/profile")
	public UserProfile findById(@RequestParam(required=true) String profileId) {
		UserProfile userProfile =  mongoService.findById(profileId);
		return userProfile;
	}

	@RequestMapping(method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, path="/profile")
	public UserProfile update(@RequestBody UserProfile profile) throws UserProfileException {
		return mongoService.update(profile);		
	}
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, path="/whiteList/findByEmail")
	public UserWhiteList findUserWhiteListEmail(@RequestParam(required=true) String email) {
		UserWhiteList userWhiteList =  mongoWhiteListService.findUserByEmail(email);
		return userWhiteList;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, path="/whiteList/findByRut")
	public UserWhiteList findUserWhiteListRut(@RequestParam(required=true) String rut) {
		UserWhiteList userWhiteList =  mongoWhiteListService.findUserByRut(rut);
		return userWhiteList;
	}
	
}
