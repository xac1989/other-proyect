package com.cencosud.mobile.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.mobile.model.category.Category;
import com.cencosud.mobile.model.category.CategoryResponse;
import com.cencosud.mobile.service.CategoriesApiService;

public class CategoriesApiServiceImpl implements CategoriesApiService {

    private static final String CATEGORY_PATH = "categories/r1/v1/category";
    private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
    private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";
    private static final Logger LOG = LoggerFactory.getLogger(CategoriesApiServiceImpl.class.getName());

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
    public Category getCategory(String categoryId) {
        Client searchClient = ClientBuilder.newClient();

        WebTarget fullTextTarget = searchClient.target(apiBaseUrl).path(CATEGORY_PATH).path(categoryId);

        LOG.info("URL: {}", fullTextTarget.getUri());
        Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
        invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());

        Response response = invocationBuilder.get();

        return response.readEntity(CategoryResponse.class).getCategory();
    }
}
