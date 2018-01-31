package com.cencosud.middleware.address.dto;

import javax.validation.constraints.NotNull;

import com.cencosud.middleware.profile.utils.MsgValidation;

/**
 * 
 * @author anyela.herrera
 * @version 1.0
 * @since Oct 20, 2017
 *
 */
public class AddressDto {

	@NotNull(message = MsgValidation.USERID_NULL)
	private String userId;

	@NotNull(message = MsgValidation.ADDRESSID_NULL)
	private String addressId;

	@NotNull(message = MsgValidation.ADDRESSNAME_NULL)
	private String addressName;

	@NotNull(message = MsgValidation.ADDRESSTYPE_NULL)
	private String addressType;

	@NotNull(message = MsgValidation.POSTALCODE_NULL)
	private String postalCode;

	@NotNull(message = MsgValidation.CITY_NULL)
	private String city;

	@NotNull(message = MsgValidation.COUNTRY_NULL)
	private String country;

	@NotNull(message = MsgValidation.STATE_NULL)
	private String state;

	@NotNull(message = MsgValidation.STREET_NULL)
	private String street;

	@NotNull(message = MsgValidation.NUMBER_NULL)
	private String number;

	@NotNull(message = MsgValidation.NEIGHBORHOOD_NULL)
	private String neighborhood;

	@NotNull(message = MsgValidation.COMPLEMENT_NULL)
	private String complement;

	@NotNull(message = MsgValidation.REFERENCE_NULL)
	private String reference;

	@NotNull(message = MsgValidation.RECEIVERNAME_NULL)
	private String receiverName;

	@NotNull(message = MsgValidation.GEOCOORDINATE_NULL)
	private GeoCoordinate geoCoordinate;

	public AddressDto() {
		super();
	}

	public AddressDto(String userId, String addressId, String addressName, String addressType, String postalCode,
			String city, String country, String state, String street, String number, String neighborhood,
			String complement, String reference, String receiverName, GeoCoordinate geoCoordinate) {
		this.userId = userId;
		this.addressId = addressId;
		this.addressName = addressName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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
		return "Address [userId=" + userId + ", addressId=" + addressId + ", addressName=" + addressName
				+ ", addressType=" + addressType + ", postalCode=" + postalCode + ", city=" + city + ", country="
				+ country + ", state=" + state + ", street=" + street + ", number=" + number + ", neighborhood="
				+ neighborhood + ", complement=" + complement + ", reference=" + reference + ", receiverName="
				+ receiverName + ", geoCoordinate=" + geoCoordinate + "]";
	}

}
