package com.cencosud.middleware.saleschannel.model;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommuneTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommuneTest.class);

	private Commune commune = new Commune();

	@Test
	public void getName() {
		String name = "I Región";
		commune.setName(name);
		Assert.assertThat(commune.getName(), Matchers.equalTo(name));
	}

	@Test
	public void getSalesChannel() {
		String salesChannel = "1";
		commune.setSalesChannel(salesChannel);
		Assert.assertThat(commune.getSalesChannel(), Matchers.equalTo(salesChannel));
	}

	@Test
	public void getGeoCoordinates() {
		List<Double> geoCoordinates = new ArrayList<>();
		geoCoordinates.add(-33.509946);
		geoCoordinates.add(-70.607891);

		commune.setGeoCoordinates(geoCoordinates);
		Assert.assertThat(commune.getGeoCoordinates().get(0), Matchers.equalTo(-33.509946));
		Assert.assertThat(commune.getGeoCoordinates().get(1), Matchers.equalTo(-70.607891));

		LOGGER.info("commune : " + commune.toString());
	}
}
