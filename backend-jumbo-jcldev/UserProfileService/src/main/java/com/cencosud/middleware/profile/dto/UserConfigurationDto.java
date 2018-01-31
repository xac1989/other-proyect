package com.cencosud.middleware.profile.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.cencosud.middleware.profile.model.Configuration;
import com.cencosud.middleware.profile.model.UserConfiguration;

public class UserConfigurationDto {

	private List<ConfigurationDto> localNotifications;
	private List<ConfigurationDto> pushNotifications;

	public UserConfigurationDto() { }

	public UserConfigurationDto(UserConfiguration userConfiguration) {
		if (userConfiguration != null) {
			if (!CollectionUtils.isEmpty(userConfiguration.getLocalNotifications())) {
				this.localNotifications = new ArrayList<>(userConfiguration.getLocalNotifications().size());
				for (Configuration configuration : userConfiguration.getLocalNotifications()) {
					this.localNotifications.add(new ConfigurationDto(configuration));
				}
			}

			if (!CollectionUtils.isEmpty(userConfiguration.getPushNotifications())) {
				this.pushNotifications = new ArrayList<>(userConfiguration.getPushNotifications().size());
				for (Configuration configuration : userConfiguration.getPushNotifications()) {
					this.pushNotifications.add(new ConfigurationDto(configuration));
				}
			}
		}
	}

	public List<ConfigurationDto> getLocalNotifications() {
		return localNotifications;
	}

	public void setLocalNotifications(List<ConfigurationDto> localNotifications) {
		this.localNotifications = localNotifications;
	}

	public List<ConfigurationDto> getPushNotifications() {
		return pushNotifications;
	}

	public void setPushNotifications(List<ConfigurationDto> pushNotifications) {
		this.pushNotifications = pushNotifications;
	}
}
