package com.cencosud.mobile.service;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.product.model.ProductResponse;

public interface ProductApiService {
      public ProductResponse productGet(String ean, String storeId) throws NotFoundException;

}
