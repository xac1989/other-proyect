package com.cencosud.mobile.service.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * UserProfile
 *
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile {
	
	private String id;
    private String displayName;
    private String document;
    private String documentType;
    private Map<String, String> attributes;
    private Favorites favorites;
    private UserConfiguration configurations;
    private String fullName;
    private Date birthDate;
    private String sex;
    private String phone;
	

	public UserProfile() {
		super();
	}

	public UserProfile(String id, String displayName, String document, String documentType,
			Map<String, String> attributes, Favorites favorites, UserConfiguration configurations, String fullName,
			Date birthDate, String sex, String phone) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.document = document;
		this.documentType = documentType;
		this.attributes = attributes;
		this.favorites = favorites;
		this.configurations = configurations;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.sex = sex;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Favorites getFavorites() {
		return favorites;
	}

	public void setFavorites(Favorites favorites) {
		this.favorites = favorites;
	}

	public UserConfiguration getConfigurations() {
		return configurations;
	}

	public void setConfigurations(UserConfiguration configurations) {
		this.configurations = configurations;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}


	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * 
	 * Favorites
	 *
	 */
	public static class Favorites {

		List<String> categories;
		
		public Favorites() {
			super();
		}

		public Favorites(List<String> categories) {
			super();
			this.categories = categories;
		}

		public List<String> getCategories() {
			return categories;
		}

		public void setCategories(List<String> categories) {
			this.categories = categories;
		}
		
	}
	
	/**
	 * 
	 * UserConfiguration
	 * 
	 */
	public static class UserConfiguration {
		
		
		private List<Configuration> localNotifications;
		private List<Configuration> pushNotifications;
		
		public UserConfiguration(){
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
	
	/**
	 * 
	 * Configuration
	 *
	 */
	public static class Configuration {

		private String id;
		private boolean enabled;
		
		public Configuration() {
			super();
		}
		
		public Configuration(String id, boolean enabled) {
			super();
			this.id = id;
			this.enabled = enabled;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		
	}
}
