package com.cencosud.middleware.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cencosud.middleware.product.model.Product;

public interface ProductMongoService {
	
	/**
	 * Crea o actualiza un producto, si el productId existe es actualizado de lo contrario lo crea.
	 * @param product {@link Product}
	 * @return {@link Product}
	 */
	Product saveOrUpdate(Product product);
	
	/**
	 * Crea o actualiza una lista de productos, si el productId existe es actualizado de lo contrario lo crea.
	 * @param products {@link List}<{@link Product}>
	 * @return {@link List}<{@link Product}>
	 */
	List<Product> saveOrUpdate(List<Product> products);

	/**
	 * Actualiza un producto, si el productId existe
	 * @param product {@link Product} 
	 * @return {@link Product}
	 */
	Product update(Product product);
	
	/**
	 * Elimina un producto al proporcionar el productId
	 * @param productId {@link Integer}
	 * @return {@link ResponseEntity}
	 */
	void delete(Integer productId);

	/**
	 * Busca un producto al proporcionar los datos necesarios, el ean es un dato <br>
	 * requerido por lo que tiene que ser proporcionado.
	 * @param ean {@link String}
	 * @param storeId {@link String}
	 * @return {@link Product}
	 */
	Product findByEANandStoreId(String ean, String storeId);
}
