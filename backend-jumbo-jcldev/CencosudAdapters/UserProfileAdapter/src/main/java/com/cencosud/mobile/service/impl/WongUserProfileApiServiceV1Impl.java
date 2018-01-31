package com.cencosud.mobile.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.mobile.model.category.UserProfile;
import com.cencosud.mobile.model.category.UserProfileResponse;
import com.cencosud.mobile.model.profile.wong.ProfileUpdateRequest;
import com.cencosud.mobile.service.UserProfileApiService;

public class WongUserProfileApiServiceV1Impl implements UserProfileApiService {

    private static final String PROFILE_PATH = "userprofile/r1/v1/profile";
    private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
    private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";

    private static final Logger LOG = LoggerFactory.getLogger(WongUserProfileApiServiceV1Impl.class);

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

    @Override
    public UserProfile getProfile(String profileId) {
        Client searchClient = ClientBuilder.newClient();
        WebTarget fullTextTarget = searchClient.target(apiBaseUrl).path(PROFILE_PATH).queryParam("id", profileId);

        LOG.info("URL: {}", fullTextTarget.getUri());

        Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
        invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());

        Response response = invocationBuilder.get();
        return response.readEntity(UserProfileResponse.class).getProfile();
    }

    @Override
    public UserProfile updateProfile(ProfileUpdateRequest profile) {
        WebTarget fullTextTarget = ClientBuilder.newClient().target(apiBaseUrl).path(PROFILE_PATH);

        LOG.info("URL: {}", fullTextTarget.getUri());

        Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
        invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());

        Response response = invocationBuilder.put(Entity.entity(profile, MediaType.APPLICATION_JSON));
        return response.readEntity(UserProfileResponse.class).getProfile();
    }
}
