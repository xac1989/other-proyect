package com.cencosud.middleware.recommendation.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.recommendation.exception.RecommendationServiceException;
import com.cencosud.middleware.recommendation.model.Product;
import com.cencosud.middleware.recommendation.model.Recommendation;
import com.cencosud.middleware.recommendation.repository.RecommendationRepository;
import com.cencosud.middleware.recommendation.service.ChaordicService;
import com.cencosud.middleware.recommendation.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	private static final Logger logger = LoggerFactory.getLogger(RecommendationServiceImpl.class);
	private static final String RECOMMENDED = "recommended";

	@Autowired
	private RecommendationRepository recommendationsRepository;

	@Autowired
	private ChaordicService chaordicService;

	@Override
	public List<Product> getRecommendedProducts(
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
			String email) {

		List<Product> products = Collections.<Product>emptyList();
		logger.debug("Obtaining recommendations from recommendation database");
		
		if ( RECOMMENDED.equals(StringUtils.lowerCase(type)) ){
			if ( StringUtils.isNotEmpty(email) ){
				Recommendation recommendation = recommendationsRepository.findOne(email);
				if (recommendation != null) {
					logger.debug("Using products from recommendation database for email {}", email);
					products = recommendation.getProducts();
				}
			}
		} else {
			logger.debug("Recommendations database data not found. Going to chaordic.");
			try {
				products = chaordicService.getAllRecommendations(type, productId, categoryId, tagId, deviceId, userId, useBoughtProducts, useCartProducts, useVisitedProducts,
						size);
			} catch (RecommendationServiceException e) {
				logger.error("Issues when retrieving data from Chaordic", e);
			}
		}
		
		return products;
	}
	
	
	@Override
	public Product getRecommendedRelevantProduct(String email) {
		
		Product starredProd = new Product();
		logger.debug("Obtaining recommendation RelevantProduct from recommendation database");
		
		if ( StringUtils.isNotEmpty(email) ){
			Recommendation recommendation = recommendationsRepository.findOne(email);
			if (recommendation != null && recommendation.getStarredProduct() != null) {
				logger.debug("Using relevant product from recommendation database for email {}", email);
				starredProd = recommendation.getStarredProduct();
			}
		}
		
		return starredProd;
	}

//	@Override
//	public void saveRecommendationsMatrix(MultipartFile recommendationsFile) throws IOException {
//		String fileContent = new String(recommendationsFile.getBytes(), StandardCharsets.UTF_8);
//		String fileName = recommendationsFile.getOriginalFilename();
//		logger.info("Received file: " + fileName + " Content:\n" + fileContent);
//	}
}
