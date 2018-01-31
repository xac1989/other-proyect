package com.cencosud.mobile.config;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.mobile.saleschannel.dto.RegionListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class AppProdConfig {
	
	
	private static RegionListDto instance = null;
	static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	private AppProdConfig() {
	}

	public static RegionListDto getInstanceRegionListDto(){
	    if(instance == null){
	        synchronized (AppConfig.class) {
	            if(instance == null){
	            	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        		try {
	        			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	        			InputStream is = classloader.getResourceAsStream("application-stores.yml");
	        			instance = mapper.readValue(is, RegionListDto.class);
	        			logger.info("Archivo con tabla de configuracón:\n{}", instance.toString());
	        		} catch (Exception e) {
	        			logger.info("Error al procesar archivo\n", e);
	        		}
	            }
	        }
	    }
	    return instance;
	}

}
