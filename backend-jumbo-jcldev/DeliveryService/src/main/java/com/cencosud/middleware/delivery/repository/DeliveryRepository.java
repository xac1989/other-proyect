package com.cencosud.middleware.delivery.repository;

import com.cencosud.middleware.delivery.model.DeliveryDto;
import com.cencosud.middleware.delivery.model.DeliveryMode;

public interface DeliveryRepository {

	DeliveryMode sendDeliveryMode(String profileId, DeliveryDto deliveryDto);

}
