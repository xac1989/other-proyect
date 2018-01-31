package com.cencosud.middleware.catalog.repository.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.catalog.client.VtexClient;
import com.cencosud.middleware.catalog.client.VtexHighlight;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.client.VtexPromotion;
import com.cencosud.middleware.catalog.component.PromotionLoader;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.BusinessProperties;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.VTexClientProperties;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.Pair;
import com.cencosud.middleware.catalog.model.SearchResult;
import com.cencosud.middleware.catalog.model.enums.RequestProtocolEnum;


@RunWith(MockitoJUnitRunner.class)
public class CatalogVtexR2RepositoryImplTest {

	private static final String SEARCH_PRODUCTS_URL = "/api/catalog_system/pub/products/search";

	@InjectMocks
	private CatalogVtexR2RepositoryImpl repo;
	
	@Mock
	private ApplicationConfig applicationConfig;
	
	@Mock
	private VtexClient client;
	
	@Mock
	private PromotionLoader promotionLoader;

	private List<VtexPromotion> promotions;
	 
	private static String SC = "1";
	
	private Pair<String, Map<String, String>> searchResult;
	private String detailResult;
	
	@Before
	public void setUp() throws Exception {
		BusinessProperties business = new BusinessProperties();
		business.setSourcesBaseURL("https://cencodev-catalog-service.mybluemix.net/images/");
		business.setHighlights(Arrays.asList("descuento_10pc","descuento_11pc","descuento_12pc","descuento_15pc","descuento_16pc","descuento_20pc","descuento_25pc","descuento_29pc","descuento_30pc","descuento_35pc","descuento_40pc","descuento_50pc","descuento_5pc","descuento_cencosud","flag_acumula_bono_puntos","flag_big_size","flag_come_libre","flag_descuento_10x","flag_descuento_10x8","flag_descuento_12x","flag_descuento_12x10","flag_descuento_12x8","flag_descuento_2x","flag_descuento_2x1","flag_descuento_3x","flag_descuento_3x2","flag_descuento_4x","flag_descuento_4x3","flag_descuento_5x","flag_descuento_5x4","flag_descuento_6x","flag_descuento_6x5","flag_descuento_7x","flag_descuento_7x5","flag_descuento_8x","flag_descuento_8x6","flag_descuento_9x","flag_descuento_cencosud","flag_despacho_1","flag_exclusivo_jumbo_cl","flag_jumbo_artesanal","flag_libre_de_alergenos_comunes","flag_libre_de_azucar_añadida","flag_libre_de_gluten","flag_libre_de_lactosa","flag_oferta","flag_organicos","flag_producto_exclusivo","flag_producto_jumbo","flag_producto_nuevo","flag_super_pack","flag_vegano","flag_vive_sabores"));
		VTexClientProperties vtexR2 = new VTexClientProperties();
		vtexR2.setBusiness(business);
		VTexProperties vtex = new VTexProperties();
		vtex.setR2(vtexR2);
		given(applicationConfig.getVtex()).willReturn(vtex);
		this.promotions = generatePromotions();
	}
	
	@Test
	public void productsShouldHaveKnownHighlights() throws CatalogServiceException {
		
		String method = "productsShouldHaveKnownHighlights";
		List<NameValuePair> params = generateParams("products");
		
		generateSearchResults(method);
		searchProductsPreconditions(params);
		
		SearchResult result = repo.searchProducts("", params, SC);
		for(VtexProduct product : result.getProducts()){
			assertThat(product.getHighlights().size(), is(greaterThan(0)));
			assertThat(product.getHighlights().size(), is(lessThanOrEqualTo(2)));
			for(VtexHighlight highlight : product.getHighlights()){
				assertThat(highlight.getImageAvailable(),is(equalTo(true)));
			}
		}
	}


	@Test
	public void productsShouldNotHaveHighlights() throws CatalogServiceException {
		String method = "productsShouldNotHaveHighlights";
		List<NameValuePair> params = generateParams("products");
		
		generateSearchResults(method);
		searchProductsPreconditions(params);
		
		SearchResult result = repo.searchProducts("", params, SC);
		for(VtexProduct product : result.getProducts()){
			assertThat(product.getHighlights().size(), is(equalTo(0)));
		}
	}
	
	@Test
	public void detailShouldHaveMixedKnownHighlights() throws CatalogServiceException{
		String method = "detailShouldHaveKnownHighlights";
		List<NameValuePair> params = generateParams("detail");
		
		generateSearchResults(method);
		detailPreconditions(params);
		
		VtexProduct product = repo.getProductDetail("11301", SC);
		assertThat(product.getHighlights().size(), is(greaterThan(0)));
		assertThat(product.getHighlights().size(), is(lessThanOrEqualTo(4)));
		for(VtexHighlight highlight : product.getHighlights()){
			if(applicationConfig.getVtex().getR2().getBusiness().getHighlights().indexOf(highlight.getId())>0){
				assertThat(highlight.getImageAvailable(),is(equalTo(true)));
			}else{
				assertThat(highlight.getImageAvailable(),is(equalTo(false)));
			}
		}
	}
	
