package com.cencosud.middleware.product.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.product.model.Product;
import com.cencosud.middleware.product.repository.ProductRepository;

/**
 * 
 * 
 * <h1>ProductMongoServiceImplTest</h1>
 * <p>
 * Test de la clase {@link ProductMongoServiceImpl}
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductMongoServiceImplTest {
	
	@Mock
	private ProductRepository mockRepository;
	
	@Mock
	private Product product;
	
	@InjectMocks
	private ProductMongoServiceImpl serviceImpl;
	
	@Captor ArgumentCaptor<List<Product>> argument;
	
	@Before
	public void setUp() throws Exception {
	    // Initialize mocks created above
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveOrUpdate_newProduct(){
		List<Product> lstProduct = new ArrayList<>(1);
		when(product.getBrandId()).thenReturn("2000240");
		when(product.getBrandName()).thenReturn("Calaf");
		when(product.getDetailUrl()).thenReturn("/malva-calaf-bolsa-250-g-banadas-en-chocolate/p");
		when(product.getEan()).thenReturn("7802220070000");
		when(product.getImageUrl()).thenReturn("http://jumbochilehomolog.vteximg.com.br/arquivos/ids/171763-100-100/269761.jpg");
		when(product.getNameComplete()).thenReturn("Malva Calaf Bolsa 250 g, Bañadas en Chocolate prueba");
		when(product.getProductDescription()).thenReturn("Encuentra lo mejor en Jumbo.cl");
		when(product.getProductId()).thenReturn(3789);
		when(product.getProductName()).thenReturn("Malva Calaf Bolsa 250 g, Bañadas en Chocolate");
		when(product.getSkuName()).thenReturn("Malva Calaf Bolsa 250 g, Bañadas en Chocolate");
		lstProduct.add(product);
		when(mockRepository.findByEANandStoreId(anyString(), anyString())).thenReturn(null);
		when(mockRepository.findByEAN(anyString())).thenReturn(null);
		when(mockRepository.save(any(Product.class))).thenReturn(product);
		
		serviceImpl.saveOrUpdate(product);
		
		verify(mockRepository, times(1)).findByEAN(anyString());
		verify(mockRepository, times(1)).save(product);
		
		verify(product, never()).setBrandId(anyString());
		verify(product, never()).setBrandName(anyString());
		verify(product, never()).setDetailUrl(anyString());
		verify(product, never()).setEan(anyString());
		verify(product, never()).setImageUrl(anyString());
		verify(product, never()).setNameComplete(anyString());
		verify(product, never()).setProductDescription(anyString());
		verify(product, never()).setProductId(anyInt());
		verify(product, never()).setProductName(anyString());
		verify(product, never()).setSkuName(anyString());
		
	}
	
	@Test
	public void saveOrUpdate_alreadyCreatedProduct(){
		List<Product> lstProduct = new ArrayList<>(1);
		when(product.getBrandId()).thenReturn("2000240");
		when(product.getBrandName()).thenReturn("Calaf");
		when(product.getDetailUrl()).thenReturn("/malva-calaf-bolsa-250-g-banadas-en-chocolate/p");
		when(product.getEan()).thenReturn("7802220070000");
		when(product.getImageUrl()).thenReturn("http://jumbochilehomolog.vteximg.com.br/arquivos/ids/171763-100-100/269761.jpg");
		when(product.getNameComplete()).thenReturn("Malva Calaf Bolsa 250 g, Bañadas en Chocolate prueba");
		when(product.getProductDescription()).thenReturn("Encuentra lo mejor en Jumbo.cl");
		when(product.getProductId()).thenReturn(3789);
		when(product.getProductName()).thenReturn("Malva Calaf Bolsa 250 g, Bañadas en Chocolate");
		when(product.getSkuName()).thenReturn("Malva Calaf Bolsa 250 g, Bañadas en Chocolate");
		lstProduct.add(product);
		when(mockRepository.findByEANandStoreId(anyString(), anyString())).thenReturn(lstProduct);
		when(mockRepository.findByEAN(anyString())).thenReturn(lstProduct);
		when(mockRepository.save(any(Product.class))).thenReturn(product);
		
		serviceImpl.saveOrUpdate(product);
		
		verify(mockRepository, times(1)).findByEAN(anyString());
		verify(mockRepository, times(1)).save(product);
		
		verify(product, never()).setBrandId(anyString());
		verify(product, never()).setBrandName(anyString());
		verify(product, never()).setDetailUrl(anyString());
		verify(product, never()).setEan(anyString());
		verify(product, never()).setImageUrl(anyString());
		verify(product, never()).setNameComplete(anyString());
		verify(product, never()).setProductDescription(anyString());
		verify(product, never()).setProductId(anyInt());
		verify(product, times(1)).setProductName(anyString());
		verify(product, never()).setSkuName(anyString());
		
	}
}
