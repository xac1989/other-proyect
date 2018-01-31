package com.cencosud.middleware.catalog.service;

import java.util.List;

import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.dto.mapper.ProductMapper;
import com.cencosud.middleware.catalog.dto.mapper.SearchProductMapper;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.Product;
import com.cencosud.middleware.catalog.model.SearchResult;

public interface CatalogService {

	String getRegionId();

	List<Product> getAllProducts() throws CatalogServiceException;

	SearchResult searchProducts(String filter, String brand, String spec, String q, String o, int offset, int limit,String sc)
			throws CatalogServiceException;

	VtexProduct getProductDetail(String productId,String salesChannel) throws CatalogServiceException;
	
	VtexProduct getProductDetail(String searchId, String productId,String salesChannel) throws CatalogServiceException;
	
	ProductMapper getProductMapper();
	SearchProductMapper getSearchProductMapper();

	List<VtexProduct> getProductsDetail(String searchId, String[] productId, String salesChannel)
			throws CatalogServiceException;

//	SearchResult searchDealsProducts(String q, String o, int offset, int limit, String sc,
//	        String filter, String brand, String spec) throws CatalogServiceException;

	SearchResult searchDealsProducts(String o, int offset, int limit, String filter, String brand, String spec, String freeText, String salesChannel)
	        throws CatalogServiceException;

}
