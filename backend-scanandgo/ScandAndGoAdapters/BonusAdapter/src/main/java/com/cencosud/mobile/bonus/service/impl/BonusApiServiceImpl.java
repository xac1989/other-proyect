package com.cencosud.mobile.bonus.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.cencosud.middleware.bonus.model.BonusResponse;
import com.cencosud.mobile.bonus.exceptions.NotFoundException;
import com.cencosud.mobile.bonus.service.BonusApiService;

@Service
public class BonusApiServiceImpl implements BonusApiService{

	private static final String PRODUCT_PATH = "/bonus-api/bonus";
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


	public BonusResponse productGet(String doctype, String numdoc) throws NotFoundException {
		BonusResponse productResponse = null;
		productResponse = getProduct(doctype, numdoc);

		return productResponse;
	}
	
	private BonusResponse getProduct(String doctype, String numdoc){
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient	
				.target(apiBaseUrl)
				.path(PRODUCT_PATH)
				.path(doctype)
				.queryParam("numdoc", numdoc);

		System.out.println("---------------->"+fullTextTarget.getUri());
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		
		Response response = invocationBuilder.get();
		
		BonusResponse bonus = response.readEntity(BonusResponse.class);
		
		return bonus;
	}
}
