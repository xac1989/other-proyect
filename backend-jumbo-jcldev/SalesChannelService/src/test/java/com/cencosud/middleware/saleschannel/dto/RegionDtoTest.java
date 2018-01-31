package com.cencosud.middleware.saleschannel.dto;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.middleware.saleschannel.model.SalesChannel;

public class RegionDtoTest {

	private static Logger logger = LoggerFactory.getLogger(RegionDtoTest.class);

	RegionDto regionDto = new RegionDto();

	@Test
	public void getName() {
		String name = "Región Metropolitana";
		regionDto.setName(name);
		Assert.assertThat(regionDto.getName(), Matchers.equalTo(name));
	}

	@Test
	public void getSalesChannel() {
		Set<SalesChannel> salesChannelList = new HashSet<>();
		SalesChannel salesChannel = new SalesChannel();
		salesChannel.setAddress("Av metropolitana");
		salesChannel.setId(1);
		salesChannel.setName("Región Metropolitana");
		salesChannelList.add(salesChannel);

		regionDto.setSalesChannels(salesChannelList);
		logger.info("Regions : " + regionDto);
		Assert.assertThat(regionDto.getSalesChannels(), Matchers.notNullValue());
	}
}
