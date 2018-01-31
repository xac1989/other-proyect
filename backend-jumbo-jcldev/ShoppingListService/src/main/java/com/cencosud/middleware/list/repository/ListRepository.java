package com.cencosud.middleware.list.repository;

import java.util.List;

import com.cencosud.middleware.list.model.ListDocument;

/**
 * 
 * 
 * <h1>ListRepository</h1>
 * <p>
 * Contrato con metodos a implementar para el repository
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
public interface ListRepository {
	
	/**
	 * Realiza ejecución del servicio Vtex para actualizar la lista 
	 * @param request {@link ListDocument} Request con los datos a actualizar
	 * @return {@link ListDocument}
	 */
	ListDocument updateProductList(ListDocument request);
	
//	/**
//	 * Realiza ejecución del servicio Vtex para obtener las listas de usuario
//	 * @param userId {@link String} Identificador de usuario
//	 * @return {@link List}<{@link UserList}>
//	 */
//	List<UserList> getListByUserId(String userId);
	
	/**
	 * Realiza ejecución del servicio Vtex para obtener las listas por identificador de lista
	 * @param listId {@link String} Identificador de de lista
	 * @return {@link ListDocument}
	 */
	ListDocument getById(String listId);

	/**
	 * Crea una nueva lista en Vtex
	 * @param listDocument Lista a registrar
	 * @return {@link ListDocument}
	 */
	ListDocument create(ListDocument listDocument);

	/**
	 * Elimina una lista de Vtex
	 * @param listDocument
	 */
	void delete(ListDocument listDocument);

	/**
	 * Obtener todas las listas registradas para un usuario 
	 * @param userId
	 * @return
	 */
	List<ListDocument> getByUserId(String userId);
	
	/**
	 * Busca por una lista de id de listas 
	 * @param listIdList
	 * @return
	 */
	List<ListDocument> getByListId(List<String> listIdList);

	/**
	 * 
	 * @param listIdList
	 * @return
	 */
	List<ListDocument> getByListIdOneByOne(List<String> listIdList);
}
