package com.cencosud.mobile.search.adapter;

import com.cencosud.mobile.search.model.ProductSearchParameter;
import com.cencosud.mobile.util.RestServiceResponse;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public interface ProductsApiService {
    RestServiceResponse<String> productsSearchGet(ProductSearchParameter productSearchParameter);

	RestServiceResponse<String> getDeals(ProductSearchParameter productSearchParameter);
}
