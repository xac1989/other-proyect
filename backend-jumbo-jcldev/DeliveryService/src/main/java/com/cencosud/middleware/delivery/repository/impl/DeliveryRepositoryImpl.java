package com.cencosud.middleware.delivery.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.delivery.model.DeliveryDto;
import com.cencosud.middleware.delivery.model.DeliveryMode;
import com.cencosud.middleware.delivery.model.DeliveryType;
import com.cencosud.middleware.delivery.repository.DeliveryRepository;
import com.cencosud.middleware.profile.utils.VtexUtilClient;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {

	static final Logger LOGGER = LoggerFactory.getLogger(DeliveryRepositoryImpl.class);

	@Autowired
	@Qualifier("vTexUtilClient")
	private VtexUtilClient clientUtil;

	@Override
	public DeliveryMode sendDeliveryMode(String profileId, DeliveryDto deliveryDto) {
		DeliveryMode deliveryMode = new DeliveryMode();

		switch (deliveryDto.getShippingModeId()) {
		case 1:
			deliveryMode = getDeliveryAddress();
			break;
		case 2:
			deliveryMode = getDeliveryJumbo(deliveryDto.getSalesChannel());
			break;

		}
		return deliveryMode;

	}

	// TODO: Metodo que devuelve objeto mockeado cuando es "ENVIO A DOMICILIO"
	// shippingModeId = 1
	private DeliveryMode getDeliveryAddress() {
		List<DeliveryType> types = new ArrayList<>();
		DeliveryType type = new DeliveryType(111, "Llevamos tus compras a tu casa", true);
		types.add(type);
		type = new DeliveryType(112, "Enviamos tus compras en 90 minutos", false);
		types.add(type);

		return new DeliveryMode(1, types);
	}

	// TODO: Metodo que devuelve objeto mockeado cuando es "RETIRA EN JUMBO"
	// shippingModeId = 2
	private DeliveryMode getDeliveryJumbo(int salesChannel) {
		List<DeliveryType> types = new ArrayList<>();
		types = new ArrayList<>();
		DeliveryType type = new DeliveryType(113, "Retira tus compras en Jumbo", true);
		types.add(type);
		type = new DeliveryType(114, "Retiro tus compras con tu vehículo", false);
		types.add(type);

		return new DeliveryMode(salesChannel, types);
	}

}