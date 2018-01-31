package com.cencosud.middleware.recommendation.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.recommendation.repository.RecommendationRepositoryCustom;

@Repository
public class RecommendationRepositoryImpl implements RecommendationRepositoryCustom {

	@Autowired
	MongoTemplate template;

}
