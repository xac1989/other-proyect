package com.cencosud.middleware.bonus.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.bonus.model.BonusResponse;
import com.cencosud.middleware.bonus.repository.BonusRepository;
import com.cencosud.middleware.bonus.service.BonusService;

@Service
public class BonusServiceImpl implements BonusService{
	
	private static final Logger log = LoggerFactory.getLogger(BonusServiceImpl.class);
	
	@Autowired
	private BonusRepository bonusRepo;

	@Override
	public BonusResponse getBonus(String docTypeCode, String documentNumber) {
		log.info("Inicia el servicio.");
		return bonusRepo.getBonus(docTypeCode, documentNumber);
	}

}
