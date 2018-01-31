package com.cencosud.middleware.profile.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.profile.dto.UserPreferencePostRequest;
import com.cencosud.middleware.profile.dto.WongUserProfileDto;
import com.cencosud.middleware.profile.exception.UserProfileException;
import com.cencosud.middleware.profile.model.JumboUserProfile;
import com.cencosud.middleware.profile.model.UserPreference;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.model.WongUserProfile;
import com.cencosud.middleware.profile.repository.ProfileRepository;
import com.cencosud.middleware.profile.service.UserProfileService;

/**
 * 
 * Esta clase corresponde a la implementación de la región 2, Jumbo Chile.
 *
 */
@Service
public class UserProfileR2ServiceImpl implements UserProfileService {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileR2ServiceImpl.class);

	// Region2 = JUMBO CHILE
	private static final String REGION_ID = "r2";

	private static final String MALE = "male";

	private static final String FEMALE = "female";

	private static final String MESSAGE_UNSUPPORTED = "Unsupported method";

	@Autowired
	ProfileRepository repo;

	@Override
	public String getRegionId() {
		return REGION_ID;
	}

	@Override
	public JumboUserProfile findById(String userId) {
		logger.info("Find user profile by id (email)");
		JumboUserProfile jumboProfile = repo.findById(userId);
		if (jumboProfile != null && StringUtils.isNotEmpty(jumboProfile.getUserId())) {
			UserPreference userPreferences = repo.getUserPreference(jumboProfile.getUserId());
			if (userPreferences != null) {
				jumboProfile.setDefaultSalesChannel(userPreferences.getDefaultSalesChannel());
				jumboProfile.setDeliveryAddress(userPreferences.getDeliveryAddress());
				jumboProfile.setDeliveryMode(userPreferences.getDeliveryMode());
			}
		}
		return jumboProfile;
	}

	@Override
	public WongUserProfile saveOrUpdate(WongUserProfile userProfile) throws UserProfileException {

		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public UserProfile update(UserProfile request, String email) throws UserProfileException {
		logger.info("Method Update user profile by email");
		JumboUserProfile user = new JumboUserProfile();

		if (email == null)
			return user;

		JumboUserProfile userProfile = null;

		if (request instanceof JumboUserProfile)
			userProfile = (JumboUserProfile) request;
		else
			throw new UserProfileException("Object is not instance JumboUserProfile", null);

		logger.info("Find user profile in Vtex");
		user = this.findById(email);

		logger.info("Setting changes to user profile");
		if (StringUtils.isNotEmpty(userProfile.getFirstName()))
			user.setFirstName(userProfile.getFirstName());

		if (StringUtils.isNotEmpty(userProfile.getLastName()))
			user.setLastName(userProfile.getLastName());

		if (com.cencosud.middleware.profile.utils.StringUtils.isValidDate(userProfile.getBirthDate()))
			user.setBirthDate(userProfile.getBirthDate());

		if (StringUtils.isNotEmpty(userProfile.getGender())
				&& (MALE.equalsIgnoreCase(userProfile.getGender()) || FEMALE.equalsIgnoreCase(userProfile.getGender())))
			user.setGender(userProfile.getGender().toLowerCase());

		if (StringUtils.isNotEmpty(userProfile.getHomePhone()))
			user.setHomePhone(userProfile.getHomePhone());

		logger.info("Update user profile");
		repo.updateUserProfile(user, email);

		return user;
	}

	@Override
	public void delete(String userProfileId) throws UserProfileException {

		throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
	}

	@Override
	public WongUserProfileDto findByIdAndFields(String profileId, String fields) throws UserProfileException {

		throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
	}

	@Override
	public void savePreferences(UserPreferencePostRequest request) {
		UserPreference userPreference = new UserPreference(request.getDefaultSalesChannel(), request.getUserId(), request.getDeliveryMode(), request.getDeliveryAddress());
		repo.postPreferences(userPreference);
	}

}
