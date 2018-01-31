package com.cencosud.middleware.product.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cencosud.middleware.product.configuration.ApplicationConfig;
import com.cencosud.middleware.product.configuration.CloudConfig;
import com.cencosud.middleware.product.configuration.WebApplication;
import com.cencosud.middleware.product.service.ProductMongoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WebApplication.class, ApplicationConfig.class, CloudConfig.class}, properties = "spring.cloud.config.enabled:false")
public class ProductMongoServiceImplTestSpring {

	@Autowired
	private ProductMongoService service;

	@Test
	public void inittest() {
		System.out.println(service.findByEANandStoreId("7802220071807", "1502"));
	}
}
