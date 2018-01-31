package com.cencosud.middleware.order.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ShippingDetailTest {

	ShippingDetail shippingDetail = new ShippingDetail();

	@Test
	public void getReceiverName() {
		String receiverName = "Luciano Ianicelli";
		shippingDetail.setReceiverName(receiverName);
		assertThat(receiverName, is(shippingDetail.getReceiverName()));
	}

	@Test
	public void getPostalCode() {
		String postalCode = "1100000";
		shippingDetail.setPostalCode(postalCode);
		assertThat(postalCode, is(shippingDetail.getPostalCode()));
	}

	@Test
	public void getCity() {
		String city = "Santiago";
		shippingDetail.setCity(city);
		assertThat(city, is(shippingDetail.getCity()));
	}

	@Test
	public void getState() {
		String state = "I REGIÓN";
		shippingDetail.setState(state);
		assertThat(state, is(shippingDetail.getState()));
	}

	@Test
	public void getCountry() {
		String country = "CHL";
		shippingDetail.setCountry(country);
		assertThat(country, is(shippingDetail.getCountry()));
	}

	@Test
	public void getStreet() {
		String street = "Cerro El Plomo";
		shippingDetail.setStreet(street);
		assertThat(street, is(shippingDetail.getStreet()));
	}

	@Test
	public void getNumber() {
		String number = "5630";
		shippingDetail.setNumber(number);
		assertThat(number, is(shippingDetail.getNumber()));
	}

}
