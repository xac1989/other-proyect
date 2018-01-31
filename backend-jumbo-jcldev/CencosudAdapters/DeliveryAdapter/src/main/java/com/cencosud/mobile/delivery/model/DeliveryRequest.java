package com.cencosud.mobile.delivery.model;

public class DeliveryRequest {

	private GeoCoordinate geoCoordinate;
	private String postalCode;
	private int shippingModeId;
	private int salesChannel;

	public DeliveryRequest(GeoCoordinate geoCoordinate, String postalCode, int shippingModeId, int salesChannel) {
		super();
		this.geoCoordinate = geoCoordinate;
		this.postalCode = postalCode;
		this.shippingModeId = shippingModeId;
		this.salesChannel = salesChannel;
	}

	public DeliveryRequest() {
		super();
	}

	public GeoCoordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int getShippingModeId() {
		return shippingModeId;
	}

	public void setShippingModeId(int shippingModeId) {
		this.shippingModeId = shippingModeId;
	}

	public int getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(int salesChannel) {
		this.salesChannel = salesChannel;
	}

	@Override
	public String toString() {
		return "DeliveryRequest [geoCoordinate=" + geoCoordinate + ", postalCode=" + postalCode + ", shippingModeId="
				+ shippingModeId + ", salesChannel=" + salesChannel + "]";
	}

}
