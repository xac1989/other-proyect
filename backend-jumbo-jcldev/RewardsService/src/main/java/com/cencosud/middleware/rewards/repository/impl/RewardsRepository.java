package com.cencosud.middleware.rewards.repository.impl;

import com.cencosud.middleware.rewards.model.RewardsResponse;

public interface RewardsRepository {

	RewardsResponse getRewards(Integer doctype, String numdoc);

}
