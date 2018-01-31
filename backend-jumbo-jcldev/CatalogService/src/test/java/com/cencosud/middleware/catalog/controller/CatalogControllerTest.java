package com.cencosud.middleware.catalog.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.dto.mapper.ProductJumboMapper;
import com.cencosud.middleware.catalog.dto.mapper.ProductMapper;
import com.cencosud.middleware.catalog.dto.productdetail.ProductJumboDto;
import com.cencosud.middleware.catalog.factory.CatalogServiceFactory;
import com.cencosud.middleware.catalog.service.impl.CatalogR2ServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CatalogControllerTest {
	
	private static final String SC = "1";

	private static final String PID_10731 = "10731";

	private static final String R2 = "r2";

	@InjectMocks
	private CatalogController controller;
	
	@Mock
	private CatalogServiceFactory serviceFactory;
	
	@Mock
	private CatalogR2ServiceImpl serviceR2;
	
	private VtexProduct product;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProductDetail() throws JSONException, IOException {
		
		product = new VtexProduct(new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/jumboVtexProductSku.json")))));
		productDetailPreconditions();
		
		ProductJumboDto result = (ProductJumboDto)controller.getProductDetail(R2, PID_10731, SC);
		
		assertThat(result, is(notNullValue()));
		assertThat(result.getSkuData(), is(notNullValue()));
		assertThat(result.getSkuData().get(0).getMeasurementUnit(), is(notNullValue()));
		assertThat(result.getPrice(), is(equalTo(product.getItems().get(0).getSellers().get(0).getCommertialOffer().getPrice().multiply(product.getSkuData().get(0).getUnit_multiplier()))));
	}

	private void productDetailPreconditions() throws IOException {
		
		given(serviceR2.getProductDetail(PID_10731, SC)).willReturn(product);
		ProductMapper mapper = Mappers.getMapper(ProductJumboMapper.class);
		given(serviceR2.getProductMapper()).willReturn(mapper);
		
		given(serviceFactory.getService(R2)).willReturn(serviceR2);
	}

	@Test
	public void testProductDetailEmptySku() throws JSONException, IOException {
		
		product = new VtexProduct(new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/jumboVtextProductEmptySkuData.json")))));
		productDetailPreconditions();
		
		ProductJumboDto result = (ProductJumboDto)controller.getProductDetail(R2, PID_10731, SC);
		
		assertThat(result, is(notNullValue()));
		assertThat(result.getSkuData(), is(notNullValue()));
		assertThat(result.getSkuData().size(), is(equalTo(0)));
		assertThat(result.getPrice(), is(equalTo(new BigDecimal("0.00"))));
	}
}
