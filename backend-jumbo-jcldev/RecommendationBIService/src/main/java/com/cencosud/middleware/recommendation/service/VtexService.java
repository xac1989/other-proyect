package com.cencosud.middleware.recommendation.service;

import java.util.List;

import com.cencosud.middleware.recommendation.exception.RecommendationServiceException;
import com.cencosud.middleware.recommendation.model.Recommendation;

public interface VtexService {

	List<Recommendation> getAllCategories() throws RecommendationServiceException;
	
	Recommendation findById(int id) throws RecommendationServiceException;
	
}
