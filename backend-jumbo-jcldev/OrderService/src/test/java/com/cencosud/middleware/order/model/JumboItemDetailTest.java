package com.cencosud.middleware.order.model;

import static org.junit.Assert.assertThat;
import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class JumboItemDetailTest {

	JumboItemDetail jumboItemDetail = new JumboItemDetail();

	@Test
	public void getProductReference() {
		String productReference = "1236666";
		jumboItemDetail.setProductReference(productReference);
		assertThat(jumboItemDetail.getProductReference(), Matchers.is(productReference));
	}

	@Test
	public void getBrand() {
		String brand = "Svelty";
		jumboItemDetail.setBrand(brand);
		assertThat(jumboItemDetail.getBrand(), Matchers.is(brand));
	}

	@Test
	public void getProductId() {
		String productId = "6659";
		jumboItemDetail.setProductId(productId);
		assertThat(jumboItemDetail.getProductId(), Matchers.is(productId));
	}

	@Test
	public void getDescription() {
		String description = "Leche Semidescremada sin Lactosa Svelty 1 L";
		jumboItemDetail.setDescription(description);
		assertThat(jumboItemDetail.getDescription(), Matchers.is(description));
	}

	@Test
	public void getImage() {
		String image = "http://jumbochilehomolog.vteximg.com.br/arquivos/ids/184127-100-100/1236666.jpg";
		jumboItemDetail.setImage(image);
		assertThat(jumboItemDetail.getImage(), Matchers.is(image));
	}

	@Test
	public void getPrice() {
		BigDecimal price = BigDecimal.valueOf(1119.00);
		jumboItemDetail.setPrice(price);
		assertThat(jumboItemDetail.getPrice(), Matchers.is(price));
	}

	@Test
	public void getQuantity() {
		int quantity = 1;
		jumboItemDetail.setQuantity(quantity);
		Assert.assertThat(jumboItemDetail.getQuantity(), Matchers.is(quantity));
	}

	@Test
	public void getMeasurementUnit() {
		String measurementUnit = "un";
		jumboItemDetail.setMeasurementUnit(measurementUnit);
		Assert.assertThat(jumboItemDetail.getMeasurementUnit(), Matchers.is(measurementUnit));
	}
}
