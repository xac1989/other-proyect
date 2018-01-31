package com.cencosud.mobile.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.profile.model.Address;
import com.cencosud.mobile.profile.model.AddressResponse;
import com.cencosud.mobile.service.AddressApiService;
import com.cencosud.mobile.util.RequestProtocolEnum;
import com.cencosud.mobile.util.RestServiceUtil;

@Service
public class AddressApiServiceImpl implements AddressApiService {
    
	private static final String ADDRESSES = "/address/r2/v1/addresses";
	static final Logger LOGGER = LoggerFactory.getLogger(AddressApiServiceImpl.class);
	
	private RestServiceUtil restServiceUtil;
	
	
	/**
	 * @return the restServiceUtil
	 */
	public RestServiceUtil getRestServiceUtil() {
		return restServiceUtil;
	}

	/**
	 * @param restServiceUtil the restServiceUtil to set
	 */
	public void setRestServiceUtil(RestServiceUtil restServiceUtil) {
		this.restServiceUtil = restServiceUtil;
	}

	
	@Override
	public String getUserAddresses(String email, String region) throws NotFoundException {
		
		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("profileId", email);
		LOGGER.info("Api service, email: {}", email);
		return restServiceUtil.executeService(ADDRESSES, null, String.class, queryParam, RequestProtocolEnum.GET);
		
	}

	@Override
	public String deleteUserAddress(String email, String addressId, String region) throws NotFoundException {
		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("profileId", email);
		queryParam.put("addressId", addressId);
		LOGGER.info("Api service, addressId: {}", addressId);
		LOGGER.info("Api service, email: {}", email);
		return restServiceUtil.executeService(ADDRESSES, null, String.class, queryParam, RequestProtocolEnum.DELETE);
	}

	@Override
	public AddressResponse createUserAddress(String email, String region, Address address) throws NotFoundException {
		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("profileId", email);
		LOGGER.info("Api service, addressId: {}", address);
		LOGGER.info("Api service, email: {}", email);
		return restServiceUtil.executeService(ADDRESSES, address, AddressResponse.class, queryParam, RequestProtocolEnum.POST);
	}

	@Override
	public AddressResponse editUserAddress(String email, String region, Address address) throws NotFoundException {
		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("profileId", email);
		LOGGER.info("Api service, addressId: {}", address);
		LOGGER.info("Api service, email: {}", email);
		return restServiceUtil.executeService(ADDRESSES, address, AddressResponse.class, queryParam, RequestProtocolEnum.PUT);
	}

}
