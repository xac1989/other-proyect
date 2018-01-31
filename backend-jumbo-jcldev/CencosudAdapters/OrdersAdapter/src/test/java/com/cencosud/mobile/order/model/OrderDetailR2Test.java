package com.cencosud.mobile.order.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OrderDetailR2Test {

	private OrderDetailR2 orderDetail = new OrderDetailR2();

	@Test
	public void getId() {
		String id = "v10008834jmqa-01";
		orderDetail.setId(id);
		assertThat(id, is(orderDetail.getId()));

	}

	@Test
	public void getDate() {
		String date = "2017-09-05T14:27:23.7832422+00:00";
		orderDetail.setDate(date);
		assertThat(date, is(orderDetail.getDate()));
	}

	@Test
	public void getShippingDetail() {
		Object shippingDetail = new Object();
		orderDetail.setShippingDetail(shippingDetail);
		assertThat(shippingDetail, is(orderDetail.getShippingDetail()));
	}

	@Test
	public void getPaymentDetails() {
		List<Object> paymentDetails = new ArrayList<>();
		orderDetail.setPaymentDetails(paymentDetails);
		assertThat(paymentDetails, is(orderDetail.getPaymentDetails()));
	}

	@Test
	public void getTotals() {
		List<Object> totals = new ArrayList<>();
		orderDetail.setTotals(totals);
		assertThat(totals, is(orderDetail.getTotals()));
	}

	@Test
	public void getItems() {
		List<ItemDetailR2> itemDetails = new ArrayList<>();
		orderDetail.setItems(itemDetails);
		assertThat(itemDetails, is(orderDetail.getItems()));
	}

	@Test
	public void getStatusDescription() {
		String statusDescription = "Preparando Entrega";
		orderDetail.setStatusDescription(statusDescription);
		assertThat(statusDescription, is(orderDetail.getStatusDescription()));
	}

	@Test
	public void getPaymentTicketUrl() {
		String paymentTicketUrl = "http://jumbochilehomolog.vteximg.com.br/arquivos/ids/188466-100-100/1561251.jpg";
		orderDetail.setPaymentTicketUrl(paymentTicketUrl);
		assertThat(paymentTicketUrl, is(orderDetail.getPaymentTicketUrl()));
	}
}
