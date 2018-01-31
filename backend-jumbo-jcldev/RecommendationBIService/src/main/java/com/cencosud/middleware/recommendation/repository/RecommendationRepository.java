package com.cencosud.middleware.recommendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cencosud.middleware.recommendation.model.Recommendation;

public interface RecommendationRepository
		extends MongoRepository<Recommendation, String>, RecommendationRepositoryCustom {
}
