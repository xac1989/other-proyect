package com.cencosud.middleware.delivery.model;

public class DeliveryType {

	private int id;
	private String name;
	private boolean enable;

	public DeliveryType(int id, String name, boolean enable) {
		this.id = id;
		this.name = name;
		this.enable = enable;
	}

	public DeliveryType() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "DeliveryType [id=" + id + ", name=" + name + ", enable=" + enable + "]";
	}

}
