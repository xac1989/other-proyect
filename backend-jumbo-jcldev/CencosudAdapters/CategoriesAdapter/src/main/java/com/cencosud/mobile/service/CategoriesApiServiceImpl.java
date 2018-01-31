package com.cencosud.mobile.service;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cencosud.mobile.categories.adapter.CategoriesApiService;
import com.cencosud.mobile.util.Utils;

public class CategoriesApiServiceImpl implements CategoriesApiService {

	private static final String CATEGORIES_BASE_PATH = "categories/";
	private static final String CATEGORIES_METH_PATH=  "/categories";
	private static final String CATEGORIES_FILTER_PATH=  "/category/filter/fields";
	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";

	private String apiBaseUrl;
	private String apiClientId;
	private String apiSecret;

	private static final Logger logger = Logger.getLogger(CategoriesApiServiceImpl.class.getName()); 

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

	public Response categoriesGet(String region, String version) {
	    logger.fine(String.format("Parameters: region: %s", region));
		Client searchClient = ClientBuilder.newClient();
		WebTarget fullTextTarget = searchClient.target(this.getApiBaseUrl())
				.path(CATEGORIES_BASE_PATH)
				.path(region).path(version)
				.path(CATEGORIES_METH_PATH);

		logger.fine("---------------->"+fullTextTarget.getUri());
		// get the authorization header from mobile first and use it with api connect
		// Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON).header(AUTH_HEADER, headers.getHeaderString(AUTH_HEADER));
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);

		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());

		Response response = invocationBuilder.get();
		String products = response.readEntity(String.class);
		
		return Response.status(response.getStatus()).entity(products).build();
	}

	public Response filterFieldsGet(String region, String version, String filter, String q, boolean isDepartment, boolean deals) {
	    logger.fine(String.format("Parameters: region: %s filter: %s q: %s isDepartment: %s deals: %s", region, filter, q, isDepartment, deals));
	    q = Utils.encodePathUTF8(q);
		Client searchClient = ClientBuilder.newClient();
		WebTarget fullTextTarget = searchClient.target(this.getApiBaseUrl()).path(CATEGORIES_BASE_PATH).path(region).path(version)
				.path(CATEGORIES_FILTER_PATH).queryParam("filter", filter).queryParam("q", q)
				.queryParam("isDepartment", isDepartment)
				.queryParam("deals", deals);

		logger.fine("---------------->"+fullTextTarget.getUri());

		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);		
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		
		Response response = invocationBuilder.get();
		String products = response.readEntity(String.class);		
		return Response.status(response.getStatus()).entity(products).build();
	}

}
