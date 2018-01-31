package com.cencosud.middleware.order.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OrderDetailTest {

	private OrderDetail orderDetail = new OrderDetail();

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
		ShippingDetail shippingDetail = new ShippingDetail();
		orderDetail.setShippingDetail(shippingDetail);
		assertThat(shippingDetail, is(orderDetail.getShippingDetail()));
	}

	@Test
	public void getPaymentDetails() {
		List<PaymentDetail> paymentDetails = new ArrayList<>();
		orderDetail.setPaymentDetails(paymentDetails);
		assertThat(paymentDetails, is(orderDetail.getPaymentDetails()));
	}

	@Test
	public void getTotals() {
		List<PaymentTotal> totals = new ArrayList<>();
		orderDetail.setTotals(totals);
		assertThat(totals, is(orderDetail.getTotals()));
	}

	@Test
	public void getItems() {
		List<ItemDetail> itemDetails = new ArrayList<>();
		orderDetail.setItems(itemDetails);
		assertThat(itemDetails, is(orderDetail.getItems()));
	}

	@Test
	public void getStatusDescription() {
		String statusDescription = "Preparando Entrega";
		orderDetail.setStatusDescription(statusDescription);
		assertThat(statusDescription, is(orderDetail.getStatusDescription()));
	}

}
