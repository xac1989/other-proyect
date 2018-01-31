package com.cencosud.mobile.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cencosud.mobile.coupon.adapter.CouponsApiService;
import com.cencosud.mobile.exceptions.NotFoundException;

public class CouponsApiServiceImpl implements CouponsApiService{
	
	private static final String COUPON_BASE_PATH = "coupons/";
	private static final String COUPON_RESOURCE = "/coupons";
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
	
	public Response couponsGet(String dni,String region,String version) throws NotFoundException {
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient.target(this.getApiBaseUrl())
				.path(COUPON_BASE_PATH)
				.path(region)
				.path(version)
				.path(COUPON_RESOURCE)
				.path(dni);
		
		// get the authorization header from mobile first and use it with api connect
		// Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON).header(AUTH_HEADER, headers.getHeaderString(AUTH_HEADER));
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		System.out.println("--------------->"+fullTextTarget.getUri());
		
		Response response = invocationBuilder.get();
		String products = response.readEntity(String.class);

				
		return Response.status(response.getStatus()).entity(products).build();
	}

}
