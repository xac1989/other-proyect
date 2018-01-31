package com.cencosud.middleware.coupon.service;

import java.util.List;

import com.cencosud.middleware.coupon.exception.CouponServiceException;
import com.cencosud.middleware.coupon.model.CouponsResult;
	

public interface CouponService {


	List<CouponsResult> searchCoupons(String dni) throws CouponServiceException;

}
