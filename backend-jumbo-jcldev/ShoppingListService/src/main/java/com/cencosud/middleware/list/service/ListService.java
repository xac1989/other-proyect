package com.cencosud.middleware.list.service;

import com.cencosud.middleware.list.dto.ListProductUpdateRequest;
import com.cencosud.middleware.list.dto.ListProductUpdateResponse;
import com.cencosud.middleware.list.dto.ListRemoveProductRequest;
import com.cencosud.middleware.list.dto.ListRemoveProductResponse;
import com.cencosud.middleware.list.dto.ListResponse;
import com.cencosud.middleware.list.dto.ListUpdateRequest;
import com.cencosud.middleware.list.model.ListDocument;
import com.cencosud.middleware.list.model.ProductList;


/**
 * Interfaz para soportar los métodos expuestos en el servicio y la lógica de negocio de cada uno.
 * @author luiggi.mendoza
 * @version 1.0
 */
public interface ListService {

	String getRegionId();

	/**
	 * Actualización de los productos de una lista de usuario
	 * @param request {@link ListProductUpdateRequest} Request con los datos a actualizar
	 * @return {@link ListProductUpdateResponse}
	 */
	ListProductUpdateResponse updateProductList(ListProductUpdateRequest request);

	/**
	 * Actualización de una lista de usuario
	 * @param request {@link ListProductUpdateRequest} Request con los datos a actualizar
	 */
	void updateList(ListUpdateRequest request);
	
	/**
	 * Eliminar productos de una lista
	 * @param request {@link ListRemoveProductRequest} Request con los datos a actualizar
	 * @return {@link ListRemoveProductResponse}
	 */
	ListRemoveProductResponse updateListRemoveProduct(ListRemoveProductRequest request);

	/**
	 * Obtener las listas de un usuario
	 * @param userId Id del usuario
	 * @param email 
	 * @return
	 */
	ListResponse getListByUserId(String userId);

	/**
	 * Crear una nueva lista para un usuario
	 * @param listDocument estructura de la nueva lista
	 * @return detalle de la nueva lista creada, incluyendo su id
	 */
	ListDocument create(ListDocument listDocument);

	/**
	 * Eliminar una lista de un usuario
	 * @param listDocument datos para eliminar la lista
	 */
	void delete(ListDocument listDocument);
	
	/**
	 * Obtener las listas de un producto en especifico
	 * @param userId es el vtexId de un usuario
	 * @param skuId	es el identificador de un producto
	 * @return
	 */
	ProductList getProductList(String userId, String skuId);
}
