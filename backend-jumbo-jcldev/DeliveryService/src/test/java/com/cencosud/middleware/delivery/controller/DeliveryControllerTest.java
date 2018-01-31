package com.cencosud.middleware.delivery.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cencosud.middleware.delivery.factory.DeliveryServiceFactory;
import com.cencosud.middleware.delivery.model.DeliveryDto;
import com.cencosud.middleware.delivery.model.DeliveryMode;
import com.cencosud.middleware.delivery.model.DeliveryType;
import com.cencosud.middleware.delivery.model.GeoCoordinate;
import com.cencosud.middleware.delivery.repository.DeliveryRepository;
import com.cencosud.middleware.delivery.service.impl.JumboDeliveryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryControllerTest {

	@InjectMocks
	DeliveryController deliveryController;

	@Mock
	private DeliveryServiceFactory serviceFactory;

	@Mock
	JumboDeliveryServiceImpl deliveryService;

	@Mock
	DeliveryRepository deliveryRepository;

	private String region = "r2";
	private String profileId = "anyela.herrera@globant.com";
	private DeliveryDto deliveryHome;
	private DeliveryDto deliveryJumbo;
	private DeliveryMode deliveryModeHome;
	private DeliveryMode deliveryModeJumbo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		deliveryHome = createDeliveryHome();
		deliveryModeHome = createDeliveryModeHome();
		deliveryJumbo = createDeliveryJumbo();
		deliveryModeJumbo = createDeliveryModeJumbo();
	}

	@Test
	public void sendDeliveryModeNull() {
		when(deliveryRepository.sendDeliveryMode(profileId, deliveryHome)).thenReturn(deliveryModeHome);
		when(deliveryService.sendDeliveryMode(profileId, deliveryHome)).thenReturn(deliveryModeHome);
		when(serviceFactory.getService(region)).thenReturn(deliveryService);

		ResponseEntity<DeliveryMode> response = deliveryController.sendDeliveryMode(region, profileId, null);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

	}

	@Test
	public void sendDeliveryModeHome() {
		when(deliveryRepository.sendDeliveryMode(profileId, deliveryHome)).thenReturn(deliveryModeHome);
		when(deliveryService.sendDeliveryMode(profileId, deliveryHome)).thenReturn(deliveryModeHome);
		when(serviceFactory.getService(region)).thenReturn(deliveryService);

		ResponseEntity<DeliveryMode> response = deliveryController.sendDeliveryMode(region, profileId, deliveryHome);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	public void sendDeliveryModeJumbo() {
		when(deliveryRepository.sendDeliveryMode(profileId, deliveryJumbo)).thenReturn(deliveryModeJumbo);
		when(deliveryService.sendDeliveryMode(profileId, deliveryJumbo)).thenReturn(deliveryModeJumbo);
		when(serviceFactory.getService(region)).thenReturn(deliveryService);

		ResponseEntity<DeliveryMode> response = deliveryController.sendDeliveryMode(region, profileId, deliveryJumbo);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	//
	// METODOS PARA CREAR AMBOS FLUJOS ENVIO DOMICILIO Y ENVIO A LA TIENDA
	//
	private DeliveryDto createDeliveryHome() {
		DeliveryDto deliveryDto = new DeliveryDto();
		GeoCoordinate geo = new GeoCoordinate(new BigDecimal(-70.57427530000001), new BigDecimal(-33.4056776));
		deliveryDto.setGeoCoordinate(geo);
		deliveryDto.setPostalCode("2600");
		deliveryDto.setShippingModeId(1);
		return deliveryDto;
	}

	private DeliveryMode createDeliveryModeHome() {
		List<DeliveryType> types = new ArrayList<>();
		DeliveryType type = new DeliveryType(111, "Llevamos tus compras a tu casa", true);
		types.add(type);
		type = new DeliveryType(112, "Enviamos tus compras en 90 minutos", false);
		types.add(type);
		return new DeliveryMode(1, types);
	}

	private DeliveryDto createDeliveryJumbo() {
		DeliveryDto deliveryDto = new DeliveryDto();
		deliveryDto.setSalesChannel(5);
		deliveryDto.setShippingModeId(2);
		return deliveryDto;
	}

	private DeliveryMode createDeliveryModeJumbo() {
		List<DeliveryType> types = new ArrayList<>();
		types = new ArrayList<>();
		DeliveryType type = new DeliveryType(113, "Retira tus compras en Jumbo", true);
		types.add(type);
		type = new DeliveryType(114, "Enviamos tus compras en 90 minutos", false);
		types.add(type);
		return new DeliveryMode(1, types);
	}

}
