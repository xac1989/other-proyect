package com.cencosud.middleware.order.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class PaymentTotalTest {

	private PaymentTotal paymentTotal = new PaymentTotal();

	@Test
	public void getId() {
		String id = "Items";
		paymentTotal.setId(id);
		assertThat(id, is(paymentTotal.getId()));
	}

	@Test
	public void getName() {
		String name = "Total de los itens";
		paymentTotal.setName(name);
		assertThat(name, is(paymentTotal.getName()));
	}

	@Test
	public void getTotal() {
		BigDecimal total = BigDecimal.valueOf(8090.00);
		paymentTotal.setTotal(total);
		assertThat(total, is(paymentTotal.getTotal()));
	}

}
