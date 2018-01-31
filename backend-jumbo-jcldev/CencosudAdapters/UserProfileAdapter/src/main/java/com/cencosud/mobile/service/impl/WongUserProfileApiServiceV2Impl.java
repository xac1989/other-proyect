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

import com.cencosud.mobile.dto.profile.jumbo.UpdateProfileGenericRequest;
import com.cencosud.mobile.model.profile.jumbo.SalesChannel;
import com.cencosud.mobile.model.profile.jumbo.UserProfile;
import com.cencosud.mobile.model.profile.jumbo.UserProfileResponse;
import com.cencosud.mobile.service.WongUserProfileApiServiceV2;
import com.cencosud.mobile.util.RequestProtocolEnum;
import com.cencosud.mobile.util.RestServiceUtil;

public class WongUserProfileApiServiceV2Impl implements WongUserProfileApiServiceV2 {

    private static final String PROFILE_PATH_PRE = "userprofile/";
    private static final String PROFILE_PATH_POS = "/v2/profile";
    private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
    private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";
    private static final String SALES_CHANNEL_LIST = "/saleschannel/r2/v1/salesChannel/";

    private String apiBaseUrl;
    private String apiClientId;
    private String apiSecret;

    private RestServiceUtil restServiceUtil;

    static final Logger LOGGER = LoggerFactory.getLogger(WongUserProfileApiServiceV2Impl.class);

    /**
     * @return the restServiceUtil
     */
    public RestServiceUtil getRestServiceUtil() {
        return restServiceUtil;
    }

    /**
     * @param restServiceUtil
     *            the restServiceUtil to set
     */
    public void setRestServiceUtil(RestServiceUtil restServiceUtil) {
        this.restServiceUtil = restServiceUtil;
    }

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
    public UserProfile getUserProfile(String profileId, String region) {
        Client searchClient = ClientBuilder.newClient();
        WebTarget fullTextTarget = searchClient.target(apiBaseUrl).path(PROFILE_PATH_PRE).path(region)
                .path(PROFILE_PATH_POS).queryParam("id", profileId);
        LOGGER.info("----------------> {}", fullTextTarget.getUri());
        Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
        invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
        Response response = invocationBuilder.get();
        UserProfile profile = response.readEntity(UserProfileResponse.class).getProfile();
        return profile;
    }

    public UserProfile putProfile(UpdateProfileGenericRequest profile, String region, String email) {
        WebTarget fullTextTarget = ClientBuilder.newClient().target(apiBaseUrl)
                .path(PROFILE_PATH_PRE + region + PROFILE_PATH_POS).queryParam("email", email);

        LOGGER.info("----------------> PUT {}", fullTextTarget.getUri());

        Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);

        invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
        invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());

        Response response = invocationBuilder.put(Entity.entity(profile, MediaType.APPLICATION_JSON));
        UserProfile updatedProfile = response.readEntity(UserProfileResponse.class).getProfile();

        return updatedProfile;
    }

	@Override
	public SalesChannel getSalesChannel(Integer idSalesChannel) {
		String pathSalesChannel = idSalesChannel != null ? SALES_CHANNEL_LIST.concat(idSalesChannel.toString())
                : SALES_CHANNEL_LIST.concat("0");
        LOGGER.info("URL: {} ", pathSalesChannel);
        return restServiceUtil.executeService(pathSalesChannel, null, SalesChannel.class, null,
                RequestProtocolEnum.GET);
	}
}
