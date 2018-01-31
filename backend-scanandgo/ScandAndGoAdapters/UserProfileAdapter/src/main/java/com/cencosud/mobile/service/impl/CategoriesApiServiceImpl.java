package com.cencosud.mobile.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.CategoryResponse;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.CategoriesApiService;

@Service
public class CategoriesApiServiceImpl implements CategoriesApiService{
	
//	private static final String CATEGORIES_PATH = "categories/r1/v1/categories";
	private static final String CATEGORY_PATH = "categories/r1/v1/category";
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

	public Category categoriesGet(String categoryId) throws NotFoundException {
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient
				.target(apiBaseUrl)
				.path(CATEGORY_PATH)
				.path(categoryId);

		System.out.println("---------------->"+fullTextTarget.getUri());
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
	
		Response response = invocationBuilder.get();
		
		Category categories = response.readEntity(CategoryResponse.class).getCategory();
		
		return categories;
	}


}
