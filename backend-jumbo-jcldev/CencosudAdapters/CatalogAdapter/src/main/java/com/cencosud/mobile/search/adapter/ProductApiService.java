package com.cencosud.mobile.search.adapter;

import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public interface ProductApiService {
	Response productProductIdGet(String productId, String region, String version,  String sc);

	Response productProductIdGet(String productId, String region, String version);
}
