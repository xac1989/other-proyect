package com.cencosud.middleware.catalog.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cencosud.middleware.catalog.service.PromotionService;

@Component
public class PromotionCacheUpdater {
	@Autowired
	private PromotionService promotionService;
	
	@Scheduled(cron = "${promotion.schedule}")
	public void updateCache() {
		promotionService.updateCache();
	}

}
