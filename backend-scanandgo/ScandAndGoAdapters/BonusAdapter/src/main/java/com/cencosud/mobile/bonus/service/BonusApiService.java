package com.cencosud.mobile.bonus.service;

import com.cencosud.middleware.bonus.model.BonusResponse;
import com.cencosud.mobile.bonus.exceptions.NotFoundException;

public interface BonusApiService {
      public BonusResponse productGet(String doctype, String numdoc) throws NotFoundException;

}
