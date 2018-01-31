package com.scanandgo.middleware.batch.product.service;

import java.io.InputStream;
import java.util.List;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.scanandgo.middleware.batch.product.exception.ProductServiceException;

/**
 * 
 * 
 * <h1>SftpService</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Apr 3, 2017
 */
public interface SftpService extends Runnable{
	/**
	 * Retorna una lista con los nombres de los archivos
	 * @return {@link List}<{@link String}>
	 * @throws SftpException
	 * @throws JSchException
	 * @throws ProductServiceException
	 */
	List<String> getFileNames() throws SftpException, JSchException, ProductServiceException;
	
	/**
	 * Retorna el stream del archivo solicitado
	 * @param fileName {@link String}
	 * @return {@link List}<{@link String}>
	 * @throws ProductServiceException
	 */
	InputStream getFileStreamByName(String fileName) throws ProductServiceException;
	
	/**
	 * Inicia el proceso de lectura y procesamiento de archivos.
	 */
	void read();
	
	/**
	 * Cierra las conexiones existentes.
	 */
	void closeConnection();
}
