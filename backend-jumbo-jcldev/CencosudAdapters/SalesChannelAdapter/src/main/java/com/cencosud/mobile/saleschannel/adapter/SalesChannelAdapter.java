package com.cencosud.mobile.saleschannel.adapter;

import java.util.logging.Logger;

import javax.ws.rs.core.Context;

import com.ibm.mfp.adapter.api.ConfigurationAPI;
import com.ibm.mfp.adapter.api.MFPJAXRSApplication;

public class SalesChannelAdapter extends MFPJAXRSApplication{

	static Logger logger = Logger.getLogger(SalesChannelAdapter.class.getName());
	public String serviceListUrl;
	
	@Context
    ConfigurationAPI configurationAPI;

	protected void init() throws Exception {
		serviceListUrl = configurationAPI.getPropertyValue("service_profile_url");
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
