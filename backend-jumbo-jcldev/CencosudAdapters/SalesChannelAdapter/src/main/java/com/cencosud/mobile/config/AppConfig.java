package com.cencosud.mobile.config;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.mobile.saleschannel.model.DeliveryType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * 
 * 
 * <h1>AppConfig</h1>
 * <p>
 * Clase con objetos preconfigurados
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Nov 15, 2017
 */
public class AppConfig {
	private Map<Integer, List<DeliveryType>> salesChannelMe;
	private List<DeliveryType> listDeliveryType;
	
	private static AppConfig instance = null;
	static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	private AppConfig() {
	}

	public static AppConfig getInstanceUsingDoubleLocking(){
	    if(instance == null){
	        synchronized (AppConfig.class) {
	            if(instance == null){
	            	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        		try {
	        			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	        			InputStream is = classloader.getResourceAsStream("application-default.yml");
	        			instance = mapper.readValue(is, AppConfig.class);
	        			logger.info("Archivo con tabla de configuracón:\n{}", instance.toString());
	        		} catch (Exception e) {
	        			logger.info("Error al procesar archivo\n", e);
	        		}
	            }
	        }
	    }
	    return instance;
	}
	
	/**
	 * @return the salesChannelMe
	 */
	public Map<Integer, List<DeliveryType>> getSalesChannelMe() {
		return salesChannelMe;
	}

	/**
	 * @param salesChannelMe the salesChannelMe to set
	 */
	public void setSalesChannelMe(Map<Integer, List<DeliveryType>> salesChannelMe) {
		this.salesChannelMe = salesChannelMe;
	}

	/**
	 * @return the listDeliveryType
	 */
	public List<DeliveryType> getListDeliveryType() {
		return listDeliveryType;
	}

	/**
	 * @param listDeliveryType the listDeliveryType to set
	 */
	public void setListDeliveryType(List<DeliveryType> listDeliveryType) {
		this.listDeliveryType = listDeliveryType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppConfig [salesChannelMe=");
		builder.append(salesChannelMe);
		builder.append(", listDeliveryType=");
		builder.append(listDeliveryType);
		builder.append("]");
		return builder.toString();
	}
		
}
