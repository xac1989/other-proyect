package com.cencosud.middleware.bonus.service;

import com.cencosud.middleware.bonus.model.BonusResponse;

public interface BonusService {
	
	BonusResponse getBonus(String doctype, String numdoc); 
	
}
