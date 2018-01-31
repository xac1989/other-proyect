package com.cencosud.mobile.coupon.adapter;

import javax.annotation.Generated;
import javax.ws.rs.core.Response;

import com.cencosud.mobile.exceptions.NotFoundException;

@Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public interface CouponsApiService {
      public Response couponsGet(String dni,String region, String version) throws NotFoundException;
}
