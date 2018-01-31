package com.cencosud.middleware.recommendation.service;

import java.util.List;

import com.cencosud.middleware.recommendation.exception.RecommendationServiceException;
import com.cencosud.middleware.recommendation.model.Product;

public interface ChaordicService {

	List<Product> getAllRecommendations(
			String type,
			List<String> productId,
			List<String> categoryId,
			List<String> tagId,
			String deviceId,
			String userId,
			String useBoughtProducts,
			String useCartProducts,
			String useVisitedProducts,
			Integer size) throws RecommendationServiceException;
	
}
