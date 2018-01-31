package com.cencosud.middleware.recommendation.service.impl;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.recommendation.exception.RecommendationServiceException;
import com.cencosud.middleware.recommendation.model.Product;
import com.cencosud.middleware.recommendation.model.Recommendation;
import com.cencosud.middleware.recommendation.repository.RecommendationRepository;
import com.cencosud.middleware.recommendation.service.RecommendationService;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationServiceImplTest {

	@InjectMocks
	RecommendationService recommService = new RecommendationServiceImpl();

	@Mock
	private RecommendationRepository recommRepository;

	Recommendation recommendation;
	
	final static String EMAIL="add@sdfs.com";
	final static String TYPE="recommended";

	@Before
	public void setUp() {
		recommendation = createRecommendation();
	}
	
	@Test
	public void getRecommendedProducts() throws RecommendationServiceException{
		
		searchProductsPreconditions();
		List<String> val = new ArrayList<>();
		List<Product> productsResponse = recommService.getRecommendedProducts(TYPE,val,val,val,"","","","","",10,EMAIL);
		assertThat(productsResponse, notNullValue());
	}
	
	@Test
	public void getRecommendedRelevantProduct() throws RecommendationServiceException{
		
		searchProductsPreconditions();
		Product productResponse = recommService.getRecommendedRelevantProduct(EMAIL);
		assertThat(productResponse, notNullValue());
	}
	
	private void searchProductsPreconditions() throws RecommendationServiceException{
		given(recommRepository.findOne(EMAIL)).willReturn(recommendation);
	}
	
	private Recommendation createRecommendation(){
		Recommendation recommendation = new Recommendation();
		
		Product starredProduct = new Product("1100", "Samsung Televisor LED Full HD Smart 40\" UN40H5303AG", 
				"http://wongqa.vteximg.com.br/arquivos/ids/156664/Televisor-Samsung-LED-FHD-Smart-40-UN40H5303AG-wong-477070.jpg",
				new BigDecimal("3799"), new BigDecimal("4500"), new BigDecimal("0"), true);
		
		List<Product> products = new ArrayList<>();
		Product product = new Product("2001080", "Samsung Televisor LED Full HD Smart 40\" UN40H5500AG", "http://wongqa.vteximg.com.br/arquivos/ids/156720/Televisor-Samsung-LED-FHD-Smart-40-pulgadas-UN40H5500AG-wong-471977.jpg",
				new BigDecimal("1099"), new BigDecimal("1799"), new BigDecimal("0"), true);
		products.add(product);
		product = new Product("2001093", "Samsung Televisor LED Full HD Smart 50\" UN50H5303AG", "http://wongqa.vteximg.com.br/arquivos/ids/156722/Televisor-Samsung-LED-FHD-Smart-50-pulgadas-UN50H5303AG-wong-477072.jpg",
				new BigDecimal("1999"), new BigDecimal("2499"), new BigDecimal("0"), true);
		products.add(product);
		product = new Product("2001094", "Samsung Televisor LED HD Smart 32\" UN32H4303AG", "http://wongqa.vteximg.com.br/arquivos/ids/156723/Televisor-Samsung-LED-HD-Smart-32-pulgadas-UN32H4303AG-wong-477068.jpg",
				new BigDecimal("1099"), new BigDecimal("1099"), new BigDecimal("0"), true);
		products.add(product);
		product = new Product("2001096", "Samsung Televisor LED Full HD Smart 46\" UN46H5303AG", "http://wongqa.vteximg.com.br/arquivos/ids/156724/Televisor-Samsung-LED-FHD-Smart-46-pulgadas-UN46H5303AG-wong-477071.jpg",
				new BigDecimal("1699"), new BigDecimal("1999"), new BigDecimal("0"), true);
		products.add(product);
		product = new Product("2001295", "Panasonic Televisor LED HD Smart 32\" TC-32AS600L", "http://wongqa.vteximg.com.br/arquivos/ids/156726/Televisor-Panasonic-LED-FHD-32-pulgadas-TC-32AS600L-wong-476554.jpg",
				new BigDecimal("1299"), new BigDecimal("1299"), new BigDecimal("0"), true);
		products.add(product);
		product = new Product("2001377", "LG Televisor LED Full HD Smart 39\" 39LB5800", "http://wongqa.vteximg.com.br/arquivos/ids/156727/Televisor-LG-LED-FHD-Smart-39-pulgadas-39LB5800-wong-480623.jpg",
				new BigDecimal("1599"), new BigDecimal("1599"), new BigDecimal("0"), true);
		products.add(product);
		product = new Product("2001378", "LG Televisor LED Ultra HD Smart 49\" 49UB8200", "http://wongqa.vteximg.com.br/arquivos/ids/156728/Televisor-LG-LED-Ultra-HD-Smart-49-pulgadas-49UB8200-wong-486717.jpg",
				new BigDecimal("3999"), new BigDecimal("3999"), new BigDecimal("0"), true);
		products.add(product);
		product = new Product("522403", "LG Televisor LED Ultra HD Smart 49\" 49UF6900", "http://wongqa.vteximg.com.br/arquivos/ids/156704/LG-Televisor-LED-Ultra-HD-Smart-WEBOS-49-pulgadas-49UF6900-wong-517095.jpg",
				new BigDecimal("900"), new BigDecimal("1000"), new BigDecimal("0"), true);
		products.add(product);
		product = new Product("523471", "LG Televisor LED Ultra HD Smart 49\" UH6230", "http://wongqa.vteximg.com.br/arquivos/ids/156716/UH6230.jpg",
				new BigDecimal("900"), new BigDecimal("1000"), new BigDecimal("0"), true);
		products.add(product);
		
		
		recommendation.setId(EMAIL);
		recommendation.setStarredProduct(starredProduct);
		recommendation.setProducts(products);
		
		return recommendation;
	}
	
}
