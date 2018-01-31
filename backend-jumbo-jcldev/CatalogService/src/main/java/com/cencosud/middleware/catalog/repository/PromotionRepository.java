package com.cencosud.middleware.catalog.repository;

import java.util.Map;

import com.cencosud.middleware.catalog.model.Promotion;

public interface PromotionRepository {
	Map<String, Promotion> getPromotions();
}
