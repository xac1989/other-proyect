package com.scanandgo.mobile.model;

import java.util.List;

public class UserConfiguration {

	private List<Configuration> localNotifications;
	private List<Configuration> pushNotifications;

	public UserConfiguration() {
		super();
	}

	public List<Configuration> getLocalNotifications() {
		return localNotifications;
	}

	public void setLocalNotifications(List<Configuration> localNotifications) {
		this.localNotifications = localNotifications;
	}

	public List<Configuration> getPushNotifications() {
		return pushNotifications;
	}

	public void setPushNotifications(List<Configuration> pushNotifications) {
		this.pushNotifications = pushNotifications;
	}

}
