package com.cencosud.middleware.order.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class JumboOrderDetailTest extends OrderDetail {

	private JumboOrderDetail jumboOrderDetail = new JumboOrderDetail();

	@Test
	public void getPaymentTicketUrl() {
		String paymentTicketUrl = "http://jumbochilehomolog.vteximg.com.br/arquivos/ids/188466-100-100/1561251.jpg";
		jumboOrderDetail.setPaymentTicketUrl(paymentTicketUrl);
		assertThat(paymentTicketUrl, is(jumboOrderDetail.getPaymentTicketUrl()));
	}

}
