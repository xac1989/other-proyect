package com.cencosud.mobile.order.adapter;

import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-24T18:04:06.708-03:00")
public interface OrderApiService {
      public Response getOrderById(String region, String version, String orderId)
      throws Exception;
      public Response findOrders(String region, String version, String email)
      throws NotFoundException;
      public Response getOrderById(String region, String version, String orderId,String sc)
    	      throws NotFoundException, Exception;
}
