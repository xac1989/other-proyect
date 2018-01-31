package com.cencosud.middleware.catalog.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.StringUtils;

import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.SearchInfo;
import com.cencosud.middleware.catalog.model.SearchResult;
import com.cencosud.middleware.catalog.repository.CatalogRepository;
import com.cencosud.middleware.catalog.service.CatalogService;

@RunWith(MockitoJUnitRunner.class)
public class CatalogR2ServiceImplTest {

	@InjectMocks
	CatalogService catalogService = new CatalogR2ServiceImpl();

	@Mock
	private CatalogRepository vtexRepo;
	
	private VtexProduct vtexProduct;
	private SearchResult searchResult;
	private SearchResult searchDealsResult;
	private List<VtexProduct> productsByMultipleSku;

	private

	final static String FILTER = "/Despensa/Abarrotes/Aceite", 
			Q = "ProductoX", 
			O = "OrderByTopSaleDESC", 
			SC = "1", 
			BRAND = "Soprole", 
			BRANDS = "Soprole,Nestle,Svelti",
			SPEC = "40,Deslactosada", 
			SPECS = "40,Deslactosada/30,No/30,Si";
	final static int OFFSET = 0, LIMIT = 1;
	final static String PRODUCT_ID = "1490", INT_SC = "1";
	final static String [] SKU_IDS = {"1494","1493","1491","1490","1489","10436","9833","9542","8881","8880"};
	private static final String SEARCH_BY_SKU_ID = "SKU";

	@Before
	public void setUp() throws Exception {
		this.searchResult = createSearchResult();
		this.vtexProduct = createVtexProduct();
		this.searchDealsResult = createSearchDealsResult();
		this.productsByMultipleSku = createVtextProducsList();
	}

	@Test
	public void getProductDetail() throws CatalogServiceException {
		getProductDetailPreconditions();
		VtexProduct vtexProduct = catalogService.getProductDetail(PRODUCT_ID, null, INT_SC);
		Assert.assertTrue(vtexProduct != null);
	}

