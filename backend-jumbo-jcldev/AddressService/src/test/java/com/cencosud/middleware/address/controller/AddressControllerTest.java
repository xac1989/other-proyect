package com.cencosud.middleware.address.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.dto.GeoCoordinate;
import com.cencosud.middleware.address.repository.AddressRepository;
import com.cencosud.middleware.address.service.impl.JumboAddressServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

	@InjectMocks
	AddressController addressController;
	
	@Mock
	JumboAddressServiceImpl addressService;
	
	@Mock
	AddressRepository addressRepository;
	
	private List<AddressDto> addresses;
	private AddressDto address;
	private String region = "r2";
	private String profileId = "anyela.herrera@globant.com";
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		addresses = createAddressList();
		address = createAddress();
	}
	
	
	@Test
	public void findAddressesByUser() throws Exception {
		
		when(addressRepository.getAddresses(anyString())).thenReturn(addresses);
		when(addressService.getAddressesByUser(profileId)).thenReturn(addresses);
		
		ResponseEntity<List<AddressDto>> listResponse = addressController.findAddressesByUser(region, profileId);
		
		assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}

	@Test
	public void findAddressesByUserNoContent() throws Exception {
		
		when(addressRepository.getAddresses(anyString())).thenReturn(new ArrayList<AddressDto>());
		when(addressService.getAddressesByUser(profileId)).thenReturn(new ArrayList<AddressDto>());
		
		ResponseEntity<List<AddressDto>> listResponse = addressController.findAddressesByUser(region, profileId);
		
		assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
	}
	
	@Test
	public void deleteAddressById(){
		
		String addressId = "-1503349421861";
				
		doNothing().when(addressRepository).deleteAddress(addressId, profileId);
		doNothing().when(addressService).deleteAddressByUser(addressId, profileId);
		
		ResponseEntity<Object> response = addressController.deleteAddressById(region, profileId, addressId);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
	}
	
	@Test
	public void saveAddressesByUser() throws JSONException, JsonProcessingException{
		
		Errors errors = new BeanPropertyBindingResult(address, "addressId");
		String addresIdResponse = "-1503349421861";
		
		when(addressRepository.createAddress(profileId, address)).thenReturn(addresIdResponse);
		when(addressService.createAddressByUser(profileId, address)).thenReturn(addresIdResponse);
		
		ResponseEntity<AddressDto> response = addressController.saveAddressesByUser(region, profileId, address, errors);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
	}
	
	@Test
	public void saveAddressesByUserWithValidationErrors() throws JSONException, JsonProcessingException{
		
		Errors errors = new BeanPropertyBindingResult(address, "addressId");
		String addresIdResponse = "-1503349421861";
		
		when(addressRepository.createAddress(profileId, address)).thenReturn(addresIdResponse);
		when(addressService.createAddressByUser(profileId, address)).thenReturn(addresIdResponse);
		
		ResponseEntity<AddressDto> response = addressController.saveAddressesByUser(region, profileId, address, errors);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
	}
	
	
	private List<AddressDto> createAddressList(){
		List<AddressDto> addresses = new ArrayList<>();
		
		GeoCoordinate geoCoordinate = new GeoCoordinate();
		geoCoordinate.setLatitude(new BigDecimal(-33.4056776));
		geoCoordinate.setLongitude(new BigDecimal(-70.57427530000001));
		
		AddressDto address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0","-1503349421861","-1503349421861","residential","","Santiago","REGIÓN METROPOLITANA","","Cerro El Plomo","5630","Las Condes","","","Anyela Herrera",geoCoordinate);
		addresses.add(address);
		
		address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0","9a252a5ef3304c2787259d37f662fdb7","9a252a5ef3304c2787259d37f662fdb7","residential","2100000","Santiago","V REGIÓN","","Cerro El Plomo","5630","Los Andes","","8","Anyela Herrera",geoCoordinate);
		addresses.add(address);
		
		return addresses;
	}
	
	private AddressDto createAddress(){
		GeoCoordinate geoCoordinate = new GeoCoordinate();
		geoCoordinate.setLatitude(new BigDecimal(-33.4056776));
		geoCoordinate.setLongitude(new BigDecimal(-70.57427530000001));
		AddressDto address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0","-1503349421861","-1503349421861","residential","","Santiago","REGIÓN METROPOLITANA","","Cerro El Plomo","5630","Las Condes","","","Anyela Herrera",geoCoordinate);
		
		return address;
	}

}
