package com.cencosud.mobile.model.profile.jumbo;

public class DeliveryMode {
	
	private String name;
	private Integer shippingModeId;
	private DeliveryModeType type;
	
	public DeliveryMode() {
	}

	/**
	 * @param name
	 * @param shippingModeId
	 * @param type
	 */
	public DeliveryMode(String name, Integer shippingModeId, DeliveryModeType type) {
		super();
		this.name = name;
		this.shippingModeId = shippingModeId;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shippingModeId
	 */
	public Integer getShippingModeId() {
		return shippingModeId;
	}

	/**
	 * @param shippingModeId the shippingModeId to set
	 */
	public void setShippingModeId(Integer shippingModeId) {
		this.shippingModeId = shippingModeId;
	}

	/**
	 * @return the type
	 */
	public DeliveryModeType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(DeliveryModeType type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeliveryMode [name=");
		builder.append(name);
		builder.append(", shippingModeId=");
		builder.append(shippingModeId);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}
