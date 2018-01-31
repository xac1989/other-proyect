package com.cencosud.middleware.order.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class PaymentDetailTest {

	private PaymentDetail paymentDetail = new PaymentDetail();

	@Test
	public void getPaymentSystem() {
		String paymentSystem = "Pago de Pruebas";
		paymentDetail.setPaymentSystem(paymentSystem);
		assertThat(paymentSystem, is(paymentDetail.getPaymentSystem()));
	}

	@Test
	public void getTotal() {
		BigDecimal total = BigDecimal.valueOf(9080.00);
		paymentDetail.setTotal(total);
		assertThat(total, is(paymentDetail.getTotal()));
	}
}
