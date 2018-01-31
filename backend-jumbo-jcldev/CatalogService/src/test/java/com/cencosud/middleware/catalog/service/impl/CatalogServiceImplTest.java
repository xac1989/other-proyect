package com.cencosud.middleware.catalog.service.impl;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
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

import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.CommertialOffer;
import com.cencosud.middleware.catalog.model.Image;
import com.cencosud.middleware.catalog.model.Item;
import com.cencosud.middleware.catalog.model.Product;
import com.cencosud.middleware.catalog.model.SearchInfo;
import com.cencosud.middleware.catalog.model.SearchResult;
import com.cencosud.middleware.catalog.model.Seller;
import com.cencosud.middleware.catalog.repository.CatalogRepository;
import com.cencosud.middleware.catalog.service.CatalogService;

@RunWith(MockitoJUnitRunner.class)
public class CatalogServiceImplTest {

	@InjectMocks
	CatalogService catalogService = new CatalogR1ServiceImpl();

	@Mock
	private CatalogRepository vtexRepo;

	List<Product> products;
	SearchResult product;
	
	final static String filter="/Tecnologia/Televisores", brand="", spec="LED", q="", o="OrderByTopSaleDESC",sc="1";
//	final static String q="imaco", filter="", brand="", spec="", o="OrderByTopSaleDESC";
	final static int offset=0, limit=1;
	

	@Before
	public void setUp() {
		products = createProducts();
		product = createProduct();
	}
	
	@Test
	public void searchProducts() throws CatalogServiceException{
		
		searchProductsPreconditions();
		SearchResult catalogResponse = catalogService.searchProducts(filter, brand, spec, q, o, offset, limit,sc);
		assertThat( catalogResponse.getProducts().size(), is(greaterThan(0)));
	}
	
	@Test
	public void getAllProducts() throws CatalogServiceException{
		
		getAllProductsPreconditions();
		List<Product> catalogResponse = catalogService.getAllProducts();
		assertTrue( catalogResponse != null );
	}
	
	
	private void getAllProductsPreconditions() throws CatalogServiceException{
		given(vtexRepo.getAllProducts()).willReturn(products);
	}
	
	private void searchProductsPreconditions() throws CatalogServiceException{
		given(vtexRepo.searchProducts(filter, brand, spec, q, o, offset, limit)).willReturn(product);
	}
	
	private SearchResult createProduct(){
		List<Seller> sellers = new ArrayList<>();
		CommertialOffer comm = new CommertialOffer(new BigDecimal(1259.0),new BigDecimal(2399.0),new BigDecimal(47.52),72l,true);
		Seller seller = new Seller(comm);
		sellers.add(seller);
		
		List<Image> images = new ArrayList<>();
		Image image = new Image("http://wong.vteximg.com.br/arquivos/ids/173051/LG-Televisor-LED-Full-HD-Smart-49-pulgadas-LH5700-wong-532357.jpg");
		images.add(image);
		image = new Image("http://wong.vteximg.com.br/arquivos/ids/173052/LG-Televisor-LED-Full-HD-Smart-49-pulgadas-LH5700-wong-532357_1.jpg");
		images.add(image);
		
		List<Item> items = new ArrayList<>();
		Item item = new Item(sellers, images);
		items.add(item);
		List<VtexProduct> products = new ArrayList<>();
		VtexProduct product = new VtexProduct();
		products.add(product);
		
		SearchInfo info = new SearchInfo(0,1);
		SearchResult searchResult = new SearchResult(products,info);
		
		return searchResult;
	}
	
	private List<Product> createProducts(){
		List<Item> items = new ArrayList<>();
		List<Seller> sellers = new ArrayList<>();
		CommertialOffer comm = new CommertialOffer(new BigDecimal(299.0),new BigDecimal(299.0),new BigDecimal(0.0),983l,true);
		Seller seller = new Seller(comm);
		sellers.add(seller);
		
		List<Image> images = new ArrayList<>();
		Image image = new Image("http://wongqa.vteximg.com.br/arquivos/ids/156824/Dolce-Gusto-Cafetera-Piccolo-Capsulas-PV100659-Negro-wong-542947_1.jpg");
		images.add(image);
		image = new Image("http://wongqa.vteximg.com.br/arquivos/ids/156825/Dolce-Gusto-Cafetera-Piccolo-Capsulas-PV100659-Negro-wong-542947_1.jpg");
		images.add(image);
		Item item = new Item(sellers, images);
		items.add(item);
		
		List<Product> products = new ArrayList<>();
		Product product = new Product("Dolce Gusto Cafetera Piccolo + Cápsulas PV100659 Negro",
				 "2001393", "542947", items);
		products.add(product);
		
		return products;
	}
}
