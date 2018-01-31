package com.cencosud.middleware.rewards.client.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.cencosud.middleware.rewards.annotation.Loggable;
import com.cencosud.middleware.rewards.client.RewardsClient;
import com.cencosud.middleware.rewards.client.wsdl.WSCDCenExecute;
import com.cencosud.middleware.rewards.client.wsdl.WSCDCenExecuteResponse;
import com.cencosud.middleware.rewards.configuration.ApplicationConfig;

@Component
public class RewardsClientImpl implements RewardsClient {
	
	@Autowired
	private ApplicationConfig applicationConfig;
	
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	private static final Logger log = LoggerFactory.getLogger(RewardsClientImpl.class);
	private static final String DATE_FRT = "yyyyMMdd";
	private static final String TIME_FRT = "HHmmss";
	private static final String POS_ID = "1";
	private static final String COMERCIO = "101001";
	
	@Loggable
	@Override
	public WSCDCenExecuteResponse getRewards(Integer docTypeCode, String documentNumber) {
		Date now = new Date();
		try {
			WSCDCenExecute request = new WSCDCenExecute();
			request.setComercio(COMERCIO);
			request.setFechatransaccion(new SimpleDateFormat(DATE_FRT).format(now));
			request.setHoratransaccion(new SimpleDateFormat(TIME_FRT).format(now));
			request.setPosId(POS_ID);
			request.setPrsnrodoc(documentNumber);
			request.setTipdoccod((short)docTypeCode.intValue());
			
			String rewardsService = applicationConfig.getRewards().getUrl();
			log.info("Requesting rewards for {} with docType: {} and docNumber: {}" , rewardsService, request.getTipdoccod(), request.getPrsnrodoc());

			WSCDCenExecuteResponse response = (WSCDCenExecuteResponse) webServiceTemplate.marshalSendAndReceive(request);

			log.info("Message from Server: {}",response.getOMensajeerror());
			return response;
		} catch (Exception e) {
			log.error("Error  llamando al servicio web", e);
			return new WSCDCenExecuteResponse();
		}
	}

}
