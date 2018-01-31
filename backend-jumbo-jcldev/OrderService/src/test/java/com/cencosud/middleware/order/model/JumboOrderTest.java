package com.cencosud.middleware.order.model;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class JumboOrderTest {

	JumboOrder jumboOrder = new JumboOrder();

	@Test
	public void getId() {
		String id = "v10009544jmqa-01";
		jumboOrder.setId(id);
		Assert.assertThat(jumboOrder.getId(), Matchers.is(id));
	}

	@Test
	public void getDate() {
		String date = "2017-04-21T16:13:40.7602436+00:00";
		jumboOrder.setDate(date);
		Assert.assertThat(jumboOrder.getDate(), Matchers.is(date));
	}

	@Test
	public void getStatusDescription() {
		String statusDescription = "Preparando Entrega";
		jumboOrder.setStatusDescription(statusDescription);
		Assert.assertThat(jumboOrder.getStatusDescription(), Matchers.is(statusDescription));
	}

	@Test
	public void getSalesChannel() {
		String salesChannel = "1";
		jumboOrder.setSalesChannel(salesChannel);
		Assert.assertThat(jumboOrder.getSalesChannel(), Matchers.is(salesChannel));
	}

	@Test
	public void getTotalValue() {
		BigDecimal totalValue = BigDecimal.valueOf(1591.2);
		jumboOrder.setTotalValue(totalValue);
		Assert.assertThat(jumboOrder.getTotalValue(), Matchers.is(totalValue));
	}

	@Test
	public void getQuantityProducts() {
		int quantityProducts = 1;
		jumboOrder.setQuantityProducts(quantityProducts);
		Assert.assertThat(jumboOrder.getQuantityProducts(), Matchers.is(quantityProducts));
	}
}
