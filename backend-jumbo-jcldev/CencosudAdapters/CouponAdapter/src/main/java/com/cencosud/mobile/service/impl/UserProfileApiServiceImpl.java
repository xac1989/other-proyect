package com.cencosud.mobile.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.UserProfileApiService;
import com.cencosud.mobile.service.model.UserProfile;
import com.cencosud.mobile.service.model.UserProfileResponse;

@Service
public class UserProfileApiServiceImpl implements UserProfileApiService{

	private static final Logger LOG = LoggerFactory.getLogger(UserProfileApiServiceImpl.class);
    
	private static final String PROFILE_BASE_PATH = "userprofile/";
	private static final String PROFILE_RESOURCE_PATH = "/profile";
	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";
	
	private String apiBaseUrl;
	private String apiClientId;
	private String apiSecret;
	
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

	public String getApiClientId() {
		return apiClientId;
	}

	public void setApiClientId(String apiClientId) {
		this.apiClientId = apiClientId;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}


	public UserProfile  profileGet(String profileId,String region,String version) throws NotFoundException {
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient	
				.target(apiBaseUrl)
				.path(PROFILE_BASE_PATH)
				.path(region)
				.path(version)
				.path(PROFILE_RESOURCE_PATH)				
				.queryParam("id",profileId);

		LOG.info("---------------->"+fullTextTarget.getUri());
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		
		Response response = invocationBuilder.get();

		UserProfile profile = response.readEntity(UserProfileResponse.class).getProfile();

		return profile;
	}


}
