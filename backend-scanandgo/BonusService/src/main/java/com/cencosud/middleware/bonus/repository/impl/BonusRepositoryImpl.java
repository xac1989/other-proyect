package com.cencosud.middleware.bonus.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.bonus.annotation.Loggable;
import com.cencosud.middleware.bonus.client.BonusClient;
import com.cencosud.middleware.bonus.client.wsdl.QueryLoyaltyMemberFullResponseEBMType;
import com.cencosud.middleware.bonus.model.BonusResponse;
import com.cencosud.middleware.bonus.repository.BonusRepository;

@Repository
public class BonusRepositoryImpl implements BonusRepository{
	
	private static final Logger log = LoggerFactory.getLogger(BonusRepositoryImpl.class);

	@Autowired
	private BonusClient bonusClient;
	
	@Loggable
	@Override
	public BonusResponse getBonus(String doctype, String numdoc) {
		log.info("Inicia consulta a repository.");
		QueryLoyaltyMemberFullResponseEBMType response = bonusClient.getBonus(doctype, numdoc);
		if(response == null || !response.getReturnCode().equals("0"))
			return null;
		return new BonusResponse(response.getDataArea().getQueryLoyaltyMember().getLoyaltyMember().getStatus(),
				response.getDataArea().getQueryLoyaltyMember().getLoyaltyMember().getSubStatus(),
				response.getDataArea().getQueryLoyaltyMember().getLoyaltyMember().getPoints());
	}

}
