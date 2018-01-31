package com.cencosud.mobile.profile.model;

/**
 * 
 * @author anyela.herrera
 * @version 1.0
 * @since Aug 21, 2017
 *
 */
public class Address {

	private String userId;
	private String addressName;
	private String addressId;
	private String addressType;
	private String postalCode; 
	private String city;
	private String country;
	private String state;
	private String street;
	private String number;
	private String neighborhood;
	private String complement;
	private String reference;
	private String receiverName;
	private GeoCoordinate geoCoordinate;
	
	public Address() {
		super();
	}
	

	public Address(String userId, String addressName, String addressId, String addressType, String postalCode,
			String city, String country, String state, String street, String number, String neighborhood,
			String complement, String reference, String receiverName, GeoCoordinate geoCoordinate) {
		this.userId = userId;
		this.addressName = addressName;
		this.addressId = addressId;
		this.addressType = addressType;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
		this.state = state;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.complement = complement;
		this.reference = reference;
		this.receiverName = receiverName;
		this.geoCoordinate = geoCoordinate;
	}



	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getAddressId() {
		return addressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public GeoCoordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "Address [userId=" + userId + ", addressName=" + addressName + ", addressId=" + addressId
				+ ", addressType=" + addressType + ", postalCode=" + postalCode + ", city=" + city + ", country="
				+ country + ", state=" + state + ", street=" + street + ", number=" + number + ", neighborhood="
				+ neighborhood + ", complement=" + complement + ", reference=" + reference + ", receiverName="
				+ receiverName + ", geoCoordinate=" + geoCoordinate + "]";
	}


}
