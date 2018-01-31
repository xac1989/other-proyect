package com.cencosud.middleware.delivery.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.delivery.model.DeliveryDto;
import com.cencosud.middleware.delivery.model.DeliveryMode;
import com.cencosud.middleware.delivery.repository.DeliveryRepository;
import com.cencosud.middleware.delivery.service.DeliveryService;

@Service
public class JumboDeliveryServiceImpl implements DeliveryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JumboDeliveryServiceImpl.class);

	// Region2 = JUMBO CHILE
	private static final String REGION_ID = "r2";

	@Autowired
	private DeliveryRepository repo;

	@Override
	public String getRegionId() {
		return REGION_ID;
	}

	@Override
	public DeliveryMode sendDeliveryMode(String profileId, DeliveryDto deliveryDto) {
		LOGGER.info("JumboDeliveryServiceImpl (sendDeliveryMode)");
		DeliveryMode deliveryMode = new DeliveryMode();
		
		deliveryMode = repo.sendDeliveryMode(profileId, deliveryDto);
		
		return deliveryMode;
	}

}
