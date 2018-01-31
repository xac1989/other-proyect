package com.cencosud.middleware.catalog.repository;

import java.util.List;

import org.apache.http.NameValuePair;

import com.cencosud.middleware.catalog.client.VtexClient;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.Product;
import com.cencosud.middleware.catalog.model.SearchResult;

public interface CatalogRepository {
	
	void setClient(VtexClient client);

	List<Product> getAllProducts() throws CatalogServiceException;

	SearchResult searchProducts(String filter, String brand, String spec, String q, String o, int offset, int limit)
			throws CatalogServiceException;
	
	SearchResult searchProducts(String path, List<NameValuePair> parameters, String salesChannel) throws CatalogServiceException;

	VtexProduct getProductDetail(String id, String salesChannel) throws CatalogServiceException;
	
	VtexProduct getProductDetail(String searchId, String id, String salesChannel) throws CatalogServiceException;

	List<VtexProduct> getProductsDetail(String searchId, String[] ids, String salesChannel)
			throws CatalogServiceException;
}
