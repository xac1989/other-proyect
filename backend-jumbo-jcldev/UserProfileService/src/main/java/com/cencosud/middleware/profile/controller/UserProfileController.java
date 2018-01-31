package com.cencosud.middleware.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.profile.annotation.Loggable;
import com.cencosud.middleware.profile.dto.UpdateProfileGenericRequest;
import com.cencosud.middleware.profile.dto.UserPreferencePostRequest;
import com.cencosud.middleware.profile.dto.UserProfileDto;
import com.cencosud.middleware.profile.exception.UserProfileException;
import com.cencosud.middleware.profile.factory.ProfileServiceFactory;
import com.cencosud.middleware.profile.mapper.UserProfileMapper;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.model.WongUserProfile;

@RestController
@RequestMapping(path = { "/{region}/v1/profile", "/{region}/v1/profile/" }, produces = "application/json; charset=UTF-8")
public class UserProfileController {

	@Autowired
	private ProfileServiceFactory serviceFactory;

	@Loggable
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public WongUserProfile save(@PathVariable("region") String region, @RequestBody WongUserProfile profile)
			throws UserProfileException {
		return serviceFactory.getService(region).saveOrUpdate(profile);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("region") String region,
			@RequestParam(required = true) String profileId) throws UserProfileException {
		serviceFactory.getService(region).delete(profileId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.GET)
	public UserProfileDto findById(@PathVariable("region") String region,
			@RequestParam(required = true) String profileId) {
		UserProfile userProfile = serviceFactory.getService(region).findById(profileId);
		return UserProfileMapper.mapToUserProfileDto(userProfile, region);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserProfileDto update(@PathVariable("region") String region, @RequestBody UpdateProfileGenericRequest profile,
			@RequestParam(required = false) String email)
			throws UserProfileException {
		
		UserProfile request = UserProfileMapper.mapToUserProfile(profile, region);
		UserProfile userProfile = serviceFactory.getService(region).update(request, email);
		return UserProfileMapper.mapToUserProfileDto(userProfile, region);
	}

	@Loggable
	@RequestMapping(path = "/specific", method = RequestMethod.GET)
	public UserProfileDto findByIdAndFields(@PathVariable("region") String region,
			@RequestParam(required = true) String profileId, @RequestParam(required = false) String fields)
			throws UserProfileException {
		UserProfileDto userProfile = serviceFactory.getService(region).findByIdAndFields(profileId, fields);
		return userProfile;
	}

	@Loggable
	@RequestMapping(path = "/preferences", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> savePreferences(@PathVariable("region") String region,
			@RequestBody UserPreferencePostRequest request) throws UserProfileException{
		serviceFactory.getService(region).savePreferences(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
