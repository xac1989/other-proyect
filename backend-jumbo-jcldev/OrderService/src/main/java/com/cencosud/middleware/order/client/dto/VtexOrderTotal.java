package com.cencosud.middleware.order.client.dto;

import java.math.BigDecimal;

import com.cencosud.middleware.order.model.PaymentTotal;

public class VtexOrderTotal {
	private String id;
	private String name;
	private int value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public PaymentTotal toModelPaymentTotal() {
		return new PaymentTotal(id, name, BigDecimal.valueOf(value).movePointLeft(2));
	}
}
