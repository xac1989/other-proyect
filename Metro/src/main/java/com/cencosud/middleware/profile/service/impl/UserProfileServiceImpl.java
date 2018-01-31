package com.cencosud.middleware.profile.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.cencosud.middleware.profile.annotation.Loggable;
import com.cencosud.middleware.profile.configuration.ApplicationConfig;
import com.cencosud.middleware.profile.model.Configuration;
import com.cencosud.middleware.profile.model.UserConfiguration;
import com.cencosud.middleware.profile.model.UserProfileMetro;
import com.cencosud.middleware.profile.model.enums.LocalConfigurationsEnum;
import com.cencosud.middleware.profile.service.UserProfileService;

/**
 * 
 * Esta clase corresponde a la implementación de la región 1, Metro Perú.
 *
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private ApplicationConfig config;

	private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

	private DynamoDBMapper mapper;
	
	private DynamoDBMapperConfig mapperConfig;

	@PostConstruct
	public void init() {
		
		 mapperConfig = new DynamoDBMapperConfig.Builder().withTableNameOverride(TableNameOverride.withTableNameReplacement("pe_jumbo_category")).build();
		mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(config.getAwsProperties().getAccessKey(), config.getAwsProperties().getSecretKey()))).withRegion(config.getAwsProperties().getRegion()).build(),mapperConfig);
	}

	@Loggable
	@Override
	public UserProfileMetro save(UserProfileMetro userProfileToSave) {
		logger.debug("Saving or updating user profile id:::" + userProfileToSave.getId());
		if (StringUtils.isEmpty(userProfileToSave.getUserProfileId())) {
			logger.info("Social login 201");
		} else {
			logger.info("Social login 200");
		}
		UserProfileMetro profileFromDB = this.findById(userProfileToSave.getUserProfileId());

		if (profileFromDB != null) {
			logger.debug("Got user from bd");
			if(userProfileToSave.getBirthDate() != null) {
				profileFromDB.setBirthDate(userProfileToSave.getBirthDate());
			}
			if(userProfileToSave.getDisplayName() != null) {
				profileFromDB.setDisplayName(userProfileToSave.getDisplayName());
			}
			if(userProfileToSave.getDocument() != null) {
				profileFromDB.setDocument(userProfileToSave.getDocument());
			}
			if(userProfileToSave.getEmail() != null) {
				profileFromDB.setEmail(userProfileToSave.getEmail());
			}
			if(userProfileToSave.getFullName() != null) {
				profileFromDB.setFullName(userProfileToSave.getFullName());
			}
			if(userProfileToSave.getPhoneNumber() != null) {
				profileFromDB.setPhoneNumber(userProfileToSave.getPhoneNumber());
			}
			if(userProfileToSave.getProfilePicture() != null) {
				profileFromDB.setProfilePicture(userProfileToSave.getProfilePicture());
			}
			if(userProfileToSave.getSocialNetworkId() != null) {
				profileFromDB.setSocialNetworkId(userProfileToSave.getSocialNetworkId());
			}
			if(userProfileToSave.getUserProfileId() != null) {
				profileFromDB.setUserProfileId(userProfileToSave.getUserProfileId());
			}
			if(userProfileToSave.getGender() != null) {
				profileFromDB.setGender(userProfileToSave.getGender());
			}
			if(userProfileToSave.getAttributes() != null) {
				profileFromDB.setAttributes(userProfileToSave.getAttributes());
			}
			if(userProfileToSave.getConfigurations() != null) {
				profileFromDB.setAttributes(userProfileToSave.getAttributes());
			}
			if(userProfileToSave.getDocumentType() != null) {
				profileFromDB.setDocumentType(userProfileToSave.getDocumentType());
			}
			if(userProfileToSave.getFavorites().getCategories()!=null){
				
			}
			
			mapper.save(profileFromDB);
			return this.findById(profileFromDB.getUserProfileId());
		} else {
			logger.debug("User is new for us, welcome!! :D");
			UserProfileMetro newUser = new UserProfileMetro(userProfileToSave);

			if (newUser.getConfigurations() == null) {
				logger.debug("Assigning default notifications, creating configuration");
				UserConfiguration userConfiguration = new UserConfiguration();
				userConfiguration.setLocalNotifications(getDefaultLocalNotification());
				newUser.setConfigurations(userConfiguration);
			} else if (newUser.getConfigurations().getLocalNotifications() == null
					|| newUser.getConfigurations().getLocalNotifications().isEmpty()) {
				logger.debug("Assigning default notifications, notifications from configurations null");
				newUser.getConfigurations().setLocalNotifications(this.getDefaultLocalNotification());
			}
			logger.debug("Saving user profile into database");
			mapper.save(newUser);
			return this.findById(newUser.getUserProfileId());
		}
	}

	private List<Configuration> getDefaultLocalNotification() {
		logger.debug("Getting default local notification");
		List<Configuration> result = new ArrayList<>(LocalConfigurationsEnum.values().length);
		for (LocalConfigurationsEnum currentConf : LocalConfigurationsEnum.values()) {
			result.add(new Configuration(currentConf.getId(), currentConf.getValue()));
		}

		return result;
	}

	@Override
	public void delete(String profileId) {
		logger.info("Deleting user profile by id :::" + profileId);
		UserProfileMetro userProfile = this.findById(profileId);
		if(userProfile != null) {
			mapper.delete(userProfile);	
		}
	}

	@Override
	public UserProfileMetro findById(String profileId) {
		logger.info("Searching user profile by id :::" + profileId);
		return mapper.load(UserProfileMetro.class, profileId);
	}

	@Override
	public UserProfileMetro updateDNI(UserProfileMetro userProfile) {
		UserProfileMetro profileFromDB = this.findById(userProfile.getUserProfileId());
		if (profileFromDB != null) {
			logger.info("Got user from bd");
			profileFromDB.setDocument(userProfile.getDocument());
			logger.info("Document updated to ::" + profileFromDB.getDocument());
			mapper.save(profileFromDB);
			return findById(profileFromDB.getUserProfileId());
		}
		return null;
	}
}
