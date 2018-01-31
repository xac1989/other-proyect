package com.cencosud.mobile.saleschannel.model;

public class DeliveryType implements Cloneable {
	private Integer id;
	private String name;
	private boolean enabled = false;

	public DeliveryType() {
	}

	/**
	 * @param id
	 * @param name
	 * @param enabled
	 */
	public DeliveryType(Integer id, String name, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.enabled = enabled;
	}

	/**
	 * @param id
	 * @param name
	 */
	public DeliveryType(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public DeliveryType clone() throws CloneNotSupportedException {

		DeliveryType clone = (DeliveryType) super.clone();
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeliveryType [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append("]");
		return builder.toString();
	}

}
