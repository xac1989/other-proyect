package com.cencosud.middleware.address.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.dto.GeoCoordinate;
import com.cencosud.middleware.address.repository.AddressRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class JumboAddressServiceImplTest {
	
	@InjectMocks
	JumboAddressServiceImpl addressService;
	
	@Mock
	AddressRepository addressRepository;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getRegionId(){
		String REGION_ID = "r2";
		String regionId = addressService.getRegionId();
		
		assertThat(regionId).isEqualTo(REGION_ID);
	}
	
	@Test
	public void findAddressesByUser(){
		
		String profileId = "anyela.herrera@globant.com";
		List<AddressDto> addresses = new ArrayList<>();
		
		GeoCoordinate geoCoordinate = new GeoCoordinate();
		geoCoordinate.setLatitude(new BigDecimal(-33.4056776));
		geoCoordinate.setLongitude(new BigDecimal(-70.57427530000001));
		
		AddressDto address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0","-1503349421861","-1503349421861","residential","","Santiago","REGIÓN METROPOLITANA","","Cerro El Plomo","5630","Las Condes","","","Anyela Herrera",geoCoordinate);
		addresses.add(address);
		
		address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0","9a252a5ef3304c2787259d37f662fdb7","9a252a5ef3304c2787259d37f662fdb7","residential","2100000","Santiago","V REGIÓN","","Cerro El Plomo","5630","Los Andes","","8","Anyela Herrera",geoCoordinate);
		addresses.add(address);
		
		when(addressRepository.getAddresses(anyString())).thenReturn(addresses);
		
		List<AddressDto> listResponse = addressService.getAddressesByUser(profileId);
		
		verify(addressRepository, times(1)).getAddresses(anyString());
		
		assertThat(listResponse).isNotNull();
		assertThat(listResponse.get(0).getAddressName()).isEqualTo("-1503349421861");
		
	}
	
	@Test
	public void findAddressesByUserEmpty(){
		
		String profileId = "anyela.herrera@globant.com";
		List<AddressDto> addresses = null;
		
		when(addressRepository.getAddresses(anyString())).thenReturn(addresses);
		
		List<AddressDto> listResponse = addressService.getAddressesByUser(profileId);
		
		verify(addressRepository, times(1)).getAddresses(anyString());
		
		assertThat(listResponse).isNull();
	}
	
	
	@Test
	public void createAddressesByUser() throws JSONException, JsonProcessingException{
		
		String profileId = "anyela.herrera@globant.com";
		String addresIdResponse = "1";
		
		GeoCoordinate geoCoordinate = new GeoCoordinate();
		geoCoordinate.setLatitude(new BigDecimal(-33.4056776));
		geoCoordinate.setLongitude(new BigDecimal(-70.57427530000001));
		
		AddressDto address = new AddressDto("","","","residential","","Santiago","REGIÓN METROPOLITANA","","Cerro El Plomo","5630","Las Condes","","","Anyela Herrera",geoCoordinate);
		
		when(addressRepository.createAddress(profileId, address)).thenReturn(addresIdResponse);
		
		String addresId = addressService.createAddressByUser(profileId, address);
		
		assertThat(addresId).isNotNull();
		
	}
	
	@Test
	public void updateAddressesByUser() throws JSONException, JsonProcessingException {
		
		String profileId = "anyela.herrera@globant.com";
		
		GeoCoordinate geoCoordinate = new GeoCoordinate();
		geoCoordinate.setLatitude(new BigDecimal(-33.4056776));
		geoCoordinate.setLongitude(new BigDecimal(-70.57427530000001));
		
		AddressDto address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0","-1503349421861","-1503349421861","residential","","Santiago","REGIÓN METROPOLITANA","","Cerro El Plomo","5630","Las Condes","","","Anyela Herrera",geoCoordinate);
		
		doNothing().when(addressRepository).updateAddress(profileId, address);
		
		addressService.updateAddressByUser(profileId, address);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateAddressesByUserError() throws JSONException, JsonProcessingException {
		
		String profileId = "anyela.herrera@globant.com";
		
		GeoCoordinate geoCoordinate = new GeoCoordinate();
		geoCoordinate.setLatitude(new BigDecimal(-33.4056776));
		geoCoordinate.setLongitude(new BigDecimal(-70.57427530000001));
		
		AddressDto address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0",null,"-1503349421861","residential","","Santiago","REGIÓN METROPOLITANA","","Cerro El Plomo","5630","Las Condes","","","Anyela Herrera",geoCoordinate);
		
		addressService.updateAddressByUser(profileId, address);
		
	}
	
	@Test
	public void deleteAddressByUser() {
		
		String profileId = "anyela.herrera@globant.com";
		String addressId = "-1503349421861";
		
		doNothing().when(addressRepository).deleteAddress(addressId, profileId);
		
		addressService.deleteAddressByUser(addressId, profileId);
	}
	
	@Test
	public void getAddressById(){
		
		String profileId = "anyela.herrera@globant.com";
		String addressId = "-1503349421861";
		List<AddressDto> addresses = new ArrayList<>();
		
		GeoCoordinate geoCoordinate = new GeoCoordinate();
		geoCoordinate.setLatitude(new BigDecimal(-33.4056776));
		geoCoordinate.setLongitude(new BigDecimal(-70.57427530000001));
		
		AddressDto address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0","-1503349421861","-1503349421861","residential","","Santiago","REGIÓN METROPOLITANA","CH","Cerro El Plomo","5630","Las Condes","","","Anyela Herrera",geoCoordinate);
		addresses.add(address);
		
		address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0","9a252a5ef3304c2787259d37f662fdb7","9a252a5ef3304c2787259d37f662fdb7","residential","2100000","Santiago","V REGIÓN","","Cerro El Plomo","5630","Los Andes","","8","Anyela Herrera",geoCoordinate);
		addresses.add(address);
		
		when(addressRepository.getAddresses(anyString())).thenReturn(addresses);
		
		AddressDto response = addressService.getAddressById(profileId, addressId);
		
		assertThat(response).isNotNull();
		assertThat(response.getAddressId()).isEqualTo(addressId);
		
	}
	
	@Test
	public void getAddressByIdNull(){
		String profileId = "anyela.herrera@globant.com";
		String addressId = null;
		
		when(addressRepository.getAddresses(anyString())).thenReturn(null);
		
		AddressDto response = addressService.getAddressById(profileId, addressId);
		
		assertThat(response).isNotNull();
		assertThat(response.getAddressId()).isNull();
	}
	
}
