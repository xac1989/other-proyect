package com.cencosud.mobile.delivery.adapter;

import java.util.logging.Logger;

import javax.ws.rs.core.Context;

import com.ibm.mfp.adapter.api.ConfigurationAPI;
import com.ibm.mfp.adapter.api.MFPJAXRSApplication;

public class DeliveryAdapter extends MFPJAXRSApplication{

	static Logger logger = Logger.getLogger(DeliveryAdapter.class.getName());
	public String serviceProfileUrl;
	
	@Context
    ConfigurationAPI configurationAPI;

	protected void init() throws Exception {
		serviceProfileUrl = configurationAPI.getPropertyValue("service_profile_url");
		logger.info("Adapter initialized!");
	}
	

	protected void destroy() throws Exception {
		logger.info("Adapter destroyed!");
	}
	

	protected String getPackageToScan() {
		//The package of this class will be scanned (recursively) to find JAX-RS resources. 
		//It is also possible to override "getPackagesToScan" method in order to return more than one package for scanning
		return getClass().getPackage().getName();
	}
}
