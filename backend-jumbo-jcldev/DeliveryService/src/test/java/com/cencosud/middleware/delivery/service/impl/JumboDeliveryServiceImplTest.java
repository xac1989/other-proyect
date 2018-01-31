package com.cencosud.middleware.delivery.service.impl;

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

import com.cencosud.middleware.delivery.model.DeliveryDto;
import com.cencosud.middleware.delivery.model.DeliveryMode;
import com.cencosud.middleware.delivery.model.DeliveryType;
import com.cencosud.middleware.delivery.model.GeoCoordinate;
import com.cencosud.middleware.delivery.repository.DeliveryRepository;

@RunWith(MockitoJUnitRunner.class)
public class JumboDeliveryServiceImplTest {

	@InjectMocks
	JumboDeliveryServiceImpl deliveryService;

	@Mock
	DeliveryRepository deliveryRepository;

	private DeliveryDto deliveryHome;
	private DeliveryDto deliveryJumbo;
	private DeliveryMode deliveryModeHome;
	private DeliveryMode deliveryModeJumbo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		deliveryHome = createDeliveryHome();
		deliveryModeHome = createDeliveryModeHome();
		deliveryJumbo = createDeliveryJumbo();
		deliveryModeJumbo = createDeliveryModeJumbo();
	}

	@Test
	public void getRegionId() {
		String REGION_ID = "r2";
		String regionId = deliveryService.getRegionId();

		assertThat(regionId).isEqualTo(REGION_ID);
	}

	@Test
	public void sendDeliveryModeHome() {
		String profileId = "anyela.herrera@globant.com";

		when(deliveryRepository.sendDeliveryMode(profileId, deliveryHome)).thenReturn(deliveryModeHome);

		DeliveryMode deliveryMode = deliveryService.sendDeliveryMode(profileId, deliveryHome);

		assertThat(deliveryMode).isNotNull();

	}
	
	@Test
	public void sendDeliveryModeJumbo() {
		String profileId = "anyela.herrera@globant.com";

		when(deliveryRepository.sendDeliveryMode(profileId, deliveryJumbo)).thenReturn(deliveryModeJumbo);

		DeliveryMode deliveryMode = deliveryService.sendDeliveryMode(profileId, deliveryJumbo);

		assertThat(deliveryMode).isNotNull();

	}


	//
	//METODOS PARA CREAR AMBOS FLUJOS ENVIO DOMICILIO Y ENVIO A LA TIENDA
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
