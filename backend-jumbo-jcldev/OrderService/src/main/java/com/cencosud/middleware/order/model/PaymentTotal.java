package com.cencosud.middleware.order.model;

import java.math.BigDecimal;

public class PaymentTotal {
	private String id;
	private String name;
	private BigDecimal total;
	
	public PaymentTotal(String id, String name, BigDecimal total) {
		super();
		this.id = id;
		this.name = name;
		this.total = total;
	}
	
	public PaymentTotal() {
	}

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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
