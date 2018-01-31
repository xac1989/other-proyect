package com.cencosud.middleware.rewards.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.rewards.model.RewardsResponse;
import com.cencosud.middleware.rewards.repository.impl.RewardsRepository;
import com.cencosud.middleware.rewards.service.RewardsService;

@Service
public class RewardsServiceImpl implements RewardsService{
	
	private static final Logger log = LoggerFactory.getLogger(RewardsServiceImpl.class);
	
	@Autowired
	private RewardsRepository rewardsRepo;

	@Override
	public RewardsResponse getRewards(Integer docTypeCode, String documentNumber) {
		log.info("call getRewards from repository");
		return rewardsRepo.getRewards(docTypeCode, documentNumber);
	}

}
