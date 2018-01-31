package com.cencosud.mobile.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.cencosud.middleware.feedback.model.Feedback;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.FeedbackApiService;

@Service
public class FeedBackServiceImpl implements FeedbackApiService {

	private static final String PRODUCT_PATH = "/feedback";
	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";
	
	private String apiBaseUrl;
	private String apiClientId;
	private String apiSecret;
	
	/**
	 * 
	 * @return
	 */
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	/**
	 * 
	 * @param apiBaseUrl
	 */
	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

	/**
	 * 
	 * @return
	 */
	public String getApiClientId() {
		return apiClientId;
	}
	 /**
	  * 
	  * @param apiClientId
	  */
	public void setApiClientId(String apiClientId) {
		this.apiClientId = apiClientId;
	}
	
	/**
	 * @return the apiSecret
	 */
	public String getApiSecret() {
		return apiSecret;
	}

	/**
	 * @param apiSecret the apiSecret to set
	 */
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	@Override
	public Feedback product(Feedback feedback) throws NotFoundException {
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient	
				.target(apiBaseUrl)
				.path(PRODUCT_PATH);

		System.out.println("---------------->"+fullTextTarget.getUri());
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		Entity<Feedback> request = Entity.entity(feedback, MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(request);
		
		Feedback responseFeedback = response.readEntity(Feedback.class);
		
		return responseFeedback;
	}
	

}
