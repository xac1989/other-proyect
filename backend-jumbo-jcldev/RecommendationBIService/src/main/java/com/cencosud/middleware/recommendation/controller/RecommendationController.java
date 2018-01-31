package com.cencosud.middleware.recommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.recommendation.annotation.Loggable;
import com.cencosud.middleware.recommendation.model.Product;
import com.cencosud.middleware.recommendation.model.Recommendation;
import com.cencosud.middleware.recommendation.service.RecommendationService;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

	@Autowired
	RecommendationService recommendationService;

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/getRecommendedProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getRecommendedProducts(
			@RequestParam(required = false) String type,
			@RequestParam(required = false) List<String> productId,
			@RequestParam(required = false) List<String> categoryId,
			@RequestParam(required = false) List<String> tagId,
			@RequestParam(required = false) String deviceId,
			@RequestParam(required = false) String userId,
			@RequestParam(required = false) String useBoughtProducts,
			@RequestParam(required = false) String useCartProducts,
			@RequestParam(required = false) String useVisitedProducts,
			@RequestParam(required = false) Integer size,
			@RequestParam(required = false) String email) {

		return recommendationService.getRecommendedProducts(type, productId, categoryId, tagId, deviceId, userId,
				useBoughtProducts, useCartProducts, useVisitedProducts, size, email);
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/getRecommendedRelevantProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getRecommendedRelevantProduct(
			@RequestParam(required = false) String email) {

		return recommendationService.getRecommendedRelevantProduct(email);
	}
}
