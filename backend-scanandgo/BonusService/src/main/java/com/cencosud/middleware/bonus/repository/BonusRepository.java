package com.cencosud.middleware.bonus.repository;

import com.cencosud.middleware.bonus.model.BonusResponse;

public interface BonusRepository {

	BonusResponse getBonus(String doctype, String numdoc);

}
