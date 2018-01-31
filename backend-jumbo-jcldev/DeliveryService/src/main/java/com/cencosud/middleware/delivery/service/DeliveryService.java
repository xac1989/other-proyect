package com.cencosud.middleware.delivery.service;

import com.cencosud.middleware.delivery.model.DeliveryDto;
import com.cencosud.middleware.delivery.model.DeliveryMode;

public interface DeliveryService {

	DeliveryMode sendDeliveryMode(String profileId, DeliveryDto deliveryDto);

	String getRegionId();
}