	@Test
	public void searchProductsByFilter() throws CatalogServiceException {
		searchProductsPreconditions(FILTER, null, null, null, O, OFFSET, LIMIT, INT_SC, "searchProductsByFilter");
		SearchResult catalogResponse = catalogService.searchProducts(FILTER, null, null, "", O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void searchProductsByQ() throws CatalogServiceException {
		searchProductsPreconditions(null, null, null, Q, O, OFFSET, LIMIT, INT_SC, "searchProductsByQ");
		SearchResult catalogResponse = catalogService.searchProducts(null, null, null, Q, O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void searchProductsByBrand() throws CatalogServiceException {
		searchProductsPreconditions(null, BRAND, null, Q, O, OFFSET, LIMIT, INT_SC, "searchProductsByBrand");
		SearchResult catalogResponse = catalogService.searchProducts(null, BRAND, null, null, O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void searchProductsByBrands() throws CatalogServiceException {
		searchProductsPreconditions(null, BRANDS, null, Q, O, OFFSET, LIMIT, INT_SC, "searchProductsByBrands");
		SearchResult catalogResponse = catalogService.searchProducts(null, BRANDS, null, null, O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void searchProductsBySpec() throws CatalogServiceException {
		searchProductsPreconditions(null, null, SPEC, Q, O, OFFSET, LIMIT, INT_SC, "searchProductsBySpec");
		SearchResult catalogResponse = catalogService.searchProducts(null, null, SPEC, null, O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void searchProductsBySpecs() throws CatalogServiceException {
		searchProductsPreconditions(null, null, SPECS, Q, O, OFFSET, LIMIT, INT_SC, "searchProductsBySpec");
		SearchResult catalogResponse = catalogService.searchProducts(null, null, SPEC, null, O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void searchProductsByFilterBrandSpecAndQ() throws CatalogServiceException {
		searchProductsPreconditions(FILTER, BRAND, SPEC, Q, O, OFFSET, LIMIT, INT_SC, "searchProductsByFilterBrandSpecAndQ");
		SearchResult catalogResponse = catalogService.searchProducts(FILTER, BRAND, SPEC, Q, O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void searchProductsByFilterBrandsSpecsAndQ() throws CatalogServiceException {
		searchProductsPreconditions(FILTER, BRANDS, SPECS, Q, O, OFFSET, LIMIT, INT_SC, "searchProductsByFilterBrandsSpecsAndQ");
		SearchResult catalogResponse = catalogService.searchProducts(FILTER, BRANDS, SPECS, Q, O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void getPoductsBySkuid() throws CatalogServiceException{
		getProductsDetailBySkusPreconditions();
		List<VtexProduct> products = catalogService.getProductsDetail(SEARCH_BY_SKU_ID, SKU_IDS, INT_SC);
		assertThat(products.size(), is(SKU_IDS.length));
	}
	
	@Test
	public void searchProductsDeals() throws CatalogServiceException {
		searchProductsPreconditions("", null, null, "", O, OFFSET, LIMIT, INT_SC, "searchProductsDeals");
		SearchResult catalogResponse = catalogService.searchProducts(null, null, null, "H:136", O, OFFSET, LIMIT, INT_SC);
		assertThat(catalogResponse.getProducts().size(), is(greaterThan(0)));
	}


	/***
	 * Preconditions
	 */
	
	private void getProductDetailPreconditions() throws CatalogServiceException {
		given(vtexRepo.getProductDetail(PRODUCT_ID,null, INT_SC)).willReturn(vtexProduct);
	}
	
	private void searchProductsPreconditions(String filter, String brand, String spec, String q, String o, int offset, int limit,String sc, String method)
			throws CatalogServiceException {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		String path = "";
		
		switch(method){
			case "searchProductsByFilter":
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				parameters.add(new BasicNameValuePair("map", "c,c,c"));
				path = filter; 
			break;
			case "searchProductsByQ":
				parameters.add(new BasicNameValuePair("ft","ProductoX"));
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				path = "";
			break;
			case "searchProductsByBrand":
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				parameters.add(new BasicNameValuePair("map","b"));
				path = "/Soprole"; 		
			break;
			case "searchProductsByBrands":
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				parameters.add(new BasicNameValuePair("map","b,b,b"));
				path = "/Soprole/Nestle/Svelti"; 		
			break;
			case "searchProductsBySpec":
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				parameters.add(new BasicNameValuePair("map","specificationFilter_40"));
				path = "/Deslactosada"; 		
			break;
			case "searchProductsBySpecs":
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				parameters.add(new BasicNameValuePair("map","specificationFilter_40,specificationFilter_30,specificationFilter_30"));
				path = "/Deslactosada/No/Si"; 		
			break;
			case "searchProductsByFilterBrandSpecAndQ":
				parameters.add(new BasicNameValuePair("ft","ProductoX"));
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				parameters.add(new BasicNameValuePair("map","c,c,c,specificationFilter_40,b"));
				path = "/Despensa/Abarrotes/Aceite/Deslactosada/Soprole"; 		
			break;
			case "searchProductsByFilterBrandsSpecsAndQ":
				parameters.add(new BasicNameValuePair("ft","ProductoX"));
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				parameters.add(new BasicNameValuePair("map","c,c,c,specificationFilter_40,specificationFilter_30,specificationFilter_30,b,b,b"));
				path = "/Despensa/Abarrotes/Aceite/Deslactosada/No/Si/Soprole/Nestle/Svelti"; 		
			break;
			case "searchProductsDeals":
				parameters.add(new BasicNameValuePair("ft","H:136"));
				parameters.add(new BasicNameValuePair("O", o));
				parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
				parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
				parameters.add(new BasicNameValuePair("sc", sc));
				//parameters.add(new BasicNameValuePair("map","b,b,b"));
				path = filter;
			break;
		}
		
		given(vtexRepo.searchProducts(path, parameters, sc)).willReturn(searchResult);
	}
	
	private void getProductsDetailBySkusPreconditions() throws CatalogServiceException{
		given(vtexRepo.getProductsDetail(SEARCH_BY_SKU_ID, SKU_IDS, INT_SC)).willReturn(productsByMultipleSku);
	}
	
	
	/***
	 * Builders
	 * 
	 */
	
	private VtexProduct createVtexProduct() throws Exception {
		String jsonVtexJumbo = new String(Files.readAllBytes(Paths.get("src/test/resources/jumboVtextProduct.json")));
		JSONArray jsonArray = new JSONArray(jsonVtexJumbo);
		VtexProduct vtexProduct = new VtexProduct(jsonArray.getJSONObject(0));
		return vtexProduct;

	}
	
	private SearchResult createSearchResult() throws IOException {

		SearchInfo searchInfo = new SearchInfo(9, 146);
		String jsonVtexJumboList = new String(
				Files.readAllBytes(Paths.get("src/test/resources/jumboVtextProductList.json")));

		JSONArray products = new JSONArray(jsonVtexJumboList);
		List<VtexProduct> listProducts = new ArrayList<VtexProduct>();
		for (Object product : products) {
			listProducts.add(new VtexProduct((JSONObject) product));
		}

		SearchResult searchResult = new SearchResult(listProducts, searchInfo);
		return searchResult;
	}
	
	private List<VtexProduct> createVtextProducsList() throws IOException{
		String jsonVtexJumboList = new String(
				Files.readAllBytes(Paths.get("src/test/resources/jumboVtextProductList.json")));
		List<VtexProduct> vtexProducts = new ArrayList<VtexProduct>();
		VtexProduct vtexProduct;
		if (!StringUtils.isEmpty(jsonVtexJumboList)) {
			JSONArray jsonArray = new JSONArray(jsonVtexJumboList);
				for(Object json : jsonArray){
					vtexProduct = new VtexProduct((JSONObject) json);
					vtexProduct.setSalesChannel(INT_SC);
					vtexProducts.add(vtexProduct);
				}
		
		}
		return vtexProducts;
	}
	
	private SearchResult createSearchDealsResult() throws IOException {

		SearchInfo searchInfo = new SearchInfo(9, 146);
		String jsonVtexJumboList = new String(
				Files.readAllBytes(Paths.get("src/test/resources/jumboVtextProductDealsList.json")));

		JSONArray products = new JSONArray(jsonVtexJumboList);
		List<VtexProduct> listProducts = new ArrayList<VtexProduct>();
		for (Object product : products) {
			listProducts.add(new VtexProduct((JSONObject) product));
		}

		SearchResult searchResult = new SearchResult(listProducts, searchInfo);
		return searchResult;
	}

}
