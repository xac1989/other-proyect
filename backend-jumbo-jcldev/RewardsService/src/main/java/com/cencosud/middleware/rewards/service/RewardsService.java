package com.cencosud.middleware.rewards.service;

import com.cencosud.middleware.rewards.model.RewardsResponse;

public interface RewardsService {
	
	RewardsResponse getRewards(Integer doctype, String numdoc); 
	
}
