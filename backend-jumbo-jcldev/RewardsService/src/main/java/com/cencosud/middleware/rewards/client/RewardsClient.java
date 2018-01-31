package com.cencosud.middleware.rewards.client;

import com.cencosud.middleware.rewards.client.wsdl.WSCDCenExecuteResponse;

public interface RewardsClient {

	WSCDCenExecuteResponse getRewards(Integer docTypeCode, String documentNumber);

}
