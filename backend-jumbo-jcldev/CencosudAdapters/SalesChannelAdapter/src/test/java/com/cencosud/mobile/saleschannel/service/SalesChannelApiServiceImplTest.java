package com.cencosud.mobile.saleschannel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.mobile.saleschannel.dto.RegionListDto;
import com.cencosud.mobile.saleschannel.dto.SalesChannelListDto;
import com.cencosud.mobile.saleschannel.exception.NotFoundException;
import com.cencosud.mobile.saleschannel.model.Region;
import com.cencosud.mobile.saleschannel.model.SalesChannel;
import com.cencosud.mobile.saleschannel.service.impl.SalesChannelApiServiceImpl;
import com.cencosud.mobile.saleschannel.util.RequestProtocolEnum;
import com.cencosud.mobile.saleschannel.util.RestServiceResponse;
import com.cencosud.mobile.saleschannel.util.RestServiceUtil;

@RunWith(MockitoJUnitRunner.class)
public class SalesChannelApiServiceImplTest {

	private static final Logger LOG = LoggerFactory.getLogger(SalesChannelApiServiceImplTest.class);

	@InjectMocks
	private SalesChannelApiServiceImpl channelApiServiceImpl;

	@Mock
	private RestServiceUtil restServiceUtil;

	private static final String SALES_CHANNEL_LIST = "/saleschannel/r2/v1/salesChannels";
	private static final String REGION_LIST = "/saleschannel/r2/v1/regions";

	@Test
	public void getRegions() throws NotFoundException {
		getRegionPreConditions();

		RegionListDto regionListDto = channelApiServiceImpl.getRegions();
		LOG.info("regionListDto : {}", regionListDto);
		List<Region> regions = regionListDto.getRegions();
		Assert.assertThat(regions, Matchers.notNullValue());

		Region firstRegion = regions.get(0);
		LOG.info("region : {}", firstRegion);

		Assert.assertThat(firstRegion.getName(), Matchers.is("Regi�n metropolitana"));

	}

	private void getRegionPreConditions() throws NotFoundException {
		SalesChannelListDto salesChannelListDto = new SalesChannelListDto();
		salesChannelListDto.setSalesChannels(createSalesChannelList());
		RestServiceResponse<SalesChannelListDto> responseSalesChannel = new RestServiceResponse<>(200,
				new HashMap<String, Object>());
		responseSalesChannel = responseSalesChannel.setResponse(salesChannelListDto);

		RegionListDto regionListDto = new RegionListDto();
		regionListDto.setRegions(createRegionList());
		RestServiceResponse<RegionListDto> responseRegion = new RestServiceResponse<>(200,
				new HashMap<String, Object>());
		responseRegion = responseRegion.setResponse(regionListDto);
		Mockito.doReturn(responseSalesChannel).when(restServiceUtil).executeService(SALES_CHANNEL_LIST, null,
				SalesChannelListDto.class, null, RequestProtocolEnum.GET);
		Mockito.doReturn(responseRegion).when(restServiceUtil).executeService(REGION_LIST, null, RegionListDto.class,
				null, RequestProtocolEnum.GET);
	}

	private List<Region> createRegionList() {
		List<Region> regions = new ArrayList<>();
		Region region = new Region();
		region.setName("Regi�n metropolitana");

		SalesChannel salesChannel = new SalesChannel();
		salesChannel.setId(1);
		List<SalesChannel> salesChannels = new ArrayList<>();
		salesChannels.add(salesChannel);
		region.setSalesChannels(salesChannels);
		regions.add(region);
		return regions;
	}

	private List<SalesChannel> createSalesChannelList() {
		List<SalesChannel> salesChannels = new ArrayList<>();

		SalesChannel salesChannel = new SalesChannel();
		salesChannel.setId(1);
		salesChannel.setName("La Florida");
		salesChannel.setAddress("Av la florida");

		salesChannels.add(salesChannel);
		return salesChannels;
	}
}
