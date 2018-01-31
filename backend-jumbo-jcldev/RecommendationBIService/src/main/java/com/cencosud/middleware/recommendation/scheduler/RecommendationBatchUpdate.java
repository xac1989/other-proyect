package com.cencosud.middleware.recommendation.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cencosud.middleware.recommendation.service.RecommendationService;

@Component
public class RecommendationBatchUpdate {

	@Autowired
	RecommendationService service;
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendationBatchUpdate.class);

	@Scheduled(cron = "0 0 1 * * ?")
	public void reportCurrentTime() {
		try {
			logger.info("Scheduled recommendation update started");
			//service.bulkRecommendationUpdate();
			logger.info("Scheduled recommendation update finished");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
