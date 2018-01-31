package com.scanandgo.middleware.batch.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.product.model.Product;
import com.scanandgo.middleware.batch.product.exception.ProductServiceException;
import com.scanandgo.middleware.batch.product.repository.VtexRepository;
import com.scanandgo.middleware.batch.product.service.VtexService;

@Service
public class VtexServiceImpl implements VtexService{

	@Autowired
	VtexRepository repo;

	@Override
	public Product searchProducts(String ean) throws ProductServiceException {
		return repo.searchProduct(ean);
	}
	
}
