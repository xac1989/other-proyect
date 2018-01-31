package com.cencosud.middleware.profile.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.profile.annotation.Loggable;
import com.cencosud.middleware.profile.configuration.ApplicationConfig;
import com.cencosud.middleware.profile.dto.UserPreferencePostRequest;
import com.cencosud.middleware.profile.dto.WongUserProfileDto;
import com.cencosud.middleware.profile.exception.UserProfileException;
import com.cencosud.middleware.profile.model.Configuration;
import com.cencosud.middleware.profile.model.UserConfiguration;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.model.WongUserProfile;
import com.cencosud.middleware.profile.model.enums.LocalConfigurationsEnum;
import com.cencosud.middleware.profile.repository.UserProfileRepository;
import com.cencosud.middleware.profile.service.UserProfileService;

/**
 * 
 * Esta clase corresponde a la implementación 
 * de la región 1, Wong Perú.
 *
 */
@Service
public class UserProfileR1ServiceImpl implements UserProfileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileR1ServiceImpl.class);
	private static final String DEFAULT_DOC_TYPE = "DNI";
	
    // Region1 = WONG PERU
	private static final String REGION_ID = "r1";

	@Autowired
	private UserProfileRepository repository;

	@Autowired
	private ApplicationConfig config;

	@Loggable
	@Override
	public WongUserProfile saveOrUpdate(WongUserProfile userProfileToSave) {
		LOGGER.debug("Saving or updating user profile id:::" + userProfileToSave.getId());
		if (StringUtils.isEmpty(userProfileToSave.getId())) {
			LOGGER.info("Social login 201");
		} else {
			LOGGER.info("Social login 200");
		}
		WongUserProfile profileFromDB = (WongUserProfile) this.findById(userProfileToSave.getId());

		/**
		 * RN: Cada vez que se acceda por cualquier red social, se debe guardar
		 * en base de datos los valores de los campos que provengan de las redes
		 * sociales (nombre, fecha de nacimiento, teléfono, género), siempre y
		 * cuando estos valores no hayan sido modificados desde el perfil de
		 * usuario, en ese caso no se deben almacenar.
		 */

		if (profileFromDB != null) {
			LOGGER.debug("Got user from bd");
			if (!profileFromDB.isModified()) {
				LOGGER.debug("The user profile has not been modified by user, setting up its data to persist :)");
				profileFromDB.setDisplayName(userProfileToSave.getDisplayName());
				profileFromDB.setFullName(userProfileToSave.getFullName() == null ? "" : userProfileToSave.getFullName());
				profileFromDB.setBirthDate(userProfileToSave.getBirthDate());
				profileFromDB.setPhone(userProfileToSave.getPhone() == null ? "" : userProfileToSave.getPhone());
				profileFromDB.setSex(userProfileToSave.getSex() == null ? "" : userProfileToSave.getSex());

				LOGGER.debug("Assigning and saving display name from user social net ::: {}", userProfileToSave.getDisplayName());
				return repository.save(profileFromDB);
			} else {
				LOGGER.debug("User has been modified before, nothing to save :(");
				return profileFromDB;
			}
		} else {
			LOGGER.debug("User is new for us, welcome!! :D");
			userProfileToSave.setDocumentType(DEFAULT_DOC_TYPE);
			String document = userProfileToSave.getDocument();
			userProfileToSave.setDocument(document == null ? "" : document);
			if (userProfileToSave.getConfigurations() == null) {
				LOGGER.debug("Assigning default notifications, creating configuration");
				UserConfiguration userConfiguration = new UserConfiguration();
				userConfiguration.setLocalNotifications(getDefaultLocalNotification());
				userProfileToSave.setConfigurations(userConfiguration);
			} else if (userProfileToSave.getConfigurations().getLocalNotifications() == null
					|| userProfileToSave.getConfigurations().getLocalNotifications().isEmpty()) {
				LOGGER.debug("Assigning default notifications, notifications from configurations null");
				userProfileToSave.getConfigurations().setLocalNotifications(this.getDefaultLocalNotification());
			}
			userProfileToSave.setCreationDate(new Date());
			LOGGER.debug("Saving user profile into database");
			return repository.save(userProfileToSave);
		}
	}
	
	@Loggable
	@Override
	public WongUserProfile update(UserProfile request, String email) throws UserProfileException {
		
		WongUserProfile userProfile = null;
		
		if (request instanceof WongUserProfile)
			userProfile = (WongUserProfile) request;
		else
			throw new UserProfileException("Object is not instance WongUserProfile", null);
		
		LOGGER.debug("Updating user profile id:::" + userProfile.getId());
		if(StringUtils.isEmpty(userProfile.getId()))
			throw new UserProfileException("Id field is required", null);
		
		
		WongUserProfile profile = repository.findOne(userProfile.getId());
	
		if (profile != null) {
			if (userProfile.getDocument() != null) {
				if (!userProfile.getDocument().matches(this.config.getDocumentValidateRegex())
						&& !userProfile.getDocument().isEmpty()) {
					throw new UserProfileException("Document must have 7 or 8 characters and it must be numeric", null);
				}
				LOGGER.debug("Setting document into profile :::" + userProfile.getDocument());
				profile.setDocument(userProfile.getDocument());
			}
	
			if (userProfile.getFullName() != null) {
				LOGGER.debug("Setting full name");
				profile.setFullName(userProfile.getFullName());
				profile.setDisplayName(getFirstWord(userProfile.getFullName()));
				profile.setModified(true);
			}
	
			if (userProfile.getBirthDate() != null) {
				LOGGER.debug("Setting birth date");
				profile.setBirthDate(userProfile.getBirthDate());
				profile.setModified(true);
			}
	
			if (userProfile.getSex() != null) {
				LOGGER.debug("Setting sex");
				profile.setSex(userProfile.getSex());
				profile.setModified(true);
			}
	
			if (userProfile.getPhone() != null) {
				LOGGER.debug("Setting phone number");
				profile.setPhone(userProfile.getPhone());
				profile.setModified(true);
			}

			LOGGER.debug("Favorites: {}", userProfile.getFavorites());
			if (userProfile.getFavorites() != null) {
			    LOGGER.debug("Categories: {}", userProfile.getFavorites().getCategories());
			}
			if (userProfile.getFavorites() != null && userProfile.getFavorites().getCategories() != null) {
				LOGGER.debug("Setting favorites categories");
				profile.getFavorites().setCategories(new ArrayList<String>());
				for(String category: userProfile.getFavorites().getCategories()){
					if(category != null && !"".equals(category)){
						profile.getFavorites().getCategories().add(category);
					}
				}
				
			}
	
			if (userProfile.getConfigurations() != null) {
				if (userProfile.getConfigurations().getLocalNotifications() != null
						&& !userProfile.getConfigurations().getLocalNotifications().isEmpty()) {
					LOGGER.debug("Setting configurations into profile");
					profile.setConfigurations(userProfile.getConfigurations());
				}
			}
	
			LOGGER.debug("Saving profile into database");
			repository.save(profile);
		}
		return profile;
	}

	private String getFirstWord(String fullWord) {
		if (fullWord == null) {
			return "";
		}
		return fullWord.split(" ")[0];
	}

	@Override
	public void delete(String userProfileId) {
		LOGGER.debug("Deleting user profile by id :::" + userProfileId);
		repository.delete(userProfileId);
	}

	@Override
	public UserProfile findById(String categoryId) {
		LOGGER.debug("Searching user profile by id :::" + categoryId);
		return repository.findOne(categoryId);
	}

	@Override
	public WongUserProfileDto findByIdAndFields(String profileId, String fields) {
		LOGGER.debug("Searching user profile by id ::: {}", profileId);
		WongUserProfile userProfile = repository.findOne(profileId);
		if (userProfile == null) {
			userProfile = new WongUserProfile();
		}
		WongUserProfileDto dto = new WongUserProfileDto(userProfile);
		clearFields(dto, fields);
		return dto;
	}

	private List<Configuration> getDefaultLocalNotification() {
		LOGGER.debug("Getting default local notification");
		List<Configuration> result = new ArrayList<>(LocalConfigurationsEnum.values().length);
		for (LocalConfigurationsEnum currentConf : LocalConfigurationsEnum.values()) {
			result.add(new Configuration(currentConf.getId(), currentConf.getValue()));
		}

		return result;
	}

	private void clearFields(WongUserProfileDto userProfile, String fields) {
		if (StringUtils.isNotEmpty(fields)) {
			LOGGER.trace("Fields available: ", fields);
			Set<String> realFields = new HashSet<>(Arrays.asList(fields.split(",")));
			realFields.add("id");

			Class<WongUserProfileDto> clazz = WongUserProfileDto.class;
			Field[] allFields = clazz.getDeclaredFields();
			List<Field> nullableFields = new ArrayList<>();
			for (Field field : allFields) {
				if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {

					if (!realFields.contains(field.getName())) {
						nullableFields.add(field);
						LOGGER.trace("Field to nullify value: {}", field.getName());
					}
				}
			}
			for (Field field : nullableFields) {
				try {
					if (!field.getType().isPrimitive()) {
						field.setAccessible(true);
						field.set(userProfile, null);
					}
				} catch (IllegalArgumentException e) {
					LOGGER.error("Argument Exception {}", e);
				} catch (IllegalAccessException e) {
					LOGGER.error("Access Exception {}", e);
				}
			}
		}
	}

	@Override
	public String getRegionId() {
		return REGION_ID;
	}

	@Override
	public void savePreferences(UserPreferencePostRequest request) throws UserProfileException{
		
		throw new UnsupportedOperationException("Unsupported method");
	}

}
