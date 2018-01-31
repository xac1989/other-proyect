package com.cencosud.middleware.order.client.dto;

import java.math.BigDecimal;

import com.cencosud.middleware.order.model.PaymentDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VtexPaymentInfo {

	private String paymentSystemName;
	private int value;

	public String getPaymentSystemName() {
		return paymentSystemName;
	}

	public void setPaymentSystemName(String paymentSystemName) {
		this.paymentSystemName = paymentSystemName;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public PaymentDetail toModelPaymentDetail() {
		return new PaymentDetail(paymentSystemName, BigDecimal.valueOf(value).movePointLeft(2));
	}
}
