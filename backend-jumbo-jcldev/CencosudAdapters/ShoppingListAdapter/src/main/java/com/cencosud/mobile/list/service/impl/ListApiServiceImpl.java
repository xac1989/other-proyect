package com.cencosud.mobile.list.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cencosud.mobile.list.dto.ListPostRequest;
import com.cencosud.mobile.list.dto.ListPostResponse;
import com.cencosud.mobile.list.dto.ListProductUpdateRequest;
import com.cencosud.mobile.list.dto.ListProductUpdateResponse;
import com.cencosud.mobile.list.dto.ListUpdateRequest;
import com.cencosud.mobile.list.dto.ShoppingListDto;
import com.cencosud.mobile.list.dto.UserShoppingListDto;
import com.cencosud.mobile.list.exception.NotFoundException;
import com.cencosud.mobile.list.model.Product;
import com.cencosud.mobile.list.model.ProductList;
import com.cencosud.mobile.list.model.ProductResponse;
import com.cencosud.mobile.list.model.ProductUpdate;
import com.cencosud.mobile.list.model.ShoppingList;
import com.cencosud.mobile.list.model.ShoppingListProduct;
import com.cencosud.mobile.list.model.SkuQuantity;
import com.cencosud.mobile.list.model.UserShoppingList;
import com.cencosud.mobile.list.model.enums.RequestProtocolEnum;
import com.cencosud.mobile.list.service.ListApiService;
import com.cencosud.mobile.list.util.RestServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * <h1>ListApiServiceImpl</h1>
 * <p>
 * Servicio del adapter que ejecuta las consultas al API
 * </p>
 * 
 * @version 1.0
 * @since Jun 30, 2017
 */
