package com.cencosud.middleware.profile.model;

/**
 * 
 * @author anyela.herrera
 * @version 1.0
 * @since Aug 21, 2017
 *
 */
public class Address {

	private String userId = "";
	private String addressId = "";
	private String addressName = "";
	private String addressType = "";
	private String postalCode = "";
	private String city = "";
	private String country = "";
	private String state = "";
	private String street = "";
	private String number = "";
	private String neighborhood = "";
	private String complement = "";
	private String reference = "";
	private String receiverName = "";
	private GeoCoordinate geoCoordinate;

	public Address() {
		super();
	}

	public Address(String userId, String addressName, String addressType, String postalCode, String city, String state,
			String street, String number, String neighborhood, String complement, String receiverName,
			GeoCoordinate geoCoordinate) {
		super();
		this.userId = userId;
		this.addressName = addressName;
		this.addressType = addressType;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.complement = complement;
		this.receiverName = receiverName;
		this.geoCoordinate = geoCoordinate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		if (userId != null) {
			this.userId = userId;
		}
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		if (addressName != null) {
			this.addressName = addressName;
		}
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		if (addressType != null) {
			this.addressType = addressType;
		}
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		if (postalCode != null) {
			this.postalCode = postalCode;
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city != null) {
			this.city = city;
		}
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if (state != null) {
			this.state = state;
		}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		if (street != null) {
			this.street = street;
		}
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if (number != null) {
			this.number = number;
		}
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		if (neighborhood != null) {
			this.neighborhood = neighborhood;
		}
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		if (complement != null) {
			this.complement = complement;
		}
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		if (receiverName != null) {
			this.receiverName = receiverName;
		}
	}

	public GeoCoordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		if (addressId != null) {
			this.addressId = addressId;
		}
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		if (country != null) {
			this.country = country;
		}
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		if (reference != null) {
			this.reference = reference;
		}
	}


}
