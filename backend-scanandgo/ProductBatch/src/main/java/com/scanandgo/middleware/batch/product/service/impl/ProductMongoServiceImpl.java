package com.scanandgo.middleware.batch.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.product.model.Product;
import com.scanandgo.middleware.batch.product.repository.ProductRepository;
import com.scanandgo.middleware.batch.product.service.ProductMongoService;

/**
 * 
 * 
 * <h1>ProductMongoServiceImpl</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 24, 2017
 */
@Service
public class ProductMongoServiceImpl implements ProductMongoService {

	@Autowired
	private ProductRepository repository;

	@Override
	public Product saveOrUpdate(Product product) {
		return repository.save(product);
	}

	@Override
	public List<Product> saveOrUpdate(List<Product> products) {
		return repository.save(products);
	}

	@Override
	public Product findByEAN(String ean) {
		List<Product> lstProduct = null;
			lstProduct = repository.findByEAN(ean);
		if(lstProduct != null && lstProduct.size()>0)
			return lstProduct.get(0);
		return null;
	}

}
