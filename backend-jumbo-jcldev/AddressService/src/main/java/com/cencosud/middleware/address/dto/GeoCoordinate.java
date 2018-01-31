package com.cencosud.middleware.address.dto;

import java.math.BigDecimal;

public class GeoCoordinate {

	private BigDecimal latitude;
	private BigDecimal longitude;

	public GeoCoordinate(BigDecimal latitude, BigDecimal longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public GeoCoordinate() {
		super();
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "GeoCoordinate [latitude=" + latitude + ", length=" + longitude + "]";
	}

}
