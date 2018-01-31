package com.cencosud.middleware.recommendation.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cencosud.middleware.recommendation.client.ChaordicProduct;
import com.cencosud.middleware.recommendation.client.HttpCustomClient;
import com.cencosud.middleware.recommendation.configuration.ApplicationConfig;
import com.cencosud.middleware.recommendation.enums.RequestProtocolEnum;
import com.cencosud.middleware.recommendation.exception.RecommendationServiceException;
import com.cencosud.middleware.recommendation.model.Product;
import com.cencosud.middleware.recommendation.repository.ChaordicRepository;

@Repository
public class ChaordicRepositoryImpl implements ChaordicRepository {

	private static final Logger logger = LoggerFactory.getLogger(ChaordicRepositoryImpl.class);

	@Autowired
	HttpCustomClient client;

	@Autowired
	ApplicationConfig config;

	private final String ALL_RECOMMENDATIONS_URL = "/v0/products/recommendations";

	@Override
	public List<Product> getAllRecommendations(String type,
			List<String> productId,
			List<String> categoryId,
			List<String> tagId,
			String deviceId,
			String userId,
			String useBoughtProducts,
			String useCartProducts,
			String useVisitedProducts,
			Integer size) throws RecommendationServiceException {

		List<Product> products = Collections.<Product>emptyList();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		if (CollectionUtils.isEmpty(productId)
				&& CollectionUtils.isEmpty(categoryId)) {
			productId = Arrays.asList(config.getChaordic().getDefaultProductId());
			type = config.getChaordic().getDefaultProductSearchType();
		} else if (CollectionUtils.isEmpty(categoryId)) {
			type = config.getChaordic().getDefaultProductSearchType();
		} else if (CollectionUtils.isEmpty(productId)) {
			type = config.getChaordic().getDefaultCategorySearchType();
		}

//		type = StringUtils.isNotEmpty(type) ? type : config.getChaordic().getDefaultType();
		client.addNameValuePair("type", type, nvps);
		client.addNameValuePair("productId", productId, nvps);
		client.addNameValuePair("categoryId", categoryId, nvps);
		client.addNameValuePair("tagId", tagId, nvps);

		client.addNameValuePair("deviceId", deviceId, nvps);
		client.addNameValuePair("userId", userId, nvps);
		client.addNameValuePair("useBoughtProducts", useBoughtProducts, nvps);
		client.addNameValuePair("useCartProducts", useCartProducts, nvps);
		client.addNameValuePair("useVisitedProducts", useVisitedProducts, nvps);
		if (size > 0) {
			client.addNameValuePair("size", String.valueOf(size), nvps);
		}

		logger.debug("About to send chaordic request");
		String responseStr = client.executeAsString(ALL_RECOMMENDATIONS_URL, nvps, RequestProtocolEnum.GET);
		logger.debug("Response: {}", responseStr);

		JSONObject jsonFullResponse = new JSONObject(responseStr);
		try {
			JSONArray jsonDisplays = jsonFullResponse.getJSONArray("displays");
			if (jsonDisplays != null) {
				JSONArray jsonRecommendations = jsonDisplays.getJSONObject(0).getJSONArray("recommendations");
	
				products = new ArrayList<Product>(jsonRecommendations.length());
				for(int i = 0 ; i<jsonRecommendations.length(); i++){
					ChaordicProduct chaordicProduct =  new ChaordicProduct(jsonRecommendations.getJSONObject(i));
					products.add(chaordicProduct.toModelProduct());
				}
			}
		} catch (Exception e) {
			logger.error("Error when retrieving recommendations from Chaordic.", e);
		}
		return products;
	}
}
