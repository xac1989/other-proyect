package com.cencosud.middleware.category.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.service.CategoryMongoService;

@Component
public class CategoryUpdate {

	@Autowired
	CategoryMongoService service;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryUpdate.class);

	@Scheduled(cron = "0 0 1 * * ?")
	public void reportCurrentTime() {
		try {
			logger.info("Scheduled category update started");
			service.processAndPersistCategories();
			logger.info("Scheduled category update finished");
		} catch (CategoryServiceException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
