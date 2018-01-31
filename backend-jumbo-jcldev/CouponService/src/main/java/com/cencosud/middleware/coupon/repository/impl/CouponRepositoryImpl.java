package com.cencosud.middleware.coupon.repository.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.coupon.client.CencoCouponClient;
import com.cencosud.middleware.coupon.client.CencoCoupons;
import com.cencosud.middleware.coupon.client.CencoCoupons.Token;
import com.cencosud.middleware.coupon.exception.CouponServiceException;
import com.cencosud.middleware.coupon.model.enums.RequestProtocolEnum;
import com.cencosud.middleware.coupon.repository.CacheRepository;
import com.cencosud.middleware.coupon.repository.CouponRepository;
import com.fasterxml.jackson.core.type.TypeReference;


@Repository
public class CouponRepositoryImpl implements CouponRepository{

	private static final String TIME_ZN = "GMT-5";

	private static final String DATE_FRMT = "yyyy-MM-dd HH:mm:ss";
	private static final String NUMDOC_PARAM = "NUMDOC";
	private static final String DATE_PARAM = "date";
	private static final String AMBITO_PARAM = "AMBITO";
	private static final String WONG_PARAM_VALUE = "WONG";

	Logger logger = LoggerFactory.getLogger(CouponRepositoryImpl.class);
	
	@Autowired
	CencoCouponClient client;
	
	@Autowired
	CacheRepository cacheRepo;
	
	private final String SEARCH_COUPONS_URL = "";
	private final String GET_COUPONS_TOKEN = "/oauth2/token";
	private final TypeReference<List<CencoCoupons>> COUPON_REF = new TypeReference<List<CencoCoupons>>(){};
	private final TypeReference<Token> TOKEN_REF = new TypeReference<Token>(){};
	
	static final String cacheName = "TOKENS";
    static final String key = "prueba";

   
	@PostConstruct
    public void init() {
		logger.info("->Creating Cache: {}",cacheName);
        cacheRepo.createCache(cacheName, Token.class);    
    }
	
	private static String getFormattedDate(){
		SimpleDateFormat  sdf = new SimpleDateFormat(DATE_FRMT);
        sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZN));
        return sdf.format(new Date());
        
	}
	
	@Override
	public Token getNewToken() throws CouponServiceException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>(8);
		NameValuePair grantType = new BasicNameValuePair("grant_type", "client_credentials");
		NameValuePair clientId = new BasicNameValuePair("client_id", "bd986b7fe30b11e6916106831df067a8");
		NameValuePair clientSecret = new BasicNameValuePair("client_secret", "bd986b95e30b11e6916106831df067a8");
		nvps.add(grantType);
		nvps.add(clientId);
		nvps.add(clientSecret);

		Token tokenResp = client.executeOauth(this.GET_COUPONS_TOKEN, nvps, RequestProtocolEnum.POST, this.TOKEN_REF);		
//		Token tokenResp = new Token("k5hsofll4aoc88qbi92qnu4tio","Bearer");
		logger.info("New token obtained -------->{}",tokenResp.getAccess_token());
		return tokenResp;
	}
	
	@Override
	public List<CencoCoupons> searchCoupons(String dni, Token tokenResp) throws CouponServiceException {

		List<NameValuePair> nvps;
		
		Map<String,String> headers = new HashMap<>();
		headers.put("Authorization", tokenResp.getToken_type()+" "+tokenResp.getAccess_token());
		
		nvps = new ArrayList<NameValuePair>(8);
		NameValuePair numdoc = new BasicNameValuePair(NUMDOC_PARAM, dni);
		NameValuePair date = new BasicNameValuePair(DATE_PARAM, getFormattedDate());
		NameValuePair ambito = new BasicNameValuePair(AMBITO_PARAM, WONG_PARAM_VALUE);
		nvps.add(numdoc);
		nvps.add(date);
		nvps.add(ambito);
		List<CencoCoupons> vtexFilters = client.execute(this.SEARCH_COUPONS_URL, nvps, RequestProtocolEnum.GET, this.COUPON_REF, headers);

		return vtexFilters;
	}

    /* (non-Javadoc)
     * @see edu.globant.ltmj.dao.TokenRepo#getToken()
     */

    @Override
    public Token getToken() {
        return cacheRepo.get(cacheName, Token.class, key);
    }

    /* (non-Javadoc)
     * @see edu.globant.ltmj.dao.TokenRepo#setToken(edu.globant.ltmj.domain.Token)
     */

    @Override
    public void setToken(Token token) {
        cacheRepo.put(cacheName, Token.class, key, token);
    }



}
