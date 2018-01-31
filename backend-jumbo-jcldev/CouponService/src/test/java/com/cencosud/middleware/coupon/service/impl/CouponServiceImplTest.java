package com.cencosud.middleware.coupon.service.impl;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.coupon.client.CencoCoupons;
import com.cencosud.middleware.coupon.client.CencoCoupons.Token;
import com.cencosud.middleware.coupon.exception.CouponServiceException;
import com.cencosud.middleware.coupon.model.CouponsResult;
import com.cencosud.middleware.coupon.repository.CouponRepository;
import com.cencosud.middleware.coupon.service.CouponService;

@RunWith(MockitoJUnitRunner.class)
public class CouponServiceImplTest {

	@InjectMocks
	CouponService couponService = new CouponServiceImpl();

	@Mock
	private CouponRepository couponRepository;

	List<CencoCoupons> coupons;
	
	private static final String DNI="42409049";
	private Token tokenResp = new Token("k5hsofll4aoc88qbi92qnu4tio","Bearer");
	

	@Before
	public void setUp() {
		coupons = createCoupons();
	}

	@Test
	public void searchCoupons() throws Exception {
		searchCouponsPreconditions();
		List<CouponsResult> couponList = couponService.searchCoupons(DNI);
		assertThat(couponList, notNullValue());
	}
	
	private void searchCouponsPreconditions() throws CouponServiceException {
		given(couponRepository.searchCoupons(DNI, tokenResp)).willReturn(coupons);
	}

	private List<CencoCoupons> createCoupons(){
		List<CencoCoupons> coupons = new ArrayList<>();
		CencoCoupons coupon = new CencoCoupons("1","1","42409049","","","2017-05-19","2017-05-25","00.00","00.00","1","2400000104780","",
				"ESTE CUPON PODRA SER USADO UNA SOLA VEZ DURANTE LA VIGENCIA DE LA PROMOCION. EL DESCUENTO APLICA SOBRE PRECIO DE OFERTA. STOCK MIN 5KG. COMPRA MAXIMA 3 KG UNIDADES POR PERSONA. NO ACUMULA CON OTROS CUPONES. VALIDO PRESENTANDO TARJETA BONUS EN TIENDA FISICA. NO VALIDO EN FONOCOMPRA NI WONG ONLINE. BONUS DE CANJE : 7027661000300812035,7027661000060328610",
				"0.2","ACEITES COMESTIBLES (VEGETAL Y OLIVA)","https:cencosud.vridge.io:83-App-APP_Aceites.jpg",
				"WONG","1","2017-05-18 23:15:49","prueba@cencosud.com.pe","","1","1");
		coupons.add(coupon);
		
		return coupons;
	}
}
