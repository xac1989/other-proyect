package com.cencosud.mobile.order.model;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ItemDetailR2Test {

	ItemDetailR2 itemDetail = new ItemDetailR2();

	@Test
	public void getPrice() {
		String price = "1119.00";
		itemDetail.setPrice(price);
		assertThat(itemDetail.getPrice(), Matchers.is(price));
	}

	@Test
	public void getProductId() {
		String productId = "6659";
		itemDetail.setProductId(productId);
		assertThat(itemDetail.getProductId(), Matchers.is(productId));
	}

	@Test
	public void getDescription() {
		String description = "Leche Semidescremada sin Lactosa Svelty 1 L";
		itemDetail.setDescription(description);
		assertThat(itemDetail.getDescription(), Matchers.is(description));
	}

	@Test
	public void getImage() {
		String image = "http://jumbochilehomolog.vteximg.com.br/arquivos/ids/184127-100-100/1236666.jpg";
		itemDetail.setImage(image);
		assertThat(itemDetail.getImage(), Matchers.is(image));
	}

	@Test
	public void getQuantity() {
		int quantity = 1;
		itemDetail.setQuantity(quantity);
		Assert.assertThat(itemDetail.getQuantity(), Matchers.is(quantity));
	}

	@Test
	public void getProductReference() {
		String productReference = "1236666";
		itemDetail.setProductReference(productReference);
		assertThat(itemDetail.getProductReference(), Matchers.is(productReference));
	}

	@Test
	public void getBrand() {
		String brand = "Svelty";
		itemDetail.setBrand(brand);
		assertThat(itemDetail.getBrand(), Matchers.is(brand));
	}

	@Test
	public void getMeasurementUnit() {
		String measurementUnit = "un";
		itemDetail.setMeasurementUnit(measurementUnit);
		assertThat(itemDetail.getMeasurementUnit(), Matchers.is(measurementUnit));
	}

	@Test
	public void getSkuId() {
		String skuId = "6747";
		itemDetail.setSkuId(skuId);
		assertThat(itemDetail.getSkuId(), Matchers.is(skuId));
	}

	@Test
	public void getAddToCartLink() {
		String addToCartLink = "https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=6747&qty=1&seller=1&sc=1&price=111900&cv=44b45da10d4de9e2d7516998d616756d_geral:14928EBFB66A237A646F232BA4D88119&sc=1";
		itemDetail.setAddToCartLink(addToCartLink);
		assertThat(itemDetail.getAddToCartLink(), Matchers.is(addToCartLink));
	}

	@Test
	public void getSkuData() {
		List<Object> skuData = new ArrayList<>();
		itemDetail.setSkuData(skuData);
		assertThat(itemDetail.getSkuData(), Matchers.is(skuData));
	}

	@Test
	public void getSellerId() {
		String sellerId = "1";
		itemDetail.setSellerId(sellerId);
		assertThat(itemDetail.getSellerId(), Matchers.is(sellerId));
	}

}
