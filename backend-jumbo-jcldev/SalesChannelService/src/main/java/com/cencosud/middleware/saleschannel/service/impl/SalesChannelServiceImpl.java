package com.cencosud.middleware.saleschannel.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.saleschannel.configuration.ApplicationConfig;
import com.cencosud.middleware.saleschannel.configuration.ApplicationConfig.SalesChannelInfo;
import com.cencosud.middleware.saleschannel.model.SalesChannel;
import com.cencosud.middleware.saleschannel.model.StoresInfo;
import com.cencosud.middleware.saleschannel.repository.SalesChannelRepository;
import com.cencosud.middleware.saleschannel.service.SalesChannelService;

@Service
public class SalesChannelServiceImpl implements SalesChannelService {

	// Region2 = JUMBO CHILE
	private static final String REGION_ID = "r2";
	
	@Autowired
	private SalesChannelRepository salesChannelRepository;
	
	@Autowired
	private ApplicationConfig config;
		
	@Override
	public String getRegionId() {
		return REGION_ID;
	}

	@Override
	public List<SalesChannel> getSalesChannels() {
		List<SalesChannel> salesChannelList = salesChannelRepository.getSalesChannels();
		salesChannelList.stream().forEach(salesChannel-> salesChannel.setName(salesChannel.getName().replaceAll("Jumbo ", "")));
		salesChannelList.stream().forEach(salesChannel -> salesChannel.setAddress(getAddress(salesChannel.getId())));
		return salesChannelList;
	}

	@Override
	public StoresInfo getStoresInfo() {
		return salesChannelRepository.getStoresInfo();
	}
	
	private String getAddress(Integer salesChannelId){
		SalesChannelInfo salesChannelInfo = config.getSalesChannelInfo().get(salesChannelId);
		if(salesChannelInfo != null){
			return salesChannelInfo.getAddress();
		}
		return StringUtils.EMPTY;
	}
}
