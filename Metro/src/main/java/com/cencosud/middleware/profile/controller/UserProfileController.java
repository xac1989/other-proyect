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

import com.cencosud.middleware.profile.annotation.Loggable;
import com.cencosud.middleware.profile.model.UserProfileMetro;
import com.cencosud.middleware.profile.service.UserProfileService;

@RestController
@RequestMapping(path = { "/profile", "/profile/" }, produces = "application/json; charset=UTF-8")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@Loggable
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserProfileMetro save(@RequestBody UserProfileMetro profile) {
		return userProfileService.save(profile);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, path = "/dni")
	public UserProfileMetro updateDNI(@RequestBody UserProfileMetro profile) {
		return userProfileService.updateDNI(profile);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(@RequestBody UserProfileMetro profile) {
		userProfileService.delete(profile.getUserProfileId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserProfileMetro findById(@RequestParam(required = true) String profileId) {
		return userProfileService.findById(profileId);
	}
}
