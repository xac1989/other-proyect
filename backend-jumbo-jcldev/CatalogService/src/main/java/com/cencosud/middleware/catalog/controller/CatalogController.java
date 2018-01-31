package com.cencosud.middleware.catalog.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.catalog.annotation.Loggable;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.dto.mapper.ProductMapper;
import com.cencosud.middleware.catalog.dto.mapper.SearchProductMapper;
import com.cencosud.middleware.catalog.dto.productdetail.ProductDto;
import com.cencosud.middleware.catalog.dto.search.SearchResultDto;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.factory.CatalogServiceFactory;
import com.cencosud.middleware.catalog.model.Product;
import com.cencosud.middleware.catalog.model.SearchResult;

@RestController
@RequestMapping("/{region}/v1")
public class CatalogController {
	private static final String DEFAULT_SC = "1";

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);

	private static final String SKU_ID = "SKU";

	private CatalogServiceFactory serviceFactory;

	@Autowired
	public void setServiceFactory(CatalogServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "products", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProduct(@PathVariable("region") String region) throws CatalogServiceException {
		LOGGER.info("Obteniendo todos los productos");
		return serviceFactory.getService(region).getAllProducts();
	}

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
	public SearchResultDto searchProducts(@PathVariable("region") String region,
			@RequestParam(required = false) String filter, @RequestParam(required = false) String brand,
			@RequestParam(required = false) String spec, @RequestParam(required = false) String q,
			@RequestParam(required = false) String o, @RequestParam(defaultValue = "0", required = false) int offset,
			@RequestParam(defaultValue = "0", required = false) int limit, @RequestParam(defaultValue = DEFAULT_SC, required = false) String sc)
			throws CatalogServiceException {
		LOGGER.info("Obteniendo la consulta de catalogo");
		SearchResult searchResult = serviceFactory.getService(region).searchProducts(filter, brand, spec, q, o, offset,
				limit, sc);		
		SearchProductMapper searchProductMapper = serviceFactory.getService(region).getSearchProductMapper();
		return searchProductMapper.generateSearchResult(searchResult);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto getProductDetail(@PathVariable("region") String region,
			@PathVariable("productId") String productid, @RequestParam(defaultValue = DEFAULT_SC, required = false) String sc)
			throws CatalogServiceException {
		LOGGER.info("Obteniendo detalle de producto");
		VtexProduct vtexProduct = serviceFactory.getService(region).getProductDetail(productid, sc);
		ProductDto productDto = new ProductDto();
		if (vtexProduct != null) {
			ProductMapper productMapper = serviceFactory.getService(region).getProductMapper();
			productDto = productMapper.generateProduct(vtexProduct);
			
		}
		return productDto;
	}

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "product/sku/{skuId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto getProductDetailBySku(@PathVariable("region") String region, @PathVariable("skuId") String sku,
			@RequestParam(defaultValue = DEFAULT_SC, required = false) String sc) throws CatalogServiceException {
		LOGGER.info("Obteniendo detalle de producto por Sku");
		VtexProduct vtexProduct = serviceFactory.getService(region).getProductDetail(SKU_ID, sku, sc);
		ProductDto productDto = new ProductDto();
		if (vtexProduct != null) {
			ProductMapper productMapper = serviceFactory.getService(region).getProductMapper();
			productDto = productMapper.generateProduct(vtexProduct);
		}
		return productDto;
	}
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "products", produces = MediaType.APPLICATION_JSON_VALUE, params = "skuId")
	public List<ProductDto> getProductDetailByMultipleSku(@PathVariable("region") String region, @RequestParam("skuId") String[] skus,
			@RequestParam(defaultValue = DEFAULT_SC, required = false) String sc) throws CatalogServiceException {
		List<VtexProduct> vtexProducts = serviceFactory.getService(region).getProductsDetail(SKU_ID, skus, sc);
		List<ProductDto> productsDtos = new ArrayList<ProductDto>();
		if (vtexProducts != null) {
			ProductMapper productMapper = serviceFactory.getService(region).getProductMapper();
			for(VtexProduct vtexProd : vtexProducts){
				productsDtos.add(productMapper.generateProduct(vtexProd));
			}
		}
		return productsDtos;
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "deals", produces = MediaType.APPLICATION_JSON_VALUE)
	public SearchResultDto dealsProducts(
	        @PathVariable("region") String region,
			@RequestParam(required = false) String o,
			@RequestParam(defaultValue = "0", required = false) int offset,
			@RequestParam(defaultValue = "0", required = false) int limit,
			@RequestParam(defaultValue = DEFAULT_SC, required = false) String sc,
			@RequestParam(required = false) String filter,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String spec,
            @RequestParam(required = false) String q)
			throws CatalogServiceException {
		LOGGER.info("Get products with deals ");
		SearchResult searchResult = serviceFactory.getService(region).searchDealsProducts(o, offset, limit, filter, brand, spec, q, sc);
		SearchProductMapper searchProductMapper = serviceFactory.getService(region).getSearchProductMapper();
		return searchProductMapper.generateSearchResult(searchResult);
	}
	
}