	@Test
	public void detailShouldHaveMixedTruncatedMaxHighlights() throws CatalogServiceException{
		String method = "detailShouldHaveMixedTruncatedMaxHighlights";
		List<NameValuePair> params = generateParams("detail");
		
		generateSearchResults(method);
		detailPreconditions(params);
		
		VtexProduct product = repo.getProductDetail("11301", SC);
		assertThat(product.getHighlights().size(), is(greaterThan(0)));
		assertThat(product.getHighlights().size(), is(lessThanOrEqualTo(4)));
		for(VtexHighlight highlight : product.getHighlights()){
			if(applicationConfig.getVtex().getR2().getBusiness().getHighlights().indexOf(highlight.getId())>0){
				assertThat(highlight.getImageAvailable(),is(equalTo(true)));
			}else{
				assertThat(highlight.getImageAvailable(),is(equalTo(false)));
			}
		}
	}
	
	/**
	 * Preconditions
	 */
	private void searchProductsPreconditions(List<NameValuePair> params) throws CatalogServiceException{

		given(client.executeAndGetResultAsStringAndHeaders(SEARCH_PRODUCTS_URL,
				params, RequestProtocolEnum.GET)).willReturn(searchResult);
		given(promotionLoader.loadPromotions(any(VtexProduct.class), anyString())).willReturn(promotions);
	}
	
	private void detailPreconditions(List<NameValuePair> params) throws CatalogServiceException{

		given(client.executeAsString(SEARCH_PRODUCTS_URL,
				params, RequestProtocolEnum.GET)).willReturn(detailResult);
		given(promotionLoader.loadPromotions(any(VtexProduct.class), anyString())).willReturn(promotions);
	}
	
