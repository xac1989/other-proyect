package com.scanandgo.middleware.batch.product.repository;

import com.cencosud.middleware.product.model.Product;
import com.scanandgo.middleware.batch.product.exception.ProductServiceException;

public interface VtexRepository {
	
	Product searchProduct(String ean) throws ProductServiceException;
	
}
