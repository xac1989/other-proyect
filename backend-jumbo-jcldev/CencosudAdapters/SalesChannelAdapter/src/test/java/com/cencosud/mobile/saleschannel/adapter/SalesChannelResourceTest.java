package com.cencosud.mobile.saleschannel.adapter;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.mobile.saleschannel.dto.RegionListDto;
import com.cencosud.mobile.saleschannel.model.Region;
import com.cencosud.mobile.saleschannel.model.SalesChannel;
import com.cencosud.mobile.saleschannel.service.SalesChannelApiService;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

@RunWith(MockitoJUnitRunner.class)
public class SalesChannelResourceTest {
	private static final Logger LOG = LoggerFactory.getLogger(SalesChannelResourceTest.class);

	@InjectMocks
	SalesChannelResource salesChannelResource;

	@Mock
	AdapterSecurityContext adapterSecurityContext;

	@Mock
	SalesChannelApiService salesChannelApiService;

	@Test
	public void getRegionList() {
		getRegionPreConditions();

		Response response = salesChannelResource.getRegionList(adapterSecurityContext);
		RegionListDto regionListDto = (RegionListDto) response.getEntity();
		assertThat(regionListDto, notNullValue());
		List<Region> regions = regionListDto.getRegions();
		Region region = regions.get(0);
		assertThat(region.getName(), Matchers.equalTo("REGIÓN METROPOLITANA"));
		assertThat(region.getSalesChannels(), Matchers.notNullValue());
		assertThat(region.getSalesChannels().size(), Matchers.is(1));
		LOG.info("regions : {}", regions);
		LOG.info("SalesChannel from Region 1 : {} ", region.getSalesChannels());
	}

	private void getRegionPreConditions() {
		List<Region> regionList = new ArrayList<>();
		Region region = new Region();
		region.setName("REGIÓN METROPOLITANA");

		SalesChannel salesChannel = new SalesChannel();
		salesChannel.setId(18);
		salesChannel.setName("La Florida");
		salesChannel.setAddress("AV. VICUÑA MACKENNA #6100");

		List<SalesChannel> salesChannels = new ArrayList<>();
		salesChannels.add(salesChannel);
		region.setSalesChannels(salesChannels);

		Region region2 = new Region();
		region2.setName("I REGIÓN");

		SalesChannel salesChannel_r2 = new SalesChannel(8, "Calama");
		salesChannel_r2.setAddress("AV. CHORILLOS #1759");
		region2.setSalesChannels(salesChannels);

		List<SalesChannel> salesChannels_r2 = new ArrayList<>();
		salesChannels_r2.add(salesChannel_r2);
		regionList.add(region);
		regionList.add(region2);

		RegionListDto regionListDto = new RegionListDto();
		regionListDto.setRegions(regionList);
		Mockito.doReturn(regionListDto).when(salesChannelApiService).getRegions();
	}
}
