package com.cencosud.mobile.order.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class OrderDetailResponseR2Test {
	OrderDetailResponseR2 ordersResponse = new OrderDetailResponseR2();

	@Test
	public void getMetadata() {
		Metadata metadata = new Metadata();
		metadata.setRegion("r2");
		metadata.setVersion("v1");
		metadata.setRequestTime("Sat, 30 Sep 2017 12:50:59 Z");
		ordersResponse.setMetadata(metadata);
		assertThat(ordersResponse.getMetadata().getRegion(), is("r2"));
		assertThat(ordersResponse.getMetadata().getVersion(), is("v1"));
		assertThat(ordersResponse.getMetadata().getRequestTime(), is("Sat, 30 Sep 2017 12:50:59 Z"));
	}

	@Test
	public void getOrder() {
		OrderDetailR2 orderDetailR2 = new OrderDetailR2();
		ordersResponse.setOrder(orderDetailR2);
		assertThat(ordersResponse.getOrder(), Matchers.equalTo(orderDetailR2));
	}

	@Test
	public void toIndentedString() {
		String text = "test \nindented";
		String textIndented = ordersResponse.toIndentedString(text);
		assertThat(textIndented, is("test \n    indented"));
	}

	@Test
	public void toIndentedNull() {
		String text = null;
		String textIndented = ordersResponse.toIndentedString(text);
		assertThat(textIndented, is("null"));
	}

}
