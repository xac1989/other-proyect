package com.cencosud.middleware.saleschannel.service.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.middleware.saleschannel.configuration.ApplicationConfig;
import com.cencosud.middleware.saleschannel.configuration.ApplicationConfig.SalesChannelInfo;
import com.cencosud.middleware.saleschannel.model.SalesChannel;
import com.cencosud.middleware.saleschannel.model.StoresInfo;
import com.cencosud.middleware.saleschannel.repository.SalesChannelRepository;

@RunWith(MockitoJUnitRunner.class)
public class SalesChannelServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SalesChannelServiceTest.class);
	
	@InjectMocks
	private SalesChannelServiceImpl salesChannelService;

	@Mock
	private SalesChannelRepository salesChannelRepository;

	@Mock
	private ApplicationConfig applicationConfig;

	private Map<Integer, SalesChannelInfo> salesChannelInfoMap;
	private List<SalesChannel> salesChannelList;

	@Before
	public void setUp() {
		salesChannelInfoMap = createSalesChannelInfoMap();
		salesChannelList = createSalesChannelList();
	}

	@Test
	public void getSalesChannels() {
		getSalesChannelsPreConditions();

		List<SalesChannel> response = salesChannelService.getSalesChannels();
		verify(salesChannelRepository, times(1)).getSalesChannels();
		Assert.assertThat(response, Matchers.notNullValue());
		Assert.assertThat(response.get(0).getAddress(), Matchers.equalTo("AV. ARGENTINA 805"));
		Assert.assertThat(response.get(0).getName(), Matchers.equalTo("Los Andes"));

		SalesChannel salesChannel = new SalesChannel(1, "Los Andes");
		salesChannel.setAddress("AV. ARGENTINA 805");
		Assert.assertThat(response.get(0), Matchers.equalTo(salesChannel));
		LOGGER.info("Primer SalesChannel : " + response.get(0));
	}

	@Test
	public void getStoresInfo() {
		doReturn(new StoresInfo()).when(salesChannelRepository).getStoresInfo();
		StoresInfo storesInfo = salesChannelService.getStoresInfo();
		Assert.assertThat(storesInfo, org.hamcrest.Matchers.notNullValue());
	}

	@Test
	public void getRegion() {
		String region = salesChannelService.getRegionId();
		Assert.assertThat(region, Matchers.equalTo("r2"));
	}

	private void getSalesChannelsPreConditions() {
		doReturn(salesChannelInfoMap).when(applicationConfig).getSalesChannelInfo();
		when(salesChannelRepository.getSalesChannels()).thenReturn(salesChannelList);
	}

	private List<SalesChannel> createSalesChannelList() {
		SalesChannel salesChannel1 = new SalesChannel(1, "Los Andes");
		salesChannel1.setAddress("AV. ARGENTINA 805");
		SalesChannel salesChannel2 = new SalesChannel(2, "Chillan");
		salesChannel2.setAddress("LONGITUDINAL NORTE 134");
		SalesChannel salesChannel3 = new SalesChannel(3, "Talca el Arenal");
		salesChannel3.setAddress("EL ARENAL 411");
		List<SalesChannel> salesChannelList = new ArrayList<>();
		salesChannelList.add(salesChannel1);
		salesChannelList.add(salesChannel2);
		salesChannelList.add(salesChannel3);
		return salesChannelList;
	}

	private Map<Integer, SalesChannelInfo> createSalesChannelInfoMap() {
		Map<Integer, SalesChannelInfo> salesChannelInfoMap = new HashMap<>();
		SalesChannelInfo salesChannelInfo1 = new SalesChannelInfo();
		salesChannelInfo1.setAddress("AV. ARGENTINA 805");
		salesChannelInfo1.setId(1);
		salesChannelInfoMap.put(1, salesChannelInfo1);

		SalesChannelInfo salesChannelInfo2 = new SalesChannelInfo();
		salesChannelInfo2.setAddress("LONGITUDINAL NORTE 134");
		salesChannelInfo2.setId(2);
		salesChannelInfoMap.put(2, salesChannelInfo2);

		return salesChannelInfoMap;
	}
}
