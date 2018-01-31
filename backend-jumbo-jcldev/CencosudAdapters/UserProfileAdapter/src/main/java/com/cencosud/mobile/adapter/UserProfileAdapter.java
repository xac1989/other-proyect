package com.cencosud.mobile.adapter;

import java.util.logging.Logger;

import javax.ws.rs.core.Context;

import com.ibm.mfp.adapter.api.ConfigurationAPI;
import com.ibm.mfp.adapter.api.MFPJAXRSApplication;

public class UserProfileAdapter extends MFPJAXRSApplication{

	private static final Logger logger = Logger.getLogger(UserProfileAdapter.class.getName());
	public String serviceProfileUrl;

	@Context
    ConfigurationAPI configurationAPI;

	@Override
	protected void init() throws Exception {
		serviceProfileUrl = configurationAPI.getPropertyValue("service_profile_url");
		logger.info("Adapter initialized!");
	}

	@Override
	protected void destroy() throws Exception {
		logger.info("Adapter destroyed!");
	}

	@Override
	protected String getPackageToScan() {
		//The package of this class will be scanned (recursively) to find JAX-RS resources. 
		//It is also possible to override "getPackagesToScan" method in order to return more than one package for scanning
		return getClass().getPackage().getName();
	}
}
