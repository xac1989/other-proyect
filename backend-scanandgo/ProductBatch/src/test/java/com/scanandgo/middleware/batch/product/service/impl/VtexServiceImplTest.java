package com.scanandgo.middleware.batch.product.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.scanandgo.middleware.batch.product.configuration.WebApplication;
import com.scanandgo.middleware.batch.product.exception.ProductServiceException;
import com.scanandgo.middleware.batch.product.service.VtexService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebApplication.class })
public class VtexServiceImplTest {
	
	@Autowired
	VtexService service;
	
	@Test
	public void searchProductsImpl() throws ProductServiceException{
		service.searchProducts("7802220071807");
		
	}
}
