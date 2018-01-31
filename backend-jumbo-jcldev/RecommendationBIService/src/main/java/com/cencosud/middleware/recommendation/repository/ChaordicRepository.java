package com.cencosud.middleware.recommendation.repository;

import java.util.List;

import com.cencosud.middleware.recommendation.exception.RecommendationServiceException;
import com.cencosud.middleware.recommendation.model.Product;

public interface ChaordicRepository {

	public List<Product> getAllRecommendations(String type,
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
