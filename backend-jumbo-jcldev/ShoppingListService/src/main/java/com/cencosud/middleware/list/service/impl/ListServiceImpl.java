package com.cencosud.middleware.list.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cencosud.middleware.list.dto.ListProductUpdateRequest;
import com.cencosud.middleware.list.dto.ListProductUpdateResponse;
import com.cencosud.middleware.list.dto.ListRemoveProductRequest;
import com.cencosud.middleware.list.dto.ListRemoveProductResponse;
import com.cencosud.middleware.list.dto.ListResponse;
import com.cencosud.middleware.list.dto.ListUpdateRequest;
import com.cencosud.middleware.list.model.ListDocument;
import com.cencosud.middleware.list.model.ListFull;
import com.cencosud.middleware.list.model.ListUpdate;
import com.cencosud.middleware.list.model.Product;
import com.cencosud.middleware.list.model.ProductList;
import com.cencosud.middleware.list.model.ProductShoppingList;
import com.cencosud.middleware.list.model.ShoppingList;
import com.cencosud.middleware.list.model.Sku;
import com.cencosud.middleware.list.model.SkuQuantity;
import com.cencosud.middleware.list.model.UserList;
import com.cencosud.middleware.list.repository.ListRepository;
import com.cencosud.middleware.list.repository.UserListRepository;
import com.cencosud.middleware.list.service.ListService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * <h1>ListServiceImpl</h1>
 * <p>
 * Implementación de los métodos declarados en el contrato del servicio.
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
@Service("listService")
public class ListServiceImpl implements ListService {
	
	private static final String MESSAGE_ERROR = "error";

	private ObjectMapper mapper = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(ListServiceImpl.class);
	private static final String LIST_TYPE = "client";

	// Region2 = JUMBO CHILE
	private static final String REGION_ID = "r2";
	

	@Autowired
	@Qualifier("listRepository")
	private ListRepository listRepository;

	@Autowired
	private UserListRepository userListRepository;

	@Override
	public String getRegionId() {
		return REGION_ID;
	}

	@Override
	public ListProductUpdateResponse updateProductList(ListProductUpdateRequest request) {
		for (ListUpdate temList : request.getLists()) {
			try {
				ListDocument listDocument = listRepository.getById(temList.getListId());
				if(StringUtils.isNotEmpty(temList.getListId()) && listDocument.getId() == null) {
					listDocument = new ListDocument();
					listDocument.setId(temList.getListId());
					listDocument.setIsStoreList(false);
					listDocument.setType(LIST_TYPE);
				} else if(StringUtils.isEmpty(temList.getListId())){
					temList.setStatus(MESSAGE_ERROR);
					continue;
				}
				String mapSkus = listDocument.getSkus();
				Map<String, SkuQuantity> mapTempJson;
				if (StringUtils.isNotEmpty(mapSkus)) {
					mapTempJson = mapper.readValue(mapSkus,
							new TypeReference<Map<String, SkuQuantity>>() {
							});
	
					if (temList.getQuantity().equals(0)) {
						mapTempJson.remove(request.getSkuId());
					} else {
						mapTempJson.put(request.getSkuId(), new SkuQuantity(temList.getQuantity()));
					}
				} else {
					mapTempJson = new HashMap<>();
					if (!temList.getQuantity().equals(0)) {
						mapTempJson.put(request.getSkuId(), new SkuQuantity(temList.getQuantity()));
					}
				}
				String strJson = mapper.writeValueAsString(mapTempJson);
				listDocument.setSkus(strJson);
				listDocument.setActive(true);
				listDocument.setAdded(false);
				listDocument.setQuantity(mapTempJson.size());
				
				listRepository.updateProductList(listDocument);
				temList.setStatus("ok");
				temList.setName(listDocument.getName());
			} catch (Exception e) {
				temList.setStatus(MESSAGE_ERROR);
				logger.error("Error al actualizar la lista.", e);
			}
		}

		return new ListProductUpdateResponse(request.getSkuId(), request.getLists());
	}

	private ShoppingList map(ListDocument listDocument) {
		ShoppingList shoppingList = new ShoppingList();
		int skusQuantity = 0;
		int quantityCount = 0;
		shoppingList.setListId(listDocument.getId());
		shoppingList.setName(listDocument.getName());

		logger.debug("@@@@ obteniendo skus desde lista::: {}", listDocument.getSkus());
		String skusForm = listDocument.getSkus();
		List<Sku> skus = null;

		if (StringUtils.isNotEmpty(skusForm)) {
			try {
				Map<String, SkuQuantity> mapSkus = mapper.readValue(skusForm, new TypeReference<Map<String, SkuQuantity>>() { });
				skus = new ArrayList<>(mapSkus.size());
				for (Map.Entry<String, SkuQuantity> entry : mapSkus.entrySet()) {
					Sku sku = new Sku();
					logger.debug("@@@@ " + entry.getKey() + ", " + entry.getValue());
					if (!entry.getKey().toString().contains("undefined")) {
						sku.setId(entry.getKey().toString());
						sku.setSkuQuantity(entry.getValue());
						skusQuantity++;
						quantityCount += sku.getSkuQuantity().getQuantity();
						skus.add(sku);
					}
				}
			} catch (IOException e) {
				logger.error("Error deserializando información de skus de lista " + listDocument.getId(), e);
			}
		} else {
			skus = Collections.<Sku>emptyList();
		}
		shoppingList.setSkus(skus);
		shoppingList.setSkusQuantity(skusQuantity);
		shoppingList.setQuantity(quantityCount);
		return shoppingList;
	}

