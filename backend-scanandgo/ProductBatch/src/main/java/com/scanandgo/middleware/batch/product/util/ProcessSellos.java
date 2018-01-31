package com.scanandgo.middleware.batch.product.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.middleware.product.model.Product;
import com.scanandgo.middleware.batch.product.service.ProductMongoService;
import com.scanandgo.middleware.batch.product.service.VtexService;

@Component
public class ProcessSellos {

	@Autowired
	VtexService service;

	@Autowired
	ProductMongoService mongoService;

	public void process(String content) throws Exception {
		String[] columnas = content.split(",", -1);
		String ean = columnas[2];
		String azucar = columnas[4];
		String calorias = columnas[5];
		String sodio = columnas[6];
		String grasas = columnas[7];
		
		//si no es la primer linea, la proceso
		if (! ean.contains("EAN") && ean != null && ean != "" ) {
			Product producto = null;
			producto = mongoService.findByEAN(ean);
			if (producto != null) {
				if (azucar.equals("X")){
					producto.setSelloAzucares(Boolean.TRUE);
				}else{
					producto.setSelloAzucares(Boolean.FALSE);
				}
				if (calorias.equals("X")){
					producto.setSelloCaloricas(Boolean.TRUE);
				}else{
					producto.setSelloCaloricas(Boolean.FALSE);
				}
				if (sodio.equals("X")){
					producto.setSelloSodio(Boolean.TRUE);
				}else{
					producto.setSelloSodio(Boolean.FALSE);
				}
				if (grasas.equals("X")){
					producto.setSelloGrasas(Boolean.TRUE);
				}else{
					producto.setSelloGrasas(Boolean.FALSE);
				}

				mongoService.saveOrUpdate(producto);
				System.out.println("##### Se actiualizo sello para EAN : "+ean);
				System.out.println(producto);
			} else {
				System.out.println("----- ERROR , producto no encontrado EAN : "+ean);
			}
		}
	}
}
