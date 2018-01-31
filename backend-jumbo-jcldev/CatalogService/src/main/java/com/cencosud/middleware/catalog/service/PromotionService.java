package com.cencosud.middleware.catalog.service;

import java.util.Map;

import com.cencosud.middleware.catalog.model.Promotion;

public interface PromotionService {
	Map<String, Promotion> getPromotions();
	Map<String, Promotion> updateCache();
}
