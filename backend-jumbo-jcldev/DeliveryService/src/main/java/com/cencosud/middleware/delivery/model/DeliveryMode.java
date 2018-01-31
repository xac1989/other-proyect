package com.cencosud.middleware.delivery.model;

import java.util.List;

public class DeliveryMode {

	private int salesChannel;
	private List<DeliveryType> type;

	public DeliveryMode(int salesChannel, List<DeliveryType> type) {
		this.salesChannel = salesChannel;
		this.type = type;
	}

	public DeliveryMode() {
		super();
	}

	public int getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(int salesChannel) {
		this.salesChannel = salesChannel;
	}

	public List<DeliveryType> getType() {
		return type;
	}

	public void setType(List<DeliveryType> type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DeliveryMode [salesChannel=" + salesChannel + ", type=" + type + "]";
	}

}
