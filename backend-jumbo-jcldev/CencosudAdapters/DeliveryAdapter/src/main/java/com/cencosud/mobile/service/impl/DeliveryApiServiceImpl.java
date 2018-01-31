package com.cencosud.mobile.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.cencosud.mobile.delivery.model.DeliveryRequest;
import com.cencosud.mobile.enums.RequestProtocolEnum;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.DeliveryApiService;
import com.cencosud.mobile.util.RestServiceUtil;

@Service
public class DeliveryApiServiceImpl implements DeliveryApiService {

	static Logger logger = Logger.getLogger(DeliveryApiServiceImpl.class.getName());
	private static final String DELIVERY_MODE = "/delivery/r2/v1/delivery";

	private RestServiceUtil restServiceUtil;

	/**
	 * @return the restServiceUtil
	 */
	public RestServiceUtil getRestServiceUtil() {
		return restServiceUtil;
	}

	/**
	 * @param restServiceUtil
	 *            the restServiceUtil to set
	 */
	public void setRestServiceUtil(RestServiceUtil restServiceUtil) {
		this.restServiceUtil = restServiceUtil;
	}

	@Override
	public String sendDeliveryMode(String email, String region, DeliveryRequest deliveryRequest)
			throws NotFoundException {

		logger.info("DeliveryApiServiceImpl(sendDeliveryMode) call service ");

		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("profileId", email);

		return restServiceUtil.executeService(DELIVERY_MODE, deliveryRequest, String.class, queryParam,
				RequestProtocolEnum.POST);

	}

}
