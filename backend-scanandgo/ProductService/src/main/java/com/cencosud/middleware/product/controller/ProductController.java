package com.cencosud.middleware.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.product.model.Product;
import com.cencosud.middleware.product.service.ProductMongoService;

/**
 * 
 * 
 * <h1>ProductController</h1>
 * <p>
 * Funciones expuestas por el webservice.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
@RestController	
@RequestMapping(path="/product", produces="application/json; charset=UTF-8")
public class ProductController {
	
	@Autowired
	ProductMongoService mongoService;
	
	/**
	 * Crea o actualiza un producto, si el productId existe es actualizado de lo contrario lo crea.
	 * @param product {@link Product}
	 * @return {@link Product}
	 */
	@RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Product save(@RequestBody Product product) {
		return mongoService.saveOrUpdate(product);		
	}
	
	/**
	 * Crea o actualiza una lista de productos, si el productId existe es actualizado de lo contrario lo crea.
	 * @param products {@link List}<{@link Product}>
	 * @return {@link List}<{@link Product}>
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> save(@RequestBody List<Product> products) {
		return mongoService.saveOrUpdate(products);		
	}

	/**
	 * Elimina un producto al proporcionar el productId
	 * @param productId {@link Integer}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestParam(required=true) Integer productId) {		
		mongoService.delete(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Busca un producto al proporcionar los datos necesarios, el ean es un dato <br>
	 * requerido por lo que tiene que ser proporcionado.
	 * @param ean {@link String}
	 * @param storeId {@link String}
	 * @return {@link Product}
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Product findById(@RequestParam(required=true) String ean, @RequestParam(required=false)String storeId) {
		Product porduct =  mongoService.findByEANandStoreId(ean, storeId);
		return porduct;
	}

	/**
	 * Actualiza un producto, si el productId existe
	 * @param product {@link Product} 
	 * @return {@link Product}
	 */
	@RequestMapping(method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public Product update(@RequestBody Product product) {
		return mongoService.update(product);		
	}
}
