package com.scanandgo.middleware.batch.product.service;

import com.cencosud.middleware.product.model.Product;
import com.scanandgo.middleware.batch.product.exception.ProductServiceException;

public interface VtexService {

	Product searchProducts(String ean)
			throws ProductServiceException;

}
