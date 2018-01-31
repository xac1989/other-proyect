package com.cencosud.middleware.coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.coupon.annotation.Loggable;
import com.cencosud.middleware.coupon.exception.CouponServiceException;
import com.cencosud.middleware.coupon.model.CouponsResult;
import com.cencosud.middleware.coupon.service.CouponService;

@RestController	
@RequestMapping("/coupons")
public class CouponController {
	
	@Autowired
	CouponService vtexService;

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{dni}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CouponsResult>> searchCoupons(@PathVariable String dni) throws CouponServiceException {
		
		ResponseEntity<List<CouponsResult>> response;
		List<CouponsResult> couponsResponse = vtexService.searchCoupons(dni);
		if(couponsResponse.isEmpty()){
			response = new ResponseEntity<List<CouponsResult>>(couponsResponse, HttpStatus.NOT_FOUND);
		}else{
			response = new ResponseEntity<List<CouponsResult>>(couponsResponse, HttpStatus.OK);
		}
		
		return response;
	}
	
}
