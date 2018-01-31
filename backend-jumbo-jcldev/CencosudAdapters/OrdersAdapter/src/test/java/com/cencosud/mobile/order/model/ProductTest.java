package com.cencosud.mobile.order.model;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Product
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductTest {
	private static Logger logger = Logger.getLogger(ProductTest.class.getName());

	Product product;

	@Before
	public void setUp() {
		product = new Product();
		logger.info(product.toString());
	}

	@Test
	public void getSkuId() {
		String skuId = "6747";
		product.setSkuId(skuId);
		assertThat(product.getSkuId(), Matchers.is(skuId));
	}

	@Test
	public void getProductId() {

		String productId = "6659";
		product.setProductId(productId);
		assertThat(product.getProductId(), Matchers.is(productId));
	}

	@Test
	public void getAddToCartLink() {
		String addToCartLink = "https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=6747&qty=1&seller=1&sc=1&price=111900&cv=44b45da10d4de9e2d7516998d616756d_geral:14928EBFB66A237A646F232BA4D88119&sc=1";
		product.setAddToCartLink(addToCartLink);
		assertThat(product.getAddToCartLink(), Matchers.is(addToCartLink));
	}

	@Test
	public void getSalesChannel() {
		String salesChannel = "1";
		product.setSalesChannel(salesChannel);
		assertThat(product.getSalesChannel(), Matchers.is(salesChannel));
	}

	@Test
	public void getSkuData() {
		List<Object> skuData = new ArrayList<>();
		product.setSkuData(skuData);
		assertThat(product.getSkuData(), Matchers.is(skuData));
	}

	@Test
	public void getSellerId() {
		String sellerId = "1";
		product.setSellerId(sellerId);
		assertThat(product.getSellerId(), Matchers.is(sellerId));
	}

}
