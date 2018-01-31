package com.cencosud.middleware.bonus.client;

import com.cencosud.middleware.bonus.client.wsdl.QueryLoyaltyMemberFullResponseEBMType;

/**
 * 
 * 
 * <h1>BonusClient</h1>
 * <p>
 * Metodos para consultar el servicio de cencosud puntos.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since May 31, 2017
 */
public interface BonusClient {
	
	/**
	 * Obtiene la informacion del servicio de puntos.
	 * @param docType {@link String}
	 * @param documentNumber {@link String}
	 * @return {@link QueryLoyaltyMemberFullResponseEBMType}
	 */
	QueryLoyaltyMemberFullResponseEBMType getBonus(String docType, String documentNumber);
}
