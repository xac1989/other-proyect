package com.cencosud.middleware.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cencosud.middleware.product.model.Product;

/**
 * 
 * <h1>ProductRepository</h1>
 * <p>
 * Crea un repository con mongo para ejecutar las funciones contenidas en
 * {@link MongoRepository}<br>
 * y las que se definan en esta clases.
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
public interface ProductRepository extends MongoRepository<Product, String> {

	/**
	 * Busca todos los productos que contienen el mismo ean
	 * 
	 * @param ean
	 *            {@link String}
	 * @return {@link List}<{@link Product}>
	 */
	@Query("{ 'ean':{$regex: ?0} }")
	List<Product> findByEAN(String ean);

	/**
	 * Busca todos los productos que contienen el mismo ean y storeId
	 * 
	 * @param ean
	 *            {@link String}
	 * @param storeId
	 *            {@link String}
	 * @return {@link List}<{@link Product}>
	 */
	@Query(value = "{'ean':{$regex: ?0} , 'stores':{ $elemMatch:{storeId: ?1}}}", fields = "{'productId': 1,'productName': 1,'nameComplete': 1,'productDescription': 1,'skuName': 1,'imageUrl': 1,'detailUrl': 1,'brandId': 1,'brandName': 1,'ean': 1,'images':1,'selloGrasas':1,'selloSodio':1,'selloAzucares':1,'selloCaloricas':1, 'pesable':1,'stores.$':1}")
	List<Product> findByEANandStoreId(String ean, String storeId);
}
