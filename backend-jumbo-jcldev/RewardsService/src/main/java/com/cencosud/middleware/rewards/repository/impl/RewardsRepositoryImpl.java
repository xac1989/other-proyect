package com.cencosud.middleware.rewards.repository.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.rewards.annotation.Loggable;
import com.cencosud.middleware.rewards.client.RewardsClient;
import com.cencosud.middleware.rewards.client.wsdl.WSCDCenExecuteResponse;
import com.cencosud.middleware.rewards.model.RewardsResponse;
import com.cencosud.middleware.rewards.model.RewardsResponse.Document;

@Repository
public class RewardsRepositoryImpl implements RewardsRepository{
	
	private static final int DEFAULT_VAL = 0;
	private static final Logger log = LoggerFactory.getLogger(RewardsRepositoryImpl.class);
	private static final Byte ERR_COD_NOT_BONUS = 5;
	private static final Byte ERR_COD_INV_NUM_DIG = 4;
	private static final List<Byte> ERR_CODS = Arrays.asList(ERR_COD_NOT_BONUS, ERR_COD_INV_NUM_DIG);

	@Autowired
	private RewardsClient rewardsClient;
	
	@Loggable
	@Override
	public RewardsResponse getRewards(Integer doctype, String numdoc) {
		
		WSCDCenExecuteResponse response = rewardsClient.getRewards(doctype, numdoc);
		
		return buildBonusResponseFromWSCDExecuteResponse(response, doctype, numdoc);
	}

	private RewardsResponse buildBonusResponseFromWSCDExecuteResponse(WSCDCenExecuteResponse response, Integer docTypeCode, String documentNumber) {
		RewardsResponse bonusResponse = new RewardsResponse();
		bonusResponse.setBalancePoints(getBalancePointsFromWSCDExecuteResponse(response));
		bonusResponse.setCardNumber(response.getOTarjetapadre());
		bonusResponse.setDocument(buildDocumentFromWSCDExecuteResponse(response, docTypeCode));
		bonusResponse.setDocumentNumber(documentNumber);
		bonusResponse.setLinkedUser(builLinkedUserFromWSCDExecuteResponse(response));
		return bonusResponse;
	}

	private long getBalancePointsFromWSCDExecuteResponse(WSCDCenExecuteResponse response) {
		if("".equals(response.getOSaldopuntos()) || response.getOSaldopuntos()==null)
			return DEFAULT_VAL;
		return Long.parseLong(response.getOSaldopuntos());
	}

	private Boolean builLinkedUserFromWSCDExecuteResponse(WSCDCenExecuteResponse response) {
		return !ERR_CODS.contains(response.getOError());
	}

	private Document buildDocumentFromWSCDExecuteResponse(WSCDCenExecuteResponse response, Integer docTypeCode) {
		Document document = new Document("0","");
		if("".equals(response.getONrodocumento()) || response.getONrodocumento() == null){
			return document;
		}
		document.setDescription(response.getONrodocumento().split(" ")[0]);
		document.setId(docTypeCode.toString());
		return document;
	}

}
