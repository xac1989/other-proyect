package com.cencosud.middleware.address.repository.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.dto.GeoCoordinate;
import com.cencosud.middleware.profile.utils.VtexUtilClient;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class})
public class AddressRepositoryImplTest {

	private static final String VALID_PATH = "/api/profile-system/pvt/profiles/g.granados@globant.com/addresses";

	private static final String VALID_PROF_ID = "g.granados@globant.com";

	@InjectMocks
	private AddressRepositoryImpl repo;

	@Mock
	private VtexUtilClient clientUtil;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private String vtexAddress;
	private AddressDto address;
	private static Logger logger = LoggerFactory.getLogger(VtexUtilClient.class);

	@Before
	public void setUp(){
		GeoCoordinate geoCoordinate = new GeoCoordinate();
		geoCoordinate.setLatitude(new BigDecimal(-33.4056776));
		geoCoordinate.setLongitude(new BigDecimal(-70.57427530000001));
		
		address = new AddressDto("3433ff59-5da2-11e7-9538-0ad3911cfea0", "9a252a5ef3304c2787259d37f662fdb7",
				"9a252a5ef3304c2787259d37f662fdb7", "residential", "2100000", "Santiago", "V REGIÓN", "",
				"Cerro El Plomo", "5630", "Los Andes", "", "8", "Anyela Herrera", geoCoordinate);
	}

	@Test
	public void testGetAddress(){
		vtexAddress = getVtexResponse("vtexJumboAddresses");
		when(clientUtil.executeService(VALID_PATH, null, String.class, HttpMethod.GET, null, true))
				.thenReturn(vtexAddress);
		List<AddressDto> result = repo.getAddresses(VALID_PROF_ID);

		assertThat(result, is(not(empty())));
		assertThat(result.size(), is(5));
	}
	
	@Test
	public void testGetAddressBadVtexResponse() {
		vtexAddress = "xxcvbnm,fghjk[][][]";		
		when(clientUtil.executeService(VALID_PATH, null, String.class, HttpMethod.GET, null, true))
				.thenReturn(vtexAddress);
		
		List<AddressDto> result = repo.getAddresses(VALID_PROF_ID);
		assertThat(result, is(empty()));
		
		vtexAddress = "{\"527b6278-4f12-4777-82be-f0641dea5bb2\":\"some\"}";		
		when(clientUtil.executeService(VALID_PATH, null, String.class, HttpMethod.GET, null, true))
				.thenReturn(vtexAddress);
		
		result = repo.getAddresses(VALID_PROF_ID);
		assertThat(result, is(empty()));
	}

	
	@Test
	public void testGetEmptyAddress() throws IOException {
		vtexAddress = getVtexResponse("vtexJumboAddresses");
		when(clientUtil.executeService(VALID_PATH, null, String.class, HttpMethod.GET, null, true))
				.thenReturn(vtexAddress);
		//Null id
		List<AddressDto> result = repo.getAddresses(null);
		assertThat(result, is(empty()));
		
		//Empty id
		result = repo.getAddresses("");
		assertThat(result, is(empty()));
		
		//Without addresses
		when(clientUtil.executeService(VALID_PATH, null, String.class, HttpMethod.GET, null, true)).thenReturn(null);
		result = repo.getAddresses(VALID_PROF_ID);
		assertThat(result, is(empty()));
	}

	@Test
	public void testCreateAddress() throws JSONException, JsonProcessingException {
		when(clientUtil.executeService(eq(VALID_PATH), anyString(), eq(Object.class), eq(HttpMethod.POST), eq(true))).thenReturn(address.getAddressId());
		
		String result = repo.createAddress(VALID_PROF_ID, address);
		
		assertThat(result, is(not(nullValue())));
	}
	
	
	@Test
	public void testUpdateAddress() throws JSONException, JsonProcessingException {
		when(clientUtil.executeService(eq(VALID_PATH), anyString(), eq(Object.class), eq(HttpMethod.POST), eq(true))).thenReturn(address.getAddressId());
		repo.updateAddress(VALID_PROF_ID, address);
	}
	
	@Test
	public void testDeleteAddress() throws JSONException, JsonProcessingException {
		when(clientUtil.executeService(eq(VALID_PATH), anyString(), eq(Object.class), eq(HttpMethod.DELETE), eq(true))).thenReturn(address.getAddressId());
		repo.deleteAddress(VALID_PROF_ID, address.getAddressId());
	}
	

	private String getVtexResponse(String filename){
		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(String.format("src/test/resources/%s.json", filename))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
