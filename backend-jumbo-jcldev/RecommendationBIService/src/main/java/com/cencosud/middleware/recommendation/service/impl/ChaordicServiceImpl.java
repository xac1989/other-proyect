package com.cencosud.middleware.recommendation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.recommendation.exception.RecommendationServiceException;
import com.cencosud.middleware.recommendation.model.Product;
import com.cencosud.middleware.recommendation.repository.ChaordicRepository;
import com.cencosud.middleware.recommendation.repository.impl.ChaordicRepositoryImpl;
import com.cencosud.middleware.recommendation.service.ChaordicService;

@Service
public class ChaordicServiceImpl implements ChaordicService {

	final Logger logger = LoggerFactory.getLogger(ChaordicRepositoryImpl.class);
	static final Integer DEFAULT_SIZE = 0;

	@Autowired
	ChaordicRepository repo;
	
	@Override
	public List<Product> getAllRecommendations(
			String type,
			List<String> productId,
			List<String> categoryId,
			List<String> tagId,
			String deviceId,
			String userId,
			String useBoughtProducts,
			String useCartProducts,
			String useVisitedProducts,
			Integer size) throws RecommendationServiceException {

		logger.debug("Obtaining data from chaordic service");
		size = size == null ? DEFAULT_SIZE : size;
		List<Product> productRecommendation = repo.getAllRecommendations(
				type,
				productId,
				categoryId,
				tagId,
				deviceId,
				userId,
				useBoughtProducts,
				useCartProducts,
				useVisitedProducts,
				size);
		logger.debug("Results from chaordic service: {}", productRecommendation);
		return productRecommendation;
	}
}
