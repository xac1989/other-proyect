package com.cencosud.middleware.coupon.repository;

import java.util.List;

import com.cencosud.middleware.coupon.client.CencoCoupons;
import com.cencosud.middleware.coupon.client.CencoCoupons.Token;
import com.cencosud.middleware.coupon.exception.CouponServiceException;

public interface CouponRepository {

	List<CencoCoupons> searchCoupons(String dni, Token tokenResp) throws CouponServiceException;
	
	Token getToken();

	void setToken(Token token);

	Token getNewToken() throws CouponServiceException;
}
