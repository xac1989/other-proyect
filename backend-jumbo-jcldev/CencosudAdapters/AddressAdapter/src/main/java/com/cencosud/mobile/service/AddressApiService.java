package com.cencosud.mobile.service;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.profile.model.Address;
import com.cencosud.mobile.profile.model.AddressResponse;

public interface AddressApiService {

	
	String getUserAddresses(String email, String region) throws NotFoundException;

	String deleteUserAddress(String email, String addressId, String region) throws NotFoundException;

	AddressResponse createUserAddress(String email, String region, Address address) throws NotFoundException;

	AddressResponse editUserAddress(String email, String region, Address address) throws NotFoundException;
	
	
}
