package com.cencosud.mobile.service.impl;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.rewards.adapter.RewardsApiService;

public class RewardsApiServiceImpl implements RewardsApiService{
	
	private final Logger logger = Logger.getLogger(RewardsApiServiceImpl.class.toString());
	
	private static final String REWARDS_PATH = "rewards/r1/v1/rewards";
	//private static final String AUTH_HEADER = "Authorization";
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


	/**
	 * docype possible cases: D.N.I. , C.I. , C.E. , passport
	 */
	public Response rewardsGet(String numDoc, int docType) throws NotFoundException {
		
		logger.info("[RewardsAdapter](rewardsGet)----------------> dni from rewardsGet :: " + numDoc);
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient.target(this.getApiBaseUrl()).path(REWARDS_PATH)
				.path(numDoc)
				.queryParam("doctype", docType);
		
		
		logger.info("[RewardsAdapter](rewardsGet)---------------->uri "+fullTextTarget.getUri());
		// get the authorization header from mobile first and use it with api connect
		// Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON).header(AUTH_HEADER, headers.getHeaderString(AUTH_HEADER));
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		
		Response response = invocationBuilder.get();
		logger.info("[RewardsAdapter](rewardsGet)---------------->response status:::" + response.getStatus());
		
		String products = response.readEntity(String.class);
		
		return Response.status(response.getStatus()).entity(products).build();
		
	}

}