	/**
	 * Generate
	 */
	private void generateSearchResults(String method){
		String content = "";
		
		switch(method){
			case "productsShouldHaveKnownHighlights":
				content = "[{\"productId\":\"997\",\"productName\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"brand\":\"Jumbo\",\"linkText\":\"churrasco-de-asiento-jumbo-caja-700-g-24-unid-2\",\"productReference\":\"261639\",\"categoryId\":\"164\",\"clusterHighlights\":{\"135\":\"Flag Producto Jumbo\"},\"categories\":[\"/Congelados/Churrascos, Lomitos y Otros/\",\"/Congelados/\"],\"categoriesIds\":[\"/158/164/\",\"/158/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/churrasco-de-asiento-jumbo-caja-700-g-24-unid-2/p\",\"ProductData\": [{\"ProductData\":\"PD\"}],\"SkuData\": [],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[],\"description\":\"Encuentra lo mejor en Jumbo.cl\",\"items\":[{\"itemId\":\"999\",\"name\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"nameComplete\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"complementName\":\"\",\"ean\":\"7807910000099\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"261639\"}],\"measurementUnit\":\"un\",\"unitMultiplier\":1,\"images\":[{\"imageId\":\"179259\",\"imageLabel\":\"\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/179259-#width#-#height#/261639.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"Churrasco-de-Asiento-Jumbo-700-g-24-unid\\\" id=\\\"\\\" />\",\"imageUrl\":\"https://jumbochilehomolog.vteximg.com.br/arquivos/ids/179259/261639.jpg\",\"imageText\":\"Churrasco-de-Asiento-Jumbo-700-g-24-unid\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=999&qty=1&seller=1&sc=1&price=759900&cv=7e927c6db05a8b50a443f5e4a5a50e2e_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{\"0\":{\"DeliverySlaPerTypes\":[],\"Region\":null},\"1\":{\"DeliverySlaPerTypes\":[{\"TypeName\":\"Retiro en Tienda\",\"Price\":0,\"EstimatedTimeSpanToDelivery\":\"00:00:00\"}],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}},\"Installments\":[{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"Pago de Pruebas\",\"PaymentSystemGroupName\":\"custom201PaymentGroupPaymentGroup\",\"Name\":\"Pago de Pruebas à vista\"},{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"WEBPAY (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"WEBPAY (PaymentHub)  à vista\"},{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"CAT (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"CAT (PaymentHub)  à vista\"}],\"DiscountHighLight\":[],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":7599,\"ListPrice\":7599,\"PriceWithoutDiscount\":7599,\"RewardValue\":0,\"PriceValidUntil\":\"2018-08-08T19:23:26.6139893Z\",\"AvailableQuantity\":1000000,\"Tax\":0,\"DeliverySlaSamples\":[{\"DeliverySlaPerTypes\":[],\"Region\":null},{\"DeliverySlaPerTypes\":[{\"TypeName\":\"Retiro en Tienda\",\"Price\":0,\"EstimatedTimeSpanToDelivery\":\"00:00:00\"}],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}],\"GetInfoErrorMessage\":null,\"CacheVersionUsedToCallCheckout\":\"7e927c6db05a8b50a443f5e4a5a50e2e_geral:E683A9E48364C1119647A8F689D89068\"}}]}]},{\"productId\":\"11212\",\"productName\":\"Asiento Cat. V\",\"brand\":\"Jumbo\",\"linkText\":\"asiento-cat-v\",\"productReference\":\"1462090\",\"categoryId\":\"111\",\"clusterHighlights\":{\"135\":\"Flag Producto Jumbo\",\"136\":\"Flag Oferta\"},\"categories\":[\"/Carnes Rojas/Carnes de Vacuno/Cortes Tradicionales/\",\"/Carnes Rojas/Carnes de Vacuno/\",\"/Carnes Rojas/\"],\"categoriesIds\":[\"/2/109/111/\",\"/2/109/\",\"/2/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/asiento-cat-v/p\",\"ProductData\": [{\"ProductData\":\"PD\"}],\"SkuData\": [],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[],\"description\":\"Envasado al vacío\",\"items\":[{\"itemId\":\"11471\",\"name\":\"Asiento Cat. V\",\"nameComplete\":\"Asiento Cat. V\",\"complementName\":\"\",\"ean\":\"2499086000000\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"1462090\"}],\"measurementUnit\":\"kg\",\"unitMultiplier\":1.2,\"images\":[{\"imageId\":\"188441\",\"imageLabel\":\"\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/188441-#width#-#height#/1462090-KG.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"Asiento-Cat.-V\\\" id=\\\"\\\" />\",\"imageUrl\":\"https://jumbochilehomolog.vteximg.com.br/arquivos/ids/188441/1462090-KG.jpg\",\"imageText\":\"Asiento-Cat.-V\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=11471&qty=1&seller=1&sc=1&price=0&cv=fc51a3699d526429c623e3a8c1201167_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{},\"Installments\":[],\"DiscountHighLight\":[],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":0,\"ListPrice\":0,\"PriceWithoutDiscount\":0,\"RewardValue\":0,\"PriceValidUntil\":null,\"AvailableQuantity\":0,\"Tax\":0,\"DeliverySlaSamples\":[],\"GetInfoErrorMessage\":\"Code: withoutPriceRnB Status:error Message: No está más disponible el item Asiento Cat. V y el lo fue retirado del carrito\",\"CacheVersionUsedToCallCheckout\":\"fc51a3699d526429c623e3a8c1201167_geral:E683A9E48364C1119647A8F689D89068\"}}]}]}]";
				break;
			
			case "productsShouldHaveUknownHighlights":
				content = "[{\"productId\":\"997\",\"productName\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"brand\":\"Jumbo\",\"linkText\":\"churrasco-de-asiento-jumbo-caja-700-g-24-unid-2\",\"productReference\":\"261639\",\"categoryId\":\"164\",\"clusterHighlights\":{\"135\":\"Flag Producto Prueba\"},\"categories\":[\"/Congelados/Churrascos, Lomitos y Otros/\",\"/Congelados/\"],\"categoriesIds\":[\"/158/164/\",\"/158/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/churrasco-de-asiento-jumbo-caja-700-g-24-unid-2/p\",\"ProductData\": [{\"ProductData\":\"PD\"}],\"SkuData\": [],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[],\"description\":\"Encuentra lo mejor en Jumbo.cl\",\"items\":[{\"itemId\":\"999\",\"name\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"nameComplete\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"complementName\":\"\",\"ean\":\"7807910000099\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"261639\"}],\"measurementUnit\":\"un\",\"unitMultiplier\":1,\"images\":[{\"imageId\":\"179259\",\"imageLabel\":\"\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/179259-#width#-#height#/261639.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"Churrasco-de-Asiento-Jumbo-700-g-24-unid\\\" id=\\\"\\\" />\",\"imageUrl\":\"https://jumbochilehomolog.vteximg.com.br/arquivos/ids/179259/261639.jpg\",\"imageText\":\"Churrasco-de-Asiento-Jumbo-700-g-24-unid\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=999&qty=1&seller=1&sc=1&price=759900&cv=7e927c6db05a8b50a443f5e4a5a50e2e_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{\"0\":{\"DeliverySlaPerTypes\":[],\"Region\":null},\"1\":{\"DeliverySlaPerTypes\":[{\"TypeName\":\"Retiro en Tienda\",\"Price\":0,\"EstimatedTimeSpanToDelivery\":\"00:00:00\"}],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}},\"Installments\":[{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"Pago de Pruebas\",\"PaymentSystemGroupName\":\"custom201PaymentGroupPaymentGroup\",\"Name\":\"Pago de Pruebas à vista\"},{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"WEBPAY (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"WEBPAY (PaymentHub)  à vista\"},{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"CAT (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"CAT (PaymentHub)  à vista\"}],\"DiscountHighLight\":[],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":7599,\"ListPrice\":7599,\"PriceWithoutDiscount\":7599,\"RewardValue\":0,\"PriceValidUntil\":\"2018-08-08T19:23:26.6139893Z\",\"AvailableQuantity\":1000000,\"Tax\":0,\"DeliverySlaSamples\":[{\"DeliverySlaPerTypes\":[],\"Region\":null},{\"DeliverySlaPerTypes\":[{\"TypeName\":\"Retiro en Tienda\",\"Price\":0,\"EstimatedTimeSpanToDelivery\":\"00:00:00\"}],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}],\"GetInfoErrorMessage\":null,\"CacheVersionUsedToCallCheckout\":\"7e927c6db05a8b50a443f5e4a5a50e2e_geral:E683A9E48364C1119647A8F689D89068\"}}]}]},{\"productId\":\"11212\",\"productName\":\"Asiento Cat. V\",\"brand\":\"Jumbo\",\"linkText\":\"asiento-cat-v\",\"productReference\":\"1462090\",\"categoryId\":\"111\",\"clusterHighlights\":{\"135\":\"Flag Producto Prueba\",\"136\":\"Flag Oferta Nueva\"},\"categories\":[\"/Carnes Rojas/Carnes de Vacuno/Cortes Tradicionales/\",\"/Carnes Rojas/Carnes de Vacuno/\",\"/Carnes Rojas/\"],\"categoriesIds\":[\"/2/109/111/\",\"/2/109/\",\"/2/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/asiento-cat-v/p\",\"ProductData\": [{\"ProductData\":\"PD\"}],\"SkuData\": [],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[],\"description\":\"Envasado al vacío\",\"items\":[{\"itemId\":\"11471\",\"name\":\"Asiento Cat. V\",\"nameComplete\":\"Asiento Cat. V\",\"complementName\":\"\",\"ean\":\"2499086000000\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"1462090\"}],\"measurementUnit\":\"kg\",\"unitMultiplier\":1.2,\"images\":[{\"imageId\":\"188441\",\"imageLabel\":\"\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/188441-#width#-#height#/1462090-KG.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"Asiento-Cat.-V\\\" id=\\\"\\\" />\",\"imageUrl\":\"https://jumbochilehomolog.vteximg.com.br/arquivos/ids/188441/1462090-KG.jpg\",\"imageText\":\"Asiento-Cat.-V\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=11471&qty=1&seller=1&sc=1&price=0&cv=fc51a3699d526429c623e3a8c1201167_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{},\"Installments\":[],\"DiscountHighLight\":[],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":0,\"ListPrice\":0,\"PriceWithoutDiscount\":0,\"RewardValue\":0,\"PriceValidUntil\":null,\"AvailableQuantity\":0,\"Tax\":0,\"DeliverySlaSamples\":[],\"GetInfoErrorMessage\":\"Code: withoutPriceRnB Status:error Message: No está más disponible el item Asiento Cat. V y el lo fue retirado del carrito\",\"CacheVersionUsedToCallCheckout\":\"fc51a3699d526429c623e3a8c1201167_geral:E683A9E48364C1119647A8F689D89068\"}}]}]}]";
				break;
				
			case "productsShouldNotHaveHighlights":
				content = "[{\"productId\":\"997\",\"productName\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"brand\":\"Jumbo\",\"linkText\":\"churrasco-de-asiento-jumbo-caja-700-g-24-unid-2\",\"productReference\":\"261639\",\"categoryId\":\"164\",\"clusterHighlights\":{},\"categories\":[\"/Congelados/Churrascos, Lomitos y Otros/\",\"/Congelados/\"],\"categoriesIds\":[\"/158/164/\",\"/158/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/churrasco-de-asiento-jumbo-caja-700-g-24-unid-2/p\",\"ProductData\": [{\"ProductData\":\"PD\"}],\"SkuData\": [],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[],\"description\":\"Encuentra lo mejor en Jumbo.cl\",\"items\":[{\"itemId\":\"999\",\"name\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"nameComplete\":\"Churrasco de Asiento Jumbo 700 g, 24 unid\",\"complementName\":\"\",\"ean\":\"7807910000099\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"261639\"}],\"measurementUnit\":\"un\",\"unitMultiplier\":1,\"images\":[{\"imageId\":\"179259\",\"imageLabel\":\"\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/179259-#width#-#height#/261639.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"Churrasco-de-Asiento-Jumbo-700-g-24-unid\\\" id=\\\"\\\" />\",\"imageUrl\":\"https://jumbochilehomolog.vteximg.com.br/arquivos/ids/179259/261639.jpg\",\"imageText\":\"Churrasco-de-Asiento-Jumbo-700-g-24-unid\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=999&qty=1&seller=1&sc=1&price=759900&cv=7e927c6db05a8b50a443f5e4a5a50e2e_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{\"0\":{\"DeliverySlaPerTypes\":[],\"Region\":null},\"1\":{\"DeliverySlaPerTypes\":[{\"TypeName\":\"Retiro en Tienda\",\"Price\":0,\"EstimatedTimeSpanToDelivery\":\"00:00:00\"}],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}},\"Installments\":[{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"Pago de Pruebas\",\"PaymentSystemGroupName\":\"custom201PaymentGroupPaymentGroup\",\"Name\":\"Pago de Pruebas à vista\"},{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"WEBPAY (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"WEBPAY (PaymentHub)  à vista\"},{\"Value\":7599,\"InterestRate\":0,\"TotalValuePlusInterestRate\":7599,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"CAT (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"CAT (PaymentHub)  à vista\"}],\"DiscountHighLight\":[],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":7599,\"ListPrice\":7599,\"PriceWithoutDiscount\":7599,\"RewardValue\":0,\"PriceValidUntil\":\"2018-08-08T19:23:26.6139893Z\",\"AvailableQuantity\":1000000,\"Tax\":0,\"DeliverySlaSamples\":[{\"DeliverySlaPerTypes\":[],\"Region\":null},{\"DeliverySlaPerTypes\":[{\"TypeName\":\"Retiro en Tienda\",\"Price\":0,\"EstimatedTimeSpanToDelivery\":\"00:00:00\"}],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}],\"GetInfoErrorMessage\":null,\"CacheVersionUsedToCallCheckout\":\"7e927c6db05a8b50a443f5e4a5a50e2e_geral:E683A9E48364C1119647A8F689D89068\"}}]}]},{\"productId\":\"11212\",\"productName\":\"Asiento Cat. V\",\"brand\":\"Jumbo\",\"linkText\":\"asiento-cat-v\",\"productReference\":\"1462090\",\"categoryId\":\"111\",\"clusterHighlights\":{},\"categories\":[\"/Carnes Rojas/Carnes de Vacuno/Cortes Tradicionales/\",\"/Carnes Rojas/Carnes de Vacuno/\",\"/Carnes Rojas/\"],\"categoriesIds\":[\"/2/109/111/\",\"/2/109/\",\"/2/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/asiento-cat-v/p\",\"ProductData\": [{\"ProductData\":\"PD\"}],\"SkuData\": [],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[],\"description\":\"Envasado al vacío\",\"items\":[{\"itemId\":\"11471\",\"name\":\"Asiento Cat. V\",\"nameComplete\":\"Asiento Cat. V\",\"complementName\":\"\",\"ean\":\"2499086000000\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"1462090\"}],\"measurementUnit\":\"kg\",\"unitMultiplier\":1.2,\"images\":[{\"imageId\":\"188441\",\"imageLabel\":\"\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/188441-#width#-#height#/1462090-KG.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"Asiento-Cat.-V\\\" id=\\\"\\\" />\",\"imageUrl\":\"https://jumbochilehomolog.vteximg.com.br/arquivos/ids/188441/1462090-KG.jpg\",\"imageText\":\"Asiento-Cat.-V\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=11471&qty=1&seller=1&sc=1&price=0&cv=fc51a3699d526429c623e3a8c1201167_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{},\"Installments\":[],\"DiscountHighLight\":[],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":0,\"ListPrice\":0,\"PriceWithoutDiscount\":0,\"RewardValue\":0,\"PriceValidUntil\":null,\"AvailableQuantity\":0,\"Tax\":0,\"DeliverySlaSamples\":[],\"GetInfoErrorMessage\":\"Code: withoutPriceRnB Status:error Message: No está más disponible el item Asiento Cat. V y el lo fue retirado del carrito\",\"CacheVersionUsedToCallCheckout\":\"fc51a3699d526429c623e3a8c1201167_geral:E683A9E48364C1119647A8F689D89068\"}}]}]}]";
				break;
				
			case "detailShouldHaveKnownHighlights":
				detailResult = "[{\"productId\":\"11301\",\"productName\":\"Bebida Doble Zero Lima Limón 500 cc\",\"brand\":\"Jumbo\",\"linkText\":\"bebida-doble-zero-lima-limon-500-cc\",\"productReference\":\"1634512\",\"categoryId\":\"151\",\"clusterHighlights\":{\"135\":\"Flag Producto Jumbo\"},\"categories\":[\"/Bebidas y Jugos/\"],\"categoriesIds\":[\"/151/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/bebida-doble-zero-lima-limon-500-cc/p\",\"ProductData\":[\"{\\\"ref_id\\\":\\\"1634512\\\",\\\"allow_notes\\\":true,\\\"allow_substitute\\\":true,\\\"cart_limit\\\":\\\"0\\\",\\\"brandName\\\":\\\"Jumbo\\\",\\\"measurement_unit\\\":\\\"un\\\",\\\"unit_multiplier\\\":1,\\\"measurement_unit_un\\\":\\\"Lt\\\",\\\"unit_multiplier_un\\\":0.5,\\\"measurement_unit_selector\\\":false}\"],\"SkuData\":[\"{\\\"11560\\\":{\\\"ref_id\\\":\\\"1634512\\\",\\\"cart_limit\\\":0,\\\"allow_notes\\\":true,\\\"allow_substitute\\\":true,\\\"measurement_unit\\\":\\\"un\\\",\\\"unit_multiplier\\\":1,\\\"promotions\\\":[\\\"32\\\"],\\\"measurement_unit_un\\\":\\\"Lt\\\",\\\"unit_multiplier_un\\\":0.5,\\\"measurement_unit_selector\\\":false,\\\"release_data\\\":{\\\"date_release\\\":\\\"13-06-2017 17:22\\\",\\\"date_release_end\\\":\\\"13-07-2017 17:22\\\",\\\"is_new\\\":false}}}\"],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[\"ProductData\",\"SkuData\"],\"description\":\"Bebida Doble Zero:\\n0% Sodio\\n0% Azúcar\",\"items\":[{\"itemId\":\"11560\",\"name\":\"Bebida Doble Zero Lima Limón 500 cc\",\"nameComplete\":\"Bebida Doble Zero Lima Limón 500 cc\",\"complementName\":\"\",\"ean\":\"7807910019107\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"1634512\"}],\"measurementUnit\":\"un\",\"unitMultiplier\":1.0000,\"images\":[{\"imageId\":\"188655\",\"imageLabel\":\"Principal\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/188655-#width#-#height#/1634512.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"1634512-1\\\" id=\\\"\\\" />\",\"imageUrl\":\"http://jumbochilehomolog.vteximg.com.br/arquivos/ids/188655/1634512.jpg\",\"imageText\":\"1634512-1\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=11560&qty=1&seller=1&sc=1&price=41175&cv=335a7fd9c44acbfc79681aa574b0d216_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{\"0\":{\"DeliverySlaPerTypes\":[],\"Region\":null},\"1\":{\"DeliverySlaPerTypes\":[],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}},\"Installments\":[{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"Pago de Pruebas\",\"PaymentSystemGroupName\":\"custom201PaymentGroupPaymentGroup\",\"Name\":\"Pago de Pruebas à vista\"},{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"WEBPAY (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"WEBPAY (PaymentHub)  à vista\"},{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"CAT (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"CAT (PaymentHub)  à vista\"}],\"DiscountHighLight\":[{\"<Name>k__BackingField\":\"Promoción Globant\"}],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":411.75,\"ListPrice\":549.0,\"PriceWithoutDiscount\":549.0,\"RewardValue\":0.0,\"PriceValidUntil\":\"2018-06-23T14:00:00Z\",\"AvailableQuantity\":1000000,\"Tax\":0.0,\"DeliverySlaSamples\":[{\"DeliverySlaPerTypes\":[],\"Region\":null},{\"DeliverySlaPerTypes\":[],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}],\"GetInfoErrorMessage\":null,\"CacheVersionUsedToCallCheckout\":\"335a7fd9c44acbfc79681aa574b0d216_geral:E683A9E48364C1119647A8F689D89068\"}}]}]}]";
				break;
			
			case "detailShouldHaveUknownHighlights":
				detailResult = "[{\"productId\":\"11301\",\"productName\":\"Bebida Doble Zero Lima Limón 500 cc\",\"brand\":\"Jumbo\",\"linkText\":\"bebida-doble-zero-lima-limon-500-cc\",\"productReference\":\"1634512\",\"categoryId\":\"151\",\"clusterHighlights\":{\"135\":\"Flag Producto Test\"},\"categories\":[\"/Bebidas y Jugos/\"],\"categoriesIds\":[\"/151/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/bebida-doble-zero-lima-limon-500-cc/p\",\"ProductData\":[\"{\\\"ref_id\\\":\\\"1634512\\\",\\\"allow_notes\\\":true,\\\"allow_substitute\\\":true,\\\"cart_limit\\\":\\\"0\\\",\\\"brandName\\\":\\\"Jumbo\\\",\\\"measurement_unit\\\":\\\"un\\\",\\\"unit_multiplier\\\":1,\\\"measurement_unit_un\\\":\\\"Lt\\\",\\\"unit_multiplier_un\\\":0.5,\\\"measurement_unit_selector\\\":false}\"],\"SkuData\":[\"{\\\"11560\\\":{\\\"ref_id\\\":\\\"1634512\\\",\\\"cart_limit\\\":0,\\\"allow_notes\\\":true,\\\"allow_substitute\\\":true,\\\"measurement_unit\\\":\\\"un\\\",\\\"unit_multiplier\\\":1,\\\"promotions\\\":[\\\"32\\\"],\\\"measurement_unit_un\\\":\\\"Lt\\\",\\\"unit_multiplier_un\\\":0.5,\\\"measurement_unit_selector\\\":false,\\\"release_data\\\":{\\\"date_release\\\":\\\"13-06-2017 17:22\\\",\\\"date_release_end\\\":\\\"13-07-2017 17:22\\\",\\\"is_new\\\":false}}}\"],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[\"ProductData\",\"SkuData\"],\"description\":\"Bebida Doble Zero:\\n0% Sodio\\n0% Azúcar\",\"items\":[{\"itemId\":\"11560\",\"name\":\"Bebida Doble Zero Lima Limón 500 cc\",\"nameComplete\":\"Bebida Doble Zero Lima Limón 500 cc\",\"complementName\":\"\",\"ean\":\"7807910019107\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"1634512\"}],\"measurementUnit\":\"un\",\"unitMultiplier\":1.0000,\"images\":[{\"imageId\":\"188655\",\"imageLabel\":\"Principal\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/188655-#width#-#height#/1634512.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"1634512-1\\\" id=\\\"\\\" />\",\"imageUrl\":\"http://jumbochilehomolog.vteximg.com.br/arquivos/ids/188655/1634512.jpg\",\"imageText\":\"1634512-1\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=11560&qty=1&seller=1&sc=1&price=41175&cv=335a7fd9c44acbfc79681aa574b0d216_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{\"0\":{\"DeliverySlaPerTypes\":[],\"Region\":null},\"1\":{\"DeliverySlaPerTypes\":[],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}},\"Installments\":[{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"Pago de Pruebas\",\"PaymentSystemGroupName\":\"custom201PaymentGroupPaymentGroup\",\"Name\":\"Pago de Pruebas à vista\"},{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"WEBPAY (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"WEBPAY (PaymentHub)  à vista\"},{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"CAT (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"CAT (PaymentHub)  à vista\"}],\"DiscountHighLight\":[{\"<Name>k__BackingField\":\"Promoción Globant\"}],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":411.75,\"ListPrice\":549.0,\"PriceWithoutDiscount\":549.0,\"RewardValue\":0.0,\"PriceValidUntil\":\"2018-06-23T14:00:00Z\",\"AvailableQuantity\":1000000,\"Tax\":0.0,\"DeliverySlaSamples\":[{\"DeliverySlaPerTypes\":[],\"Region\":null},{\"DeliverySlaPerTypes\":[],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}],\"GetInfoErrorMessage\":null,\"CacheVersionUsedToCallCheckout\":\"335a7fd9c44acbfc79681aa574b0d216_geral:E683A9E48364C1119647A8F689D89068\"}}]}]}]";
				break;
				
			case "detailShouldHaveMixedTruncatedMaxHighlights":
				detailResult = "[{\"productId\":\"11301\",\"productName\":\"Bebida Doble Zero Lima Limón 500 cc\",\"brand\":\"Jumbo\",\"linkText\":\"bebida-doble-zero-lima-limon-500-cc\",\"productReference\":\"1634512\",\"categoryId\":\"151\",\"clusterHighlights\":{\"135\":\"Flag Producto Jumbo\",\"136\":\"Flag Libre de Gluten\",\"137\":\"Flag Producto Exclusivo\"},\"categories\":[\"/Bebidas y Jugos/\"],\"categoriesIds\":[\"/151/\"],\"link\":\"https://jumbochilehomolog.vtexcommercestable.com.br/bebida-doble-zero-lima-limon-500-cc/p\",\"ProductData\":[\"{\\\"ref_id\\\":\\\"1634512\\\",\\\"allow_notes\\\":true,\\\"allow_substitute\\\":true,\\\"cart_limit\\\":\\\"0\\\",\\\"brandName\\\":\\\"Jumbo\\\",\\\"measurement_unit\\\":\\\"un\\\",\\\"unit_multiplier\\\":1,\\\"measurement_unit_un\\\":\\\"Lt\\\",\\\"unit_multiplier_un\\\":0.5,\\\"measurement_unit_selector\\\":false}\"],\"SkuData\":[\"{\\\"11560\\\":{\\\"ref_id\\\":\\\"1634512\\\",\\\"cart_limit\\\":0,\\\"allow_notes\\\":true,\\\"allow_substitute\\\":true,\\\"measurement_unit\\\":\\\"un\\\",\\\"unit_multiplier\\\":1,\\\"promotions\\\":[\\\"32\\\"],\\\"measurement_unit_un\\\":\\\"Lt\\\",\\\"unit_multiplier_un\\\":0.5,\\\"measurement_unit_selector\\\":false,\\\"release_data\\\":{\\\"date_release\\\":\\\"13-06-2017 17:22\\\",\\\"date_release_end\\\":\\\"13-07-2017 17:22\\\",\\\"is_new\\\":false}}}\"],\"Configuraciones\":[\"ProductData\",\"SkuData\"],\"allSpecifications\":[\"ProductData\",\"SkuData\"],\"description\":\"Bebida Doble Zero:\\n0% Sodio\\n0% Azúcar\",\"items\":[{\"itemId\":\"11560\",\"name\":\"Bebida Doble Zero Lima Limón 500 cc\",\"nameComplete\":\"Bebida Doble Zero Lima Limón 500 cc\",\"complementName\":\"\",\"ean\":\"7807910019107\",\"referenceId\":[{\"Key\":\"RefId\",\"Value\":\"1634512\"}],\"measurementUnit\":\"un\",\"unitMultiplier\":1.0000,\"images\":[{\"imageId\":\"188655\",\"imageLabel\":\"Principal\",\"imageTag\":\"<img src=\\\"~/arquivos/ids/188655-#width#-#height#/1634512.jpg\\\" width=\\\"#width#\\\" height=\\\"#height#\\\" alt=\\\"1634512-1\\\" id=\\\"\\\" />\",\"imageUrl\":\"http://jumbochilehomolog.vteximg.com.br/arquivos/ids/188655/1634512.jpg\",\"imageText\":\"1634512-1\"}],\"sellers\":[{\"sellerId\":\"1\",\"sellerName\":\"Jumbo Chile Homolog\",\"addToCartLink\":\"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=11560&qty=1&seller=1&sc=1&price=41175&cv=335a7fd9c44acbfc79681aa574b0d216_geral:E683A9E48364C1119647A8F689D89068&sc=1\",\"sellerDefault\":true,\"commertialOffer\":{\"DeliverySlaSamplesPerRegion\":{\"0\":{\"DeliverySlaPerTypes\":[],\"Region\":null},\"1\":{\"DeliverySlaPerTypes\":[],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}},\"Installments\":[{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"Pago de Pruebas\",\"PaymentSystemGroupName\":\"custom201PaymentGroupPaymentGroup\",\"Name\":\"Pago de Pruebas à vista\"},{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"WEBPAY (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"WEBPAY (PaymentHub)  à vista\"},{\"Value\":412.0,\"InterestRate\":0.0,\"TotalValuePlusInterestRate\":412.0,\"NumberOfInstallments\":1,\"PaymentSystemName\":\"CAT (PaymentHub) \",\"PaymentSystemGroupName\":\"PaymentHubPaymentGroup\",\"Name\":\"CAT (PaymentHub)  à vista\"}],\"DiscountHighLight\":[{\"<Name>k__BackingField\":\"Promoción Globant\",\"<Name>k__BackingFields\":\"Descuento 20% - Nuggets y apanados Sadia\"}],\"GiftSkuIds\":[],\"Teasers\":[],\"BuyTogether\":[],\"Price\":411.75,\"ListPrice\":549.0,\"PriceWithoutDiscount\":549.0,\"RewardValue\":0.0,\"PriceValidUntil\":\"2018-06-23T14:00:00Z\",\"AvailableQuantity\":1000000,\"Tax\":0.0,\"DeliverySlaSamples\":[{\"DeliverySlaPerTypes\":[],\"Region\":null},{\"DeliverySlaPerTypes\":[],\"Region\":{\"IsPersisted\":true,\"IsRemoved\":false,\"Id\":1,\"Name\":\"Santiago\",\"CountryCode\":\"CHL\",\"ZipCode\":\"8320000\",\"CultureInfoName\":\"arn-CL\"}}],\"GetInfoErrorMessage\":null,\"CacheVersionUsedToCallCheckout\":\"335a7fd9c44acbfc79681aa574b0d216_geral:E683A9E48364C1119647A8F689D89068\"}}]}]}]";
				break;
				
			default:
		}
		
		HashMap<String, String> headers = new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
		{
			put("resources","0-9/2");
			put("x-vtex-operation-id","769b71ef-777e-4c0c-9969-622505d12435");
			put("no","-VERK4E5DIF8");
			put("Expires","-1");
			put("X-VTEX-Cache-Status-Janus-ApiCache","EXPIRED");
			put("X-VTEX-Cache-Status-Janus-Edge","MISS");
			put("X-Track","stable");
			put("X-Powered-by-VTEX-Janus-ApiCache","v1.3.9");
			put("X-Powered-by-VTEX-Janus-Edge","v1.38.0");
			put("X-VTEX-Janus-Router-Backend-App","portal-v1.4.778-stable+1420");
			put("Content-Type","application/json");
			put("charset","utf-8");
		}};
		
		
		searchResult = new Pair<String, Map<String, String>>(content, headers);
	}
	
	private List<NameValuePair> generateParams(String searchType){
		List<NameValuePair> params = new ArrayList<>();
		
		switch(searchType){
			case "detail":
				params.add(new BasicNameValuePair("fq","productId:11301"));
				params.add(new BasicNameValuePair("sc","1"));
				params.add(new BasicNameValuePair("_from","0"));
				params.add(new BasicNameValuePair("_to","49"));
				break;
		
			case "products":
				params.add(new BasicNameValuePair("ft","Asiento"));
				params.add(new BasicNameValuePair("from","0"));
				break;
		}
		
		return params;
	}
	
	private List<VtexPromotion> generatePromotions() {
		List<VtexPromotion> promotions = new ArrayList<>();
		return promotions;
	}
	
}
