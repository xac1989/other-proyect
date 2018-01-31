package com.cencosud.middleware.coupon.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.coupon.client.CencoCoupons;
import com.cencosud.middleware.coupon.client.CencoCoupons.Token;
import com.cencosud.middleware.coupon.exception.CouponServiceException;
import com.cencosud.middleware.coupon.model.CouponsResult;
import com.cencosud.middleware.coupon.repository.CouponRepository;
import com.cencosud.middleware.coupon.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{
	
	Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

	@Autowired
	CouponRepository repo;

	@PostConstruct
	public void init(){
		logger.info("->Generate token from scratch");
		this.generateToken();
	}
	
	@Override
	public List<CouponsResult> searchCoupons(String dni) throws CouponServiceException {
		Token tokenResp = repo.getToken();
		List<CencoCoupons> vtexCouponResponse = null;
		try{
			vtexCouponResponse = repo.searchCoupons(dni, tokenResp);
		}catch(CouponServiceException e){
			tokenResp = this.generateToken();
			vtexCouponResponse = repo.searchCoupons(dni, tokenResp);
		}
		List<CouponsResult> couponsResponse = buildCouponsFromVtex(vtexCouponResponse);

		return couponsResponse;
	}

	private Token generateToken(){
		Token token = null;
		try {
			token = repo.getNewToken();
		} catch (CouponServiceException e) {
			logger.error("Error getting token from server",e);
		}
		repo.setToken(token);
		return token;
	}
	
	
	private List<CouponsResult> buildCouponsFromVtex(List<CencoCoupons> vtexCouponResponse) {
		List<CouponsResult> coupons = new ArrayList<>();
		
		for(CencoCoupons vtexCoupon : vtexCouponResponse){
			CouponsResult coupon = new CouponsResult();
			coupon.setDiscount(vtexCoupon.getDescuento());
			coupon.setEndDate(vtexCoupon.getFechaFin());
			coupon.setImage(vtexCoupon.getCodImagen());
			coupon.setLegal(vtexCoupon.getLegal());
			coupon.setMessage(vtexCoupon.getMensaje());
			coupon.setOfflineBarcode(vtexCoupon.getCodBarraOffline());
			coupon.setOnlineCode(vtexCoupon.getCodOnline());
			coupon.setChannel(vtexCoupon.getFlagCanal());
			coupons.add(coupon);
		}
		
		return coupons;
	}
	
}
