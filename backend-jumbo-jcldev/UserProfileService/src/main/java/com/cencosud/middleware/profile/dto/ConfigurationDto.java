package com.cencosud.middleware.profile.dto;

import com.cencosud.middleware.profile.model.Configuration;

public class ConfigurationDto {

	private String id;
	private boolean enabled;

	public ConfigurationDto() {
	}

	public ConfigurationDto(String id, boolean enabled) {
		this.id = id;
		this.enabled = enabled;
	}

	public ConfigurationDto(Configuration configuration) {
		this(configuration.getId(), configuration.isEnabled());
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
