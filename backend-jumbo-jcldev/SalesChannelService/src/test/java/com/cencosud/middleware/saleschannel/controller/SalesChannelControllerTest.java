package com.cencosud.middleware.saleschannel.controller;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.cencosud.middleware.saleschannel.dto.RegionDto;
import com.cencosud.middleware.saleschannel.factory.ShoppingListServiceFactory;
import com.cencosud.middleware.saleschannel.model.StoresInfo;
import com.cencosud.middleware.saleschannel.service.SalesChannelService;

@RunWith(MockitoJUnitRunner.class)
public class SalesChannelControllerTest {

	private static Logger logger = LoggerFactory.getLogger(SalesChannelControllerTest.class);

	@InjectMocks
	SalesChannelController salesChannelController;

	@Mock
	ShoppingListServiceFactory serviceFactory;

	@Mock
	SalesChannelService salesChannelService;

	private StoresInfo storesInfo;

	@Before
	public void setUp() {
		storesInfo = createStoresInfo();
	}

	@Test
	public void getStoresInfo() {
		getStoresInfoPreConditions();

		ResponseEntity<List<RegionDto>> responseEntity = salesChannelController.getStoresInfo("r2");
		List<RegionDto> RegionDtoList = responseEntity.getBody();
		Assert.assertThat(RegionDtoList.get(0).getName(), Matchers.equalTo("REGIÓN METROPOLITANA"));
		Assert.assertThat(RegionDtoList.get(2).getName(), Matchers.equalTo("II REGIÓN"));
		Assert.assertThat(RegionDtoList.get(6).getName(), Matchers.equalTo("XIV REGIÓN"));
		Assert.assertThat(RegionDtoList.size(), Matchers.equalTo(7));
	}

	private void getStoresInfoPreConditions() {
		Mockito.doReturn(salesChannelService).when(serviceFactory).getService("r2");
		Mockito.doReturn(storesInfo).when(salesChannelService).getStoresInfo();
	}

	private StoresInfo createStoresInfo() {
		StoresInfo storesInfo = new StoresInfo();
		String data = "{\r\n\t\"REGIÓN METROPOLITANA\": [\r\n\t\t{ \"name\": \"La Florida\", \"salesChannel\": \"18\", \"geoCoordinates\" : [-33.509946, -70.607891]},\r\n\t\t{ \"name\": \"Ñuñoa\", \"salesChannel\": \"18\", \"geoCoordinates\" : [-33.464881, -70.598039]},\r\n\t\t{ \"name\": \"Providencia\", \"salesChannel\": \"18\", \"geoCoordinates\" : [-33.417698, -70.606813]}\r\n\t],\r\n\t\"I REGIÓN\" : [\r\n\t\t{ \"name\": \"Iquique\", \"salesChannel\": \"8\", \"geoCoordinates\" : [-20.238715, -70.145018] }\r\n\t],\r\n\t\"II REGIÓN\" : [\r\n\t\t{ \"name\": \"Antofagasta\", \"salesChannel\" : \"6\", \"geoCoordinates\" : [-23.667583, -70.404996] },\r\n\t\t{ \"name\": \"Calama\", \"salesChannel\" : \"7\", \"geoCoordinates\" : [-22.454892, -68.924651] },\r\n\t\t{ \"name\": \"San Pedro De Atacama\", \"salesChannel\": \"8\", \"geoCoordinates\" : []}\r\n\t],\r\n\t\"V REGIÓN\" : [\r\n\t\t{ \"name\": \"Los Andes\", \"salesChannel\": \"1\", \"geoCoordinates\" : [-32.827435, -70.602697] },\r\n\t\t{ \"name\": \"San Felipe\", \"salesChannel\": \"1\", \"geoCoordinates\" : [-32.750385, -70.707614] }\r\n\t],\r\n\t\"VII REGIÓN\" : [\r\n\t\t{ \"name\": \"Talca\", \"salesChannel\": \"3\", \"geoCoordinates\" : [-35.4291009,-71.6729319] },\r\n\t\t{ \"name\": \"Curicó\", \"salesChannel\": \"4\", \"geoCoordinates\" : [-34.993513, -71.244964] },\r\n\t\t{ \"name\": \"Maule\", \"salesChannel\": \"3\", \"geoCoordinates\" : []},\r\n\t\t{ \"name\": \"Pencahue\", \"salesChannel\": \"3\", \"geoCoordinates\" : []},\r\n\t\t{ \"name\": \"San Clemente\", \"salesChannel\": \"3\", \"geoCoordinates\" : []},\r\n\t\t{ \"name\": \"San Javier\", \"salesChannel\": \"3\", \"geoCoordinates\" : []},\r\n\t\t{ \"name\": \"San Rafael\", \"salesChannel\": \"3\", \"geoCoordinates\" : []}\r\n\t],\r\n\t\"VIII REGIÓN\" : [\r\n\t\t{ \"name\": \"Chillán\", \"salesChannel\": \"2\", \"geoCoordinates\" : [-36.595209, -72.104725] }\r\n\t],\r\n\t\"XIV REGIÓN\" : [\r\n\t\t{ \"name\": \"Valdivia\", \"salesChannel\": \"5\", \"geoCoordinates\" : [-36.595209, -72.104725] }\r\n\t]\r\n}";
		try {
			storesInfo.setData(data);
		} catch (Exception e) {
			logger.error("Error getting store Info : ", e);
		}
		return storesInfo;
	}
}