	@Override
	public ListResponse getListByUserId(String userId) {
		UserList userList = userListRepository.findByUserId(userId);

		if (userList == null || CollectionUtils.isEmpty(userList.getShoppingListFromJson())) {
			return new ListResponse(userId, new ArrayList<ShoppingList>());
		}
		List<ListDocument> userLists = listRepository.getByListIdOneByOne(userList.getShoppingListFromJson());
		List<ShoppingList> userShoppingList = new ArrayList<>(userLists.size());
		
		for (ListDocument listDocument : userLists) {
			if(!listDocument.getDefaultList()) {
				userShoppingList.add(map(listDocument));
			}
		}

		ListFull listFull = new ListFull();
		listFull.setUserId(userId);
		listFull.setShoppingList(userShoppingList);
		ListResponse listResponse = this.mapListToResponse(listFull);

		return listResponse;
	}

	@Override
	public ListDocument create(ListDocument listDocument) {
		listDocument.setIsStoreList(false);
		listDocument.setType(LIST_TYPE);

		ListDocument newListDocument = listRepository.create(listDocument);
		try {
			UserList userList = userListRepository.findByUserId(newListDocument.getUserId());
			if (userList == null) {
				List<String> lstShopping = Arrays.asList(newListDocument.getId());
				userList = new UserList(listDocument.getUserId(), mapper.writeValueAsString(lstShopping), lstShopping);
			} else {
				userList.getShoppingListFromJson().add(newListDocument.getId());
			}
			userListRepository.update(userList);
		} catch (Exception e) {
			logger.error("Error al crear la relación de usuario y sus listas", e);
			logger.warn("Se intentara hacer un rollback de la lista creada previamente.");
			listRepository.delete(newListDocument);
			newListDocument.setId(StringUtils.EMPTY);
		}
		return newListDocument;
	}

	@Override
	public void delete(ListDocument listDocument) {
		listRepository.delete(listDocument);
		UserList userList = userListRepository.findByUserId(listDocument.getUserId());
		userList.getShoppingListFromJson().remove(listDocument.getId());
		userListRepository.update(userList);
	}

	@Override
	public ListRemoveProductResponse updateListRemoveProduct(ListRemoveProductRequest request) {
		try {
			ListDocument listDocument = listRepository.getById(request.getListId());

			String mapSkus = listDocument.getSkus();
			Map<String, SkuQuantity> mapTempJson = mapper.readValue(mapSkus,
					new TypeReference<Map<String, SkuQuantity>>() {
					});

			for (Integer tmpSku : request.getSkuIds()) {
				mapTempJson.remove(tmpSku.toString());
			}

			String strJson = mapper.writeValueAsString(mapTempJson);
			listDocument.setSkus(strJson);
			listDocument.setActive(true);
			listDocument.setAdded(false);
			listDocument.setQuantity(mapTempJson.size());

			listRepository.updateProductList(listDocument);
			return new ListRemoveProductResponse(request.getListId(), "ok");
		} catch (Exception e) {
			logger.error("Error al actualizar la lista.", e);
			return new ListRemoveProductResponse(request.getListId(), MESSAGE_ERROR);
		}
	}

	private ListResponse mapListToResponse(ListFull listFull) {

		ListResponse response = new ListResponse();
		if (listFull == null)
			return response;
		response.setUserId(listFull.getUserId());
		response.setShoppingList(listFull.getShoppingList());

		return response;
	}

	@Override
	public ProductList getProductList(String userId, String skuId) {
		ListResponse listResponse = getListByUserId(userId);
		ProductList productList = new ProductList();
		productList.setUserId(userId);

		List<ProductShoppingList> productShoppingLists = new ArrayList<>();
		for (ShoppingList shoppingList : listResponse.getShoppingList()) {
			ProductShoppingList productShoppingList = new ProductShoppingList();
			productShoppingList.setListId(shoppingList.getListId());
			productShoppingList.setName(shoppingList.getName());
			productShoppingList.setQuantity(0);
			for (Sku sku : shoppingList.getSkus()) {
				if (skuId.equals(sku.getId())) {
					productShoppingList.setQuantity(sku.getSkuQuantity().getQuantity());
				}
			}
			productShoppingLists.add(productShoppingList);
		}
		productList.setProductShoppingList(productShoppingLists);

		return productList;
	}

	@Override
	public void updateList(ListUpdateRequest request) {
		try {
			ListDocument listDocument = listRepository.getById(request.getListId());
			if(!StringUtils.isEmpty(listDocument.getId())) {
				Map<String, SkuQuantity> mapTempJson = new HashMap<>(request.getProducts().size());
				listDocument.setActive(true);
				listDocument.setAdded(false);
				listDocument.setQuantity(request.getProducts().size());
				for(Product tmpProduct:request.getProducts()){
					mapTempJson.put(tmpProduct.getSkuId(), new SkuQuantity(tmpProduct.getQuantity()));
				}
				
				String strJson = mapper.writeValueAsString(mapTempJson);
				listDocument.setSkus(strJson);
	
				listRepository.updateProductList(listDocument);
			}
		} catch (Exception e) {
			logger.error("Error al actualizar la lista.", e);
		}
		
	}
}
