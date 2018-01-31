package com.scanandgo.middleware.batch.product.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.middleware.product.model.Product;
import com.cencosud.middleware.product.model.Store;
import com.scanandgo.middleware.batch.product.exception.ProductServiceException;
import com.scanandgo.middleware.batch.product.service.ProductMongoService;
import com.scanandgo.middleware.batch.product.service.VtexService;
import com.scanandgo.middleware.batch.product.service.impl.SftpServiceImpl;

@Component
public class Processor {

	@Autowired
	VtexService service;

	@Autowired
	ProductMongoService mongoService;
	
	private static final Logger logger = LoggerFactory.getLogger(SftpServiceImpl.class);
	
	public void process(List<String> content) throws Exception {
		String storeId = "";
		String lineaInicial = "";
		String lineaY = "";
		String lineaZ = "";
		for (String linea : content) {
			if (linea.substring(0, 5).contains("store")) {
				storeId = linea.substring(5, 9);
			}
			if (linea.substring(1, 2).trim().isEmpty() && !linea.contains("@@@")) {
				lineaInicial = linea;
			}
			if (linea.substring(1, 2).contains("y")) {
				lineaY = linea;
			}
			if (linea.substring(1, 2).contains("z")) {
				lineaZ = linea;
			}
		}
		
		String ean = lineaInicial.substring(3, 16);
		String um = lineaInicial.substring(26, 28);
		String name = lineaInicial.substring(36, 56);
		String price = lineaInicial.substring(70, 78);
		String pesable = lineaInicial.substring(21, 22);
		String desc = lineaY.substring(16, 66);
		String pum = lineaZ.substring(16, 34);

		Store store = new Store();
		if (pum != null || pum != "") {
			store.setPum(new BigDecimal(pum));
		}
		if (price != null || price != "") {
			store.setPrice(new BigDecimal(price));
			//si el pum quedo seteado en cero, ponemos el precio
			if(store.getPum().equals(new BigDecimal(0))){
				store.setPum(store.getPrice());
			}
		}
		store.setUm(um);
		store.setStoreId(storeId);
		store.setLastUpdate(new Date());
		Product producto = null;
		//obtengo el prod de la BD
		producto = mongoService.findByEAN(ean);
		if (producto != null) {
			//si esta -> hay que actualizar el STORE, asi que lo elimino si ya existia
			producto.removeStore(store);
		} else {
			//no esta -> se crea uno nuevo, con datos de Vtex si existen o la venga por .dat
			try {
				//busco el producto en VTEX
				producto = service.searchProducts(ean);
			} catch (ProductServiceException e) {
				e.printStackTrace();
			}
			if (producto == null || producto.getProductId() == null) {
				producto = new Product();
				producto.setEan(ean);
				producto.setNameComplete(name);
				producto.setProductDescription(desc);
			}
			producto.setSelloAzucares(Boolean.FALSE);
			producto.setSelloCaloricas(Boolean.FALSE);
			producto.setSelloSodio(Boolean.FALSE);
			producto.setSelloGrasas(Boolean.FALSE);
			if(pesable.equals("1")){
				producto.setPesable(Boolean.TRUE);
			}else{
				producto.setPesable(Boolean.FALSE);
			}
		}
		producto.addStore(store);

		logger.info("####Procesor Step: Proceso bien la lista entera");

		mongoService.saveOrUpdate(producto);
		logger.info("####Writer Step: Escribio bien la lista entera");
	}

}