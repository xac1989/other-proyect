package com.cencosud.middleware.catalog.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.catalog.model.Promotion;
import com.cencosud.middleware.catalog.repository.PromotionRepository;
import com.cencosud.middleware.catalog.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	private static Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

	@Autowired
	private PromotionRepository promotionRepository; 
	
	@Override
	@Cacheable(value = "promotionsList", key="'promo'")
	public Map<String,Promotion> getPromotions() {
		return promotionRepository.getPromotions();
	}

	@Override
	@CachePut(value = "promotionsList", key="'promo'")
	public Map<String,Promotion> updateCache() {
		logger.debug("updating cache");
		return promotionRepository.getPromotions();
	}
}
