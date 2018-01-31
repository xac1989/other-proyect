package com.cencosud.middleware.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.product.model.Product;
import com.cencosud.middleware.product.repository.ProductRepository;
import com.cencosud.middleware.product.service.ProductMongoService;

@Service
public class ProductMongoServiceImpl implements ProductMongoService {

	@Autowired
	private ProductRepository repository;

	@Override
	public Product saveOrUpdate(Product product) {
		Product productTmp = this.findByEANandStoreId(product.getEan(),null);
		if (productTmp != null) {
			productTmp.setProductName(product.getProductName());
			return repository.save(productTmp);
		} else {
			return repository.save(product);
		}
	}

	@Override
	public List<Product> saveOrUpdate(List<Product> products) {
		return repository.save(products);
	}

	@Override
	public Product update(Product product) {
		Product productTemp = null;
		productTemp = this.findByEANandStoreId(product.getEan(), null);

		if (productTemp != null) {

			if (product.getProductName() != null && !product.getProductName().isEmpty()) {
				productTemp.setProductName(product.getProductName());
			}
			repository.save(productTemp);
		}
		return productTemp;
	}

	@Override
	public void delete(Integer productId) {
		repository.delete(productId.toString());

	}

	@Override
	public Product findByEANandStoreId(String ean, String storeId) {
		List<Product> lstProduct = null;
		if(ean.length()<12){
			return null;
		}
		if(storeId == null || storeId.equals(""))
			lstProduct = repository.findByEAN(ean.concat("/*"));
		else 
			lstProduct = repository.findByEANandStoreId(ean.concat("/*"), storeId);
		if(lstProduct != null && lstProduct.size()>0)
			return lstProduct.get(0);
		return null;
	}

}
