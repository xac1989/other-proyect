package com.cencosud.mobile.service;

import com.cencosud.mobile.delivery.model.DeliveryRequest;
import com.cencosud.mobile.exceptions.NotFoundException;

public interface DeliveryApiService {

	String sendDeliveryMode(String email, String region, DeliveryRequest deliveryRequest)
			throws NotFoundException;

}
