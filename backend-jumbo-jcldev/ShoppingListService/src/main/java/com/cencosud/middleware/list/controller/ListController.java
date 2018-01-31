package com.cencosud.middleware.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.list.dto.ListPostRequest;
import com.cencosud.middleware.list.dto.ListPostResponse;
import com.cencosud.middleware.list.dto.ListProductUpdateRequest;
import com.cencosud.middleware.list.dto.ListProductUpdateResponse;
import com.cencosud.middleware.list.dto.ListRemoveProductRequest;
import com.cencosud.middleware.list.dto.ListRemoveProductResponse;
import com.cencosud.middleware.list.dto.ListResponse;
import com.cencosud.middleware.list.dto.ListUpdateRequest;
import com.cencosud.middleware.list.dto.ProductListGetResponse;
import com.cencosud.middleware.list.factory.ShoppingListServiceFactory;
import com.cencosud.middleware.list.model.ListDocument;
import com.cencosud.middleware.list.model.ProductList;

/**
 * 
 * 
 * <h1>ListController</h1>
 * <p>
 * Métodos expuestos por el servicio web
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
@RestController()
@RequestMapping(path = { "/{region}/v1/lists", "/{region}/v1/lists/" }, produces = "application/json; charset=UTF-8")
public class ListController {

	private ShoppingListServiceFactory serviceFactory;

	@Autowired
	public void setServiceFactory(ShoppingListServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<ListResponse> getList(@RequestParam(required = true) String userId,
			@PathVariable("region") String region) {

		ListResponse response = serviceFactory.getService(region).getListByUserId(userId);
		return new ResponseEntity<ListResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/productList", produces = "application/json; charset=UTF-8")
	public ResponseEntity<ProductListGetResponse> getListByProduct(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String skuId, @PathVariable("region") String region) {

		ProductList productList = serviceFactory.getService(region).getProductList(userId, skuId);
		return new ResponseEntity<ProductListGetResponse>(new ProductListGetResponse(productList), HttpStatus.OK);
	}

	/**
	 * Método que inicia el proceso de guardar las modificaciones de la lista
	 * completa
	 * 
	 * @param request
	 *            {@link ListUpdateRequest} Objeto request del servicio
	 * @param region
	 *            {@link String} Región
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Void> putList(@RequestBody(required = true) ListUpdateRequest request,
			@PathVariable("region") String region) {
		serviceFactory.getService(region).updateList(request);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Método que inicia el proceso de guardar las modificaciones de la lista
	 * 
	 * @param request
	 *            {@link ListUpdateRequest} Objeto request del servicio
	 * @param region
	 *            {@link String} Región
	 * @return
	 */
	@RequestMapping(path = { "/products",
			"products" }, method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<ListProductUpdateResponse> putProductList(
			@RequestBody(required = true) ListProductUpdateRequest request, @PathVariable("region") String region) {
		ListProductUpdateResponse response = serviceFactory.getService(region).updateProductList(request);
		return new ResponseEntity<ListProductUpdateResponse>(response, HttpStatus.OK);
	}

	/**
	 * Método para iniciar el proceso de eliminar multiples productos de una
	 * lista
	 * 
	 * @param request
	 *            {@link ListRemoveProductRequest} Objeto request del servicio
	 * @param region
	 *            {@link String} Región
	 * @return
	 */
	@RequestMapping(path = { "removeProducts",
			"/removeProducts" }, method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<ListRemoveProductResponse> putListRemoveProduct(
			@RequestBody(required = true) ListRemoveProductRequest request, @PathVariable("region") String region) {
		ListRemoveProductResponse response = serviceFactory.getService(region).updateListRemoveProduct(request);
		return new ResponseEntity<ListRemoveProductResponse>(response, HttpStatus.OK);
	}

	/**
	 * Método para eliminar una lista
	 * 
	 * @param listId
	 *            {@link String}
	 * @param region
	 *            {@link String} región sobre la cual eliminar la lista
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestParam(required = true) String listId,
			@RequestParam(required = true) String userId, @PathVariable("region") String region) {
		ListDocument listDocument = new ListDocument();
		listDocument.setId(listId);
		listDocument.setUserId(userId);
		serviceFactory.getService(region).delete(listDocument);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Método para crear una lista
	 * 
	 * @param request
	 *            {@link ListPostRequest}
	 * @param region
	 *            {@link String} región sobre la cual eliminar la lista
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ListPostResponse> create(@RequestBody(required = true) ListPostRequest request,
			@PathVariable("region") String region) {
		ListDocument listDocument = new ListDocument();
		listDocument.setUserId(request.getUserId());
		listDocument.setName(request.getName());

		ListDocument result = serviceFactory.getService(region).create(listDocument);
		return new ResponseEntity<ListPostResponse>(new ListPostResponse(result), HttpStatus.OK);
	}
}
