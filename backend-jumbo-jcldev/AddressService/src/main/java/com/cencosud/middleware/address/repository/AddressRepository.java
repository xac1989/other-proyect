package com.cencosud.middleware.address.repository;

import java.util.List;

import org.json.JSONException;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.dto.mapper.AddressMapper;
import com.cencosud.middleware.address.model.Address;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AddressRepository {
	List<AddressDto> getAddresses(String profileId);
	
	String createAddress(String profileId, AddressDto address) throws JSONException, JsonProcessingException;
	
	void updateAddress(String profileId, AddressDto address) throws JSONException, JsonProcessingException;
		
	void deleteAddress(String addressId, String profileId);

	AddressMapper getAddressMapper();
	
}
