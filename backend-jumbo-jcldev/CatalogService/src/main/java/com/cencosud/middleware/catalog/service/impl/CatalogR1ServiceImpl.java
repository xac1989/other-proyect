package com.cencosud.middleware.catalog.service.impl;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.dto.mapper.ProductMapper;
import com.cencosud.middleware.catalog.dto.mapper.ProductWongMapper;
import com.cencosud.middleware.catalog.dto.mapper.SearchProductMapper;
import com.cencosud.middleware.catalog.dto.mapper.SearchProductWongMapper;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.Product;
import com.cencosud.middleware.catalog.model.SearchResult;
import com.cencosud.middleware.catalog.repository.CatalogRepository;
import com.cencosud.middleware.catalog.service.CatalogService;

@Service
public class CatalogR1ServiceImpl implements CatalogService {
	
	//Region1 = WONG PERU
	private static final String REGION_ID = "r1";
	
	@Autowired
	@Qualifier(value="catalogVtexRepositoryR1")
	CatalogRepository repo;
	
	@Override
	public String getRegionId() {
		return REGION_ID;
	}
	
	@Override
	public List<Product> getAllProducts() throws CatalogServiceException {
		return repo.getAllProducts();
	}

	@Override
	public SearchResult searchProducts(String filter, String brand, String spec, String q, String o, int offset,
			int limit,String salesChannel) throws CatalogServiceException {
		return repo.searchProducts(filter, brand, spec, q, o, offset, limit);
	}

	@Override
	public VtexProduct getProductDetail(String productId,String salesChannel) throws CatalogServiceException {
		return repo.getProductDetail(productId,salesChannel);
	}
	
	public ProductMapper getProductMapper(){
		return Mappers.getMapper(ProductWongMapper.class);
	}

	public SearchProductMapper getSearchProductMapper(){
		return Mappers.getMapper(SearchProductWongMapper.class);
	}

	@Override
	public VtexProduct getProductDetail(String searchId, String productId, String salesChannel) throws CatalogServiceException {
		// TODO: Search with different searchId, not yet implemented for region 1 
		return null;
	}

	@Override
	public List<VtexProduct> getProductsDetail(String searchId, String[] productId, String salesChannel)
			throws CatalogServiceException {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public SearchResult searchDealsProducts(String o, int offset, int limit, String filter, String brand, String spec, String freeText, String salesChannel)
            throws CatalogServiceException {
		throw new UnsupportedOperationException("Unsupported method");
	}
}
