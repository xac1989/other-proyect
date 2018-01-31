package com.scanandgo.middleware.batch.product.service;

/**
 * <h1>SelloService</h1>
 * <p>Procesamiento de archivos de Sellos para completar la BD.</p>
 * 
 */
public interface SelloService extends Runnable{
	/**
	 * Inicia el proceso de lectura y procesamiento de archivos.
	 */
	void read();
}
