package com.scanandgo.middleware.batch.product.service;

import java.util.List;

import com.cencosud.middleware.product.model.Product;

/**
 * 
 * 
 * <h1>ProductMongoService</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 24, 2017
 */
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
	 * Busca un producto al proporcionar los datos necesarios, el ean es un dato <br>
	 * requerido por lo que tiene que ser proporcionado.
	 * @param ean {@link String}
	 * @param storeId {@link String}
	 * @return {@link Product}
	 */
	Product findByEAN(String ean);
}