@Service
public class ListApiServiceImpl implements ListApiService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ListApiService.class);

	private static final String BASE_LIST_PATH = "/lists";
	private static final String RESOURCE_LIST_PATH = "lists";
	
	private static final String BASE_PRODUCT_PATH="/catalog";
	private static final String RESOURCE_PRODUCT_PATH="product";
	private static final String RESOURCE_PRODUCTS_PATH="products";
	
	private static final String SALES_CHANNEL = "1";

	private RestServiceUtil restServiceUtil;

	/**
	 * @return the restServiceUtil
	 */
	public RestServiceUtil getRestServiceUtil() {
		return restServiceUtil;
	}

	/**
	 * @param restServiceUtil
	 *            the restServiceUtil to set
	 */
	public void setRestServiceUtil(RestServiceUtil restServiceUtil) {
		this.restServiceUtil = restServiceUtil;
	}

	public static void main(String[] args) {
		ListApiServiceImpl listApi = new ListApiServiceImpl();

		RestServiceUtil restService = new RestServiceUtil();
		restService.setApiBaseUrl("https://api.us.apiconnect.ibmcloud.com/supermercados-cencosud-wong-development/wong-dev");
		restService.setApiClientId("9980e9d4-5fda-4164-bbbe-469a12fc5471");
		restService.setApiSecret("aG1sA4sS4eO2qP6mJ5iA7oX8tN3kC3fY5bD6tI7wL2xN8vP4eB");

		listApi.setRestServiceUtil(restService);

		try {
			long start = System.currentTimeMillis();
			UserShoppingList usl = listApi.listsGet("83d57072-e8ec-486e-9cae-b018e51e1748", "1","r1","v2");
			ObjectMapper mapper = new ObjectMapper();
			long end = System.currentTimeMillis();
			System.out.println("Tiempo de ejecución: " + (end - start) + "ms");
			System.out.println(mapper.writeValueAsString(new UserShoppingListDto(usl)));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public UserShoppingList listsGet(String userId, final String salesChannel,final String region,final String version) throws NotFoundException {
		try {
			Map<String, Object> queryParam = new HashMap<>();
			queryParam.put("userId", userId);
			queryParam.put("salesChannel", salesChannel);

			UserShoppingList userShoppingList = restServiceUtil.executeService(getListPath(region,version), null, UserShoppingList.class,
					queryParam, RequestProtocolEnum.GET);
			// System.out.println("Call product by skuId.");
			List<ShoppingList> listShoppingList = userShoppingList.getShoppingList();
			Set<String> skusSet = new HashSet<>();
			for (final ShoppingList shop : listShoppingList) {
				List<ShoppingListProduct> shoppingListProductList = shop.getSkus();
				for (final ShoppingListProduct shoppingListProduct : shoppingListProductList) {
					skusSet.add(shoppingListProduct.getId());
				}

			}
			if(!skusSet.isEmpty()) {
				
				// get map of products from Catalg service
				Map<String, Product> products = getMapProduct(skusSet, salesChannel,region,version);
				
				for (final ShoppingList shop : listShoppingList) {
					List<ShoppingListProduct> shoppingListProductList = shop.getSkus();
					for (final ShoppingListProduct shoppingListProduct : shoppingListProductList) {
						Product product = products.get(shoppingListProduct.getId());
						shoppingListProduct.updateFromProduct(product);
						shoppingListProduct.setSpecifications(null);
					}
				}
			}
			return userShoppingList;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new NotFoundException(404, e.getMessage());
		}
	}

	@Override
	public ProductList getListProductGet(String userId, String skuId,final String region,final String version) throws NotFoundException {
		try {
			Map<String, Object> queryParams = new HashMap<>();
			queryParams.put("userId", userId);
			queryParams.put("skuId", skuId);
			String finalPath = BASE_LIST_PATH.concat("/").concat(region).concat("/").concat(version).concat("/")
					.concat(RESOURCE_LIST_PATH);
			
			ProductList productList = restServiceUtil.executeService(finalPath.concat("/products"), null,
					ProductList.class, queryParams, RequestProtocolEnum.GET);
			return productList;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new NotFoundException(404, e.getMessage());
		}
	}

	private ProductResponse getProduct(String skuId, String salesChannel,final String region,final String version) throws NotFoundException {
		try {
			Map<String, Object> queryParams = new HashMap<>();
			queryParams.put("sc", salesChannel);
			
			String productPath = BASE_PRODUCT_PATH.concat("/").concat(region).concat("/").concat(version).concat("/")
					.concat(RESOURCE_PRODUCT_PATH);

			ProductResponse listResponse = restServiceUtil.executeService(productPath.concat("/sku/").concat(skuId),
					null, ProductResponse.class, queryParams, RequestProtocolEnum.GET);
			return listResponse;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new NotFoundException(404, e.getMessage());
		}
	}

	private Map<String, Product> getMapProduct(Set<String> skusSet, String salesChannel,final String region,final String version) throws NotFoundException {
		try {
			String skus = StringUtils.join(skusSet, ",");
			
			Map<String, Object> queryParams = new HashMap<>();
			queryParams.put("sc", salesChannel);
			queryParams.put("skuId", skus);
			LOG.info("---------------------------------->"+skus);
			
			String productsPath = BASE_PRODUCT_PATH.concat("/").concat(region).concat("/").concat(version).concat("/")
					.concat(RESOURCE_PRODUCTS_PATH);
			
			Product[] listResponse = restServiceUtil.executeService(productsPath, null, Product[].class, queryParams,
					RequestProtocolEnum.GET);
			Map<String, Product> response = new HashMap<>(listResponse.length);
			for (Product tmpProduct : listResponse) {
				response.put(tmpProduct.getSkuId(), tmpProduct);
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new NotFoundException(404, e.getMessage());
		}
	}

	public ShoppingListDto updateList(ListUpdateRequest request,final String region,final String version) throws NotFoundException {
		try {
			int quantity = 0;
			restServiceUtil.executeService(getListPath(region,version), request, Void.class, null, RequestProtocolEnum.PUT);
			List<ShoppingListProduct> shoppingListProductList = new ArrayList<>(request.getProducts().size());
			for (ProductUpdate tmpProduct : request.getProducts()) {
				ShoppingListProduct shoppingListProduct = new ShoppingListProduct();
				shoppingListProduct.setId(tmpProduct.getSkuId());
				shoppingListProduct.setSkuQuantity(new SkuQuantity(tmpProduct.getQuantity()));
				System.out.println("@@@ sku -> " + tmpProduct.getSkuId());
				Product product = this.getProduct(tmpProduct.getSkuId(), SALES_CHANNEL,region,version).getProduct();
				System.out.println("Product -> " + product.toString());
				shoppingListProduct.updateFromProduct(product);
				shoppingListProduct.setSpecifications(null);
				shoppingListProductList.add(shoppingListProduct);
				quantity += tmpProduct.getQuantity();
			}
			ShoppingList shoppingList = new ShoppingList();
			shoppingList.setListId(request.getListId());
			shoppingList.setQuantity(quantity);
			shoppingList.setSkus(shoppingListProductList);
			shoppingList.setSkusQuantity(request.getProducts().size());
			ShoppingListDto response = new ShoppingListDto(shoppingList);
			return response;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new NotFoundException(404, e.getMessage());
		}
	}

	public ListProductUpdateResponse updateProductList(ListProductUpdateRequest request,final String region,final String version) throws NotFoundException {
		try {
			ListProductUpdateResponse listResponse = restServiceUtil.executeService(getListPath(region,version).concat("/products"),
					request, ListProductUpdateResponse.class, null, RequestProtocolEnum.PUT);
			return listResponse;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new NotFoundException(404, e.getMessage());
		}
	}

	@Override
	public ListPostResponse createList(ListPostRequest request,final String region,final String version) throws NotFoundException {
		try {
			ListPostResponse listResponse = restServiceUtil.executeService(getListPath(region,version), request, ListPostResponse.class,
					null, RequestProtocolEnum.POST);
			return listResponse;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new NotFoundException(404, e.getMessage());
		}
	}

	@Override
	public void deleteList(String listId, String userId,final String region,final String version) throws NotFoundException {
		try {
			Map<String, Object> queryParams = new HashMap<>();
			queryParams.put("userId", userId);
			queryParams.put("listId", listId);
			restServiceUtil.executeService(getListPath(region,version), null, Void.class, queryParams, RequestProtocolEnum.DELETE);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new NotFoundException(404, e.getMessage());
		}
	}

	private String getListPath(String region, String version) {
		return BASE_LIST_PATH.concat("/").concat(region).concat("/").concat(version).concat("/").concat(RESOURCE_LIST_PATH);
	}
}
