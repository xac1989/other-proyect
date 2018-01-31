package com.cencosud.middleware.recommendation.service;

import java.util.List;

import com.cencosud.middleware.recommendation.model.Product;

public interface RecommendationService {

	/**
	 * Retrieves a list of products recommended for the given user email.
	 * 
	 * @param email
	 * @return
	 */
	List<Product> getRecommendedProducts(
		String type,
		List<String> productId,
		List<String> categoryId,
		List<String> tagId,
		String deviceId,
		String userId,
		String useBoughtProducts,
		String useCartProducts,
		String useVisitedProducts,
		Integer size,
		String email);
	
	Product getRecommendedRelevantProduct(
			String email);

}
