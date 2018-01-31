package com.cencosud.mobile.saleschannel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cencosud.mobile.config.AppConfig;
import com.cencosud.mobile.config.AppProdConfig;
import com.cencosud.mobile.saleschannel.dto.RegionListDto;
import com.cencosud.mobile.saleschannel.dto.SalesChannelListDto;
import com.cencosud.mobile.saleschannel.exception.NotFoundException;
import com.cencosud.mobile.saleschannel.model.DeliveryType;
import com.cencosud.mobile.saleschannel.model.Region;
import com.cencosud.mobile.saleschannel.model.SalesChannel;
import com.cencosud.mobile.saleschannel.service.SalesChannelApiService;
import com.cencosud.mobile.saleschannel.util.RequestProtocolEnum;
import com.cencosud.mobile.saleschannel.util.RestServiceResponse;
import com.cencosud.mobile.saleschannel.util.RestServiceUtil;

/**
 * 
 * 
 * <h1>SalesChannelApiServiceImpl</h1>
 * <p>
 * Implementacion de servicio de Sales Channel
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Jul 21, 2017
 */
@Service
public class SalesChannelApiServiceImpl implements SalesChannelApiService {

	@Autowired
	private RestServiceUtil restServiceUtil;
	
	@Value("${environment}")
    private String environment;
	
	static final Logger logger = LoggerFactory.getLogger(SalesChannelApiServiceImpl.class);

	private static final String SALES_CHANNEL_LIST = "/saleschannel/r2/v1/salesChannels";
	private static final String REGION_LIST = "/saleschannel/r2/v1/regions";

	@Override
	public SalesChannelListDto getSalesChannels() {
		SalesChannelListDto salesChannelResponse;
		try {
			RestServiceResponse<SalesChannelListDto> responseDto = restServiceUtil
					.executeService(SALES_CHANNEL_LIST, null, SalesChannelListDto.class, null, RequestProtocolEnum.GET);
			salesChannelResponse = responseDto.getResponse();
		} catch (NotFoundException e) {
			salesChannelResponse = new SalesChannelListDto();
		}

		return salesChannelResponse;
	}

	@Override
	public RegionListDto getRegions() {
		logger.info("environment {}", environment);
		if(environment != null && environment.equals("jumbo")) {
			return AppProdConfig.getInstanceRegionListDto();
		}
		SalesChannelListDto salesChannelResponse = getSalesChannels();
		Map<Integer, SalesChannel> salesChannelMap = new HashMap<>();
		for (SalesChannel salesChannel : salesChannelResponse.getSalesChannels()) {
			salesChannelMap.put(salesChannel.getId(), salesChannel);
		}

		AppConfig config = AppConfig.getInstanceUsingDoubleLocking();
		
		logger.info("Objeto de configuracion de SalesChannel: {}",config);
		RegionListDto regionListDto;
		try {
			regionListDto = restServiceUtil
					.executeService(REGION_LIST, null, RegionListDto.class, null, RequestProtocolEnum.GET)
					.getResponse();
		} catch (NotFoundException e) {
			regionListDto = new RegionListDto();
		}
		for (Region region : regionListDto.getRegions()) {
			for (SalesChannel salesChannel : region.getSalesChannels()) {
				List<DeliveryType> deliveryTypeList = new ArrayList<>(4);
				SalesChannel salesChannelInfo = salesChannelMap.get(salesChannel.getId());
				salesChannel.setName(salesChannelInfo.getName());
				salesChannel.setAddress(salesChannelInfo.getAddress());
				
				if(config.getSalesChannelMe().containsKey(salesChannel.getId())) {
					deliveryTypeList.addAll(config.getSalesChannelMe().get(salesChannel.getId()));
				}else {
					deliveryTypeList.addAll(config.getListDeliveryType());
				}
				salesChannel.setDeliveryType(deliveryTypeList);
			}
		}
		return regionListDto;
	}

}
