package com.cencosud.middleware.address.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.dto.mapper.AddressMapper;
import com.cencosud.middleware.address.model.Address;
import com.cencosud.middleware.address.repository.AddressRepository;
import com.cencosud.middleware.profile.utils.StringUtils;
import com.cencosud.middleware.profile.utils.VtexUtilClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
	
	private static final String PATH_ADDRESSES_PROFILE = "/api/profile-system/pvt/profiles/";
	private static final String ADDRESS = "/addresses";
	
	static final Logger LOGGER = LoggerFactory.getLogger(AddressRepositoryImpl.class);
	
	@Autowired
	@Qualifier("vTexUtilClient")
	private VtexUtilClient clientUtil;	

	@Override
	public List<AddressDto> getAddresses(String userId) {
		
		StringBuilder path = new StringBuilder(PATH_ADDRESSES_PROFILE);
		path.append(userId);
		path.append(ADDRESS);
		
		if (userId==null || "".equals(userId))
			return new ArrayList<>();
		
		String addressesVtex = clientUtil.executeService(path.toString(), null, String.class, HttpMethod.GET, null, true);
		
		if ((StringUtils.isNull(addressesVtex)))
			return new ArrayList<>();
		
		return getAddressesToLinkedHash(addressesVtex);
		
	}

	private LinkedList<AddressDto> getAddressesToLinkedHash( String addressesVtex ) {
		
		LinkedList<AddressDto> addresses = new LinkedList<>();
		Address address = null;
		AddressDto addressResponse = null;

		LinkedHashMap<String, Object> addressesMap = convertStringToMap(addressesVtex);
		
		for (Entry<String, Object> addressMap: addressesMap.entrySet()) {

			try {
				address = new ObjectMapper().readValue((String)addressMap.getValue(),Address.class);
				address.setAddressId(addressMap.getKey());
				
				AddressMapper addressMapper = getAddressMapper();
				addressResponse = addressMapper.generateAddress(address);
				
				addresses.add(addressResponse);
			} catch (IOException e) {
				LOGGER.info("Error reading vtex response");
			}
	    }
		
		return addresses;
	}

	private LinkedHashMap<String, Object> convertStringToMap(String addressesVtex) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(addressesVtex,
			        new TypeReference<LinkedHashMap<String, Object>>() {
			        });
		} catch (IOException e) {
			LOGGER.error("Exception in convertStringToMap ", e);
		}
		return new LinkedHashMap<>();
	}
	
	@Override
	public String createAddress(String profileId, AddressDto address) throws JSONException, JsonProcessingException {

		AddressMapper addressMapper = this.getAddressMapper();
		Address addressFind = addressMapper.generateAddressBack(address);
		
		addressFind.setAddressId(UUID.randomUUID().toString());
		JSONObject addresJson = buildJson(addressFind);
		Object result = clientUtil.executeService(PATH_ADDRESSES_PROFILE+profileId+ADDRESS, addresJson.toString(), Object.class, HttpMethod.POST, true);
		LOGGER.info(result.toString());
		
		return addressFind.getAddressId();
	}
	

	@Override
	public void updateAddress(String profileId, AddressDto address) throws JsonProcessingException {
		
		AddressMapper addressMapper = this.getAddressMapper();
		Address addressFind = addressMapper.generateAddressBack(address);
		
		JSONObject addresJson = buildJson(addressFind);
		Object result = clientUtil.executeService(PATH_ADDRESSES_PROFILE+profileId+ADDRESS, addresJson.toString(), Object.class, HttpMethod.POST, true);
		LOGGER.info(result.toString());
	}


	private JSONObject buildJson(Address address) throws JSONException, JsonProcessingException {
		JSONObject json = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();	

		json.put(address.getAddressId(), mapper.writeValueAsString(address));
		
		return json;
	}

	
	@Override
	public void deleteAddress(String addressId, String profileId) {
		clientUtil.executeService(PATH_ADDRESSES_PROFILE+profileId+ADDRESS+"/"+addressId, null, Object[].class, HttpMethod.DELETE, true);
	}
	
	@Override
	public AddressMapper getAddressMapper(){
		return Mappers.getMapper(AddressMapper.class);
	}
	
	
	
}
