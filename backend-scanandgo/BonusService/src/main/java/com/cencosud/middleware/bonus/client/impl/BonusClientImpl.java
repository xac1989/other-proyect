package com.cencosud.middleware.bonus.client.impl;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.cencosud.middleware.bonus.client.BonusClient;
import com.cencosud.middleware.bonus.client.wsdl.EBMHeaderType;
import com.cencosud.middleware.bonus.client.wsdl.MemberIdentifierType;
import com.cencosud.middleware.bonus.client.wsdl.ObjectFactory;
import com.cencosud.middleware.bonus.client.wsdl.QueryLoyaltyMemberFullRequestEBMType;
import com.cencosud.middleware.bonus.client.wsdl.QueryLoyaltyMemberFullResponseEBMType;
import com.cencosud.middleware.bonus.client.wsdl.QueryLoyaltyMemberType;
import com.cencosud.middleware.bonus.client.wsdl.SenderType;

/**
 * 
 * 
 * <h1>BonusClientImpl</h1>
 * <p>
 * Implementacion del cliente de puntos cencosud para realizar la consulta.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since May 31, 2017
 */
@Component
public class BonusClientImpl implements BonusClient {
	
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	private static final Logger log = LoggerFactory.getLogger(BonusClientImpl.class);
	
	private static final String EBM_ID="gero et";
	private static final String EBM_APPLICATION="ventos tempestatesque";
	private static final String EBM_COUNTRY="temperat iras";
	private static final String EBM_BUSINESS_UNIT="turbine corripuit";
	private static final String COUNTRY="56";
	private static final String LOYALTY_PROGRAM="Puntos Cencosud";
	
	@Override
	public QueryLoyaltyMemberFullResponseEBMType getBonus(String docType, String documentNumber) {
		try {
			EBMHeaderType ebmHeaderType = new EBMHeaderType();
			ebmHeaderType.setEBMID(EBM_ID);
			
			SenderType sender = new SenderType();
			sender.setApplication(EBM_APPLICATION);
			sender.setCountry(EBM_COUNTRY);
			sender.setBusinessUnit(EBM_BUSINESS_UNIT);
			
			ebmHeaderType.setSender(sender);
			
			QueryLoyaltyMemberType dataArea = new QueryLoyaltyMemberType();
			MemberIdentifierType queryLoyaltyMember = new MemberIdentifierType();
			queryLoyaltyMember.setDocumentType(docType);
			queryLoyaltyMember.setDocumentNumber(documentNumber);
			queryLoyaltyMember.setCountry(COUNTRY);
			queryLoyaltyMember.setLoyaltyProgram(LOYALTY_PROGRAM);
			dataArea.setQueryLoyaltyMember(queryLoyaltyMember);
			
			QueryLoyaltyMemberFullRequestEBMType request = new QueryLoyaltyMemberFullRequestEBMType();
			request.setEBMHeader(ebmHeaderType);
			request.setDataArea(dataArea);
			
			ObjectFactory factory = new ObjectFactory();
			
			JAXBElement<QueryLoyaltyMemberFullRequestEBMType> element = factory.createQueryLoyaltyMemberFullRequestEBM(request);
			
			@SuppressWarnings("unchecked")
			JAXBElement<QueryLoyaltyMemberFullResponseEBMType> response = (JAXBElement<QueryLoyaltyMemberFullResponseEBMType>) webServiceTemplate.marshalSendAndReceive(element);
			return response.getValue();
			
		} catch (Exception e) {
			log.error("Error  llamando al servicio web", e);
			return null;
		}
	}

}
