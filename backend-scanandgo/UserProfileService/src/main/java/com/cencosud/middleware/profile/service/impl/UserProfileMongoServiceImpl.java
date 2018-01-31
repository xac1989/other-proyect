package com.cencosud.middleware.profile.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.profile.model.Configuration;
import com.cencosud.middleware.profile.model.UserConfiguration;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.model.enums.LocalConfigurationsEnum;
import com.cencosud.middleware.profile.repository.UserProfileRepository;
import com.cencosud.middleware.profile.service.UserProfileMongoService;

@Service
public class UserProfileMongoServiceImpl implements UserProfileMongoService {

	@Autowired
	private UserProfileRepository repository;
	
	@Override
	public UserProfile save(UserProfile userProfile) {
		UserProfile oldProfile = this.findById(userProfile.getUserProfileId()); 
		boolean changeFlag = false;
		if(oldProfile != null){
			//si ya existe, cambia el RUT para puntos solamente
			String rutPuntosCencosud = userProfile.getRutPuntosCencosud();
			String displayName = userProfile.getDisplayName();
			if(rutPuntosCencosud != null && !rutPuntosCencosud.equals("")){
				oldProfile.setRutPuntosCencosud(rutPuntosCencosud);
				changeFlag = true;
			}
			if(displayName != null && !displayName.equals("")){
				oldProfile.setDisplayName(displayName);
				changeFlag = true;
			}
			
			if(!changeFlag){
				return oldProfile; 
			}
			userProfile = oldProfile; 
		}else{
			//si no existe, lo guarda, agregandole campos por defecto
			if(userProfile.getConfigurations() == null || 
					userProfile.getConfigurations().getLocalNotifications() == null ||
					userProfile.getConfigurations().getLocalNotifications().isEmpty() ){
				UserConfiguration userConfiguration = new UserConfiguration();
				userConfiguration.setLocalNotifications(getDefaultLocalNotification());
				userProfile.setConfigurations(userConfiguration);
			}
		}
		return repository.save(userProfile);
	}

	@Override
	public UserProfile update(UserProfile userProfile) {
		UserProfile oldProfile = this.findById(userProfile.getUserProfileId()); 
	
		if(oldProfile != null){
			//si ya existe, cambia el RUT para puntos solamente
			oldProfile.setRutPuntosCencosud(userProfile.getRutPuntosCencosud());
			userProfile = repository.save(oldProfile); 
		}
		return userProfile;
	}
	
	@Override
	public void delete(String userProfileId) {
		repository.delete(userProfileId);
	}

	@Override
	public UserProfile findById(String userId) {
		UserProfile userProfile = new UserProfile();
		userProfile.setUserProfileId(userId);
		return repository.findOne(Example.of(userProfile));
	}

	private List<Configuration> getDefaultLocalNotification(){
		List<Configuration> result = new ArrayList<>(LocalConfigurationsEnum.values().length);
		for(LocalConfigurationsEnum currentConf: LocalConfigurationsEnum.values()){
			result.add(new Configuration(currentConf.getId(), currentConf.getValue()));
		}
		
		return result;
	}
}
