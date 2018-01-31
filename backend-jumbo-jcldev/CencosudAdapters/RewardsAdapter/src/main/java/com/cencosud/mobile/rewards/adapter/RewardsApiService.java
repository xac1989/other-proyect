package com.cencosud.mobile.rewards.adapter;

import javax.ws.rs.core.Response;

import com.cencosud.mobile.exceptions.NotFoundException;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
public interface RewardsApiService {
      public Response rewardsGet(String numDoc, int docType)
      throws NotFoundException;
}
