package com.scanandgo.middleware.batch.product.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scanandgo.middleware.batch.product.configuration.ApplicationConfig;
import com.scanandgo.middleware.batch.product.configuration.ApplicationConfig.SelloProperties;
import com.scanandgo.middleware.batch.product.service.SelloService;
import com.scanandgo.middleware.batch.product.util.ProcessSellos;

/**
 * <h1>SelloServiceImpl</h1>
 */
@Service
public class SelloServiceImpl implements SelloService{
	
	@Autowired
	private ApplicationConfig applicationConfig;
	
	@Autowired
	ProcessSellos processSellos;

	@Override
	public void read() {
		try {
			SelloProperties selloProperties = applicationConfig.getSelloConfig();
			InputStream inputStream = new FileInputStream(selloProperties.getPathArchivo());
			List<String> lstString = IOUtils.readLines(inputStream);
			for(String line : lstString){
				processSellos.process(line);
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.read();
	}

}
