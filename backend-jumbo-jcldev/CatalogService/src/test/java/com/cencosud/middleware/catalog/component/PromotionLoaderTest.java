package com.cencosud.middleware.catalog.component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.catalog.client.VtexHighlight;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.client.VtexPromotion;
import com.cencosud.middleware.catalog.client.VtexSkuData;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.BusinessProperties;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.VTexClientProperties;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.catalog.dto.mapper.HighlightMapper;
import com.cencosud.middleware.catalog.model.Highlight;
import com.cencosud.middleware.catalog.model.Promotion;
import com.cencosud.middleware.catalog.service.PromotionService;

@RunWith(MockitoJUnitRunner.class)
public class PromotionLoaderTest {

	private static final String PROMO_TYPE_MX_U_PRICE = "maximum_unit_price";
	private static final String PROMO_TYPE_REGULAR = "regular";
	private static final Calendar TODAY = Calendar.getInstance();
	private static final List<String> AVAILABLE_ALL_DAYS = new ArrayList<String>();
	//private static final List<String> AVAILABLE_ONLY_TODAY = Arrays.asList(String.valueOf(TODAY.get(Calendar.DAY_OF_WEEK)-1));
	private static final List<String> AVAILABLE_NOT_TODAY = new ArrayList<>();

	static{
		AVAILABLE_NOT_TODAY.add("0");
		AVAILABLE_NOT_TODAY.add("1");
		AVAILABLE_NOT_TODAY.add("2");
		AVAILABLE_NOT_TODAY.add("3");
		AVAILABLE_NOT_TODAY.add("4");
		AVAILABLE_NOT_TODAY.add("5");
		AVAILABLE_NOT_TODAY.add("6");
		AVAILABLE_NOT_TODAY.remove(String.valueOf(TODAY.get(Calendar.DAY_OF_WEEK)-1));
	}
	private static final List<String> AVAILABLE_ALL_S_CH = new ArrayList<String>();
	private static final List<String> AVAILABLE_NOT_THIS_S_CH = Arrays.asList("2", "3", "4");
	private static final Date REMOTE_PAST_DATE = new GregorianCalendar(1983,8,21).getTime();
	private static final Date REMOTE_PAST_DATE_1_MONTH = new GregorianCalendar(1983,9,21).getTime();
	private static final Date REMOTE_FUTURE_DATE = new GregorianCalendar(3000,8,21).getTime();
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	
	@InjectMocks
	private PromotionLoader loader;
	
	@Mock
	private PromotionService promotionService;
	
	@Mock
	private ApplicationConfig applicationConfig;
	
	@Mock
	private HighlightMapper highlightMapper;
	
	private VtexProduct vtexProduct;
	private static final String SALES_CHANNEL = "1";
	private Map<String, Promotion> promotions;
	
	@Before
	public void setUp() throws Exception {
		promotions = buildPromotions();
		given(promotionService.getPromotions()).willReturn(promotions);
		given(highlightMapper.highlightToVtexHighlight(Mockito.any(Highlight.class))).willReturn(new VtexHighlight());
		
		VTexProperties vtex = new VTexProperties();
		VTexClientProperties r2 = new VTexClientProperties();
		BusinessProperties business = new BusinessProperties();
		
		Map<String, String> promotionKeys = new HashMap<>();
		promotionKeys.put("t-cenco", "cencoCard");
		promotionKeys.put("otros-medios", "otherPayments");
		promotionKeys.put("-", "info");
		promotionKeys.put("info", "info");
		business.setPromotionKeys(promotionKeys);
		
		Map<String, String> promotionTypes = new HashMap<>();
		promotionTypes.put("p", "percentage");
		promotionTypes.put("n", "nominal");
		promotionTypes.put("m", "maximumUnitPrice");
		business.setPromotionTypes(promotionTypes);
		
		Map<String, String> promotionTitles = new HashMap<>();
		promotionTitles.put("discount", "{1}% Dcto.");
		promotionTitles.put("mx", "{1}x");
		promotionTitles.put("mxn", "{1}x{2}");
		promotionTitles.put("deal", "Precio Oferta");
		promotionTitles.put("nominal", "{1}$ de dcto");
		business.setPromotionTitles(promotionTitles);
		
		Map<String, String> promotionColors = new HashMap<>();
		promotionColors.put("cencoCard", "#EC1164");
		promotionColors.put("otherPayments", "#008F23");
		promotionColors.put("info", "");
		business.setPromotionColors(promotionColors);
		
		r2.setBusiness(business );
		vtex.setR2(r2);
		given(applicationConfig.getVtex()).willReturn(vtex);
	}

	@Test
	public void testCencoPercentageValid() throws IOException{
		buildProductWithPromos("cenco");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(1)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), is(equalTo("cencoCard")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
		}
	}
	
	@Test
	public void testCencoNominalValidPriceZeroDecimals() throws IOException{
		buildProductWithPromos("cencoNominal");
		vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer().setListPrice(new BigDecimal("0.00"));
		
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(empty()));
		assertThat(promos.size(), is(equalTo(0)));
	}
	
	@Test
	public void testCencoNominalValidPriceZero() throws IOException{
		buildProductWithPromos("cencoNominal");
		vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer().setListPrice(new BigDecimal("0"));
		
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(empty()));
		assertThat(promos.size(), is(equalTo(0)));
	}
	
	@Test
	public void testOtherPaymentPercentageValid() throws IOException{
		buildProductWithPromos("other");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(1)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), is(equalTo("otherPayments")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
		}
	}
	
	@Test
	public void testSomeNewPromo() throws IOException{
		buildProductWithPromos("someNew");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(empty()));
	}
	
	@Test
	public void testCencoAndOtherPaymentsValid() throws IOException{
		buildProductWithPromos("cencoAndOther");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(2)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), isIn(Arrays.asList("cencoCard","otherPayments")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
		}
	}
	
	@Test
	public void testUknownPromo() throws IOException{
		buildProductWithPromos("uknown");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(empty()));
	}
	
	@Test
	public void testBestCencoPercentageValid() throws IOException{
		buildProductWithPromos("bestCenco");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(1)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), is(equalTo("cencoCard")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
			assertThat(p.getDiscountRate().toString(), is(equalTo("50.00")));
		}
	}
	
	@Test
	public void testCencoNominalValid() throws IOException{
		buildProductWithPromos("cencoNominal");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(1)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), is(equalTo("cencoCard")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
			assertThat(p.getDiscountRate().toString(), is(equalTo("40.88")));
		}
	}
	
	@Test
	public void testBestCencoNominalValid() throws IOException{
		buildProductWithPromos("bestCencoNominal");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(1)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), is(equalTo("cencoCard")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
			assertThat(p.getDiscountRate().toString(), is(equalTo("40.88")));
		}
	}

	@Test
	public void testCencoNotValidToday() throws IOException{
		buildProductWithPromos("cencoNotYoday");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(empty()));
		assertThat(promos.size(), is(equalTo(0)));
	}
	
	@Test
	public void testCencoNotValidDate() throws IOException{
		buildProductWithPromos("cencoInvalidDate");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(empty()));
		assertThat(promos.size(), is(equalTo(0)));
	}
	
	@Test
	public void testCencoNotThisSC() throws IOException{
		buildProductWithPromos("cencoNotThisSC");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(empty()));
		assertThat(promos.size(), is(equalTo(0)));
	}
	
	@Test
	public void testCencoTypoError() throws IOException{
		buildProductWithPromos("cencoTypo");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(1)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), is(equalTo("cencoCard")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
			assertThat(p.getDiscountRate().toString(), is(equalTo("50.00")));
		}
	}
	
	@Test
	public void testCenco1x() throws IOException{
		buildProductWithPromos("cenco1x");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(1)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), is(equalTo("cencoCard")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
		}
	}
	
	@Test
	public void testCencoOther1x() throws IOException{
		buildProductWithPromos("cencoOther1x");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(2)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), isIn(Arrays.asList("cencoCard","otherPayments")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(getListPriceFromVtexProduct().subtract(p.getDiscountAmount()))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
		}
	}
	
	@Test
	public void testInfoMxN() throws IOException{
		buildProductWithPromos("InfoMxN");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(not(empty())));
		assertThat(promos.size(), is(equalTo(1)));
		for(VtexPromotion p: promos){
			assertThat(p, is(notNullValue()));
			assertThat(p.getDiscountAmount(), is(notNullValue()));
			assertThat(p.getDiscountRate(), is(notNullValue()));
			assertThat(p.getGroup(), is(equalTo("info")));
			assertThat(p.getPromotionPrice(), is(notNullValue()));
			assertThat(p.getType(), is(notNullValue()));
			assertThat(p.getPromotionPrice(), is(equalTo(p.getSingleProductPrice().multiply(new BigDecimal(p.getQuantityM())))));
			assertThat(p.getDiscountRate(), is(equalTo(percentage(p))));
		}
	}
	
	@Test
	public void testCencoUnrecognized() throws IOException{
		buildProductWithPromos("cencoUnrecognized");
		List<VtexPromotion> promos = loader.loadPromotions(vtexProduct, SALES_CHANNEL);
		assertThat(promos, is(notNullValue()));
		assertThat(promos, is(empty()));
	}
	
	private void buildProductWithPromos(String kase) throws IOException{
		JSONObject json = new JSONObject(new String(
				Files.readAllBytes(Paths.get("src/test/resources/jumboVtexProductPromos.json"))));
		vtexProduct = new VtexProduct(json);
				
		VtexSkuData sd= new VtexSkuData();
		sd.setPromotions(getPromotionList(kase));
		sd.setUnit_multiplier(BigDecimal.ONE);
		
		ArrayList<VtexSkuData> skuData = new ArrayList<>();
		skuData.add(sd);
		
		vtexProduct.setSkuData(skuData);

	}

	
	private Map<String, Promotion> buildPromotions() {
		
		Map<String, Promotion> promos = new HashMap<>();
				
		promos.put("51", new Promotion()
				.setName("CENCO PERCENTAGE ALL_DAYS ALL_SC VALID")
				.setGroup("t-cenco")
				.setType("p")
				.setValue("30.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("30% con tarjeta cenco")
				.setDescription("Cenco Percentage")
				.setHighlight(new Highlight())
				); 	
		
		promos.put("52", new Promotion()
				.setName("OTHER PAYMENT PERCENTAGE ALL_DAYS ALL_SC VALID")
				.setGroup("otros-medios")
				.setType("p")
				.setValue("30.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("30% con todo medio de pago")
				.setDescription("Todo medio Percentage")
				.setHighlight(new Highlight())
				);
		
		promos.put("53", new Promotion()
				.setName("SOME NEW PERCENTAGE ALL_DAYS ALL_SC VALID")
				.setGroup("some-new")
				.setType("p")
				.setValue("30.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("30% con tarjeta cenco")
				.setDescription("Pago con nuez Percentage")
				.setHighlight(new Highlight())
				); 	
		
		promos.put("41", new Promotion()
				.setName("CENCO PERCENTAGE ALL_DAYS ALL_SC VALID BEST")
				.setGroup("t-cenco")
				.setType("p")
				.setValue("50.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("50% pagando con nueces")
				.setDescription("Cenco Percentage")
				); 	

		promos.put("42", new Promotion()
				.setName("CENCO Nominal ALL_DAYS ALL_SC VALID BEST")
				.setGroup("t-cenco")
				.setType("n")
				.setValue("1500.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("1500.00 con tajeta cenco")
				.setDescription("Cenco Nominal")
				); 	


		promos.put("43", new Promotion()
				.setName("CENCO PERCENTAGE NOT_TODAY ALL_SC VALID")
				.setGroup("t-cenco")
				.setType("p")
				.setValue("50.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_NOT_TODAY)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("50% con tajeta cenco")
				.setDescription("Cenco Percentage")
				); 	

		promos.put("44", new Promotion()
				.setName("CENCO PERCENTAGE ALL_DAYS ALL_SC INVALID")
				.setGroup("t-cenco")
				.setType("p")
				.setValue("50.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_PAST_DATE_1_MONTH)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("50% con tajeta cenco")
				.setDescription("Cenco Percentage")
				); 	
		
		promos.put("45", new Promotion()
				.setName("CENCO PERCENTAGE ALL_DAYS NOT_THIS_SC VALID")
				.setGroup("t-cenco")
				.setType("p")
				.setValue("50.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_NOT_THIS_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("50% con tajeta cenco")
				.setDescription("Cenco Percentage")
				); 	
		
		
		promos.put("55", new Promotion()
				.setName("CENCO PERCENTAGE ALL_DAYS ALL_SC VALID TYPO")
				.setGroup(" t-CeNcO ")
				.setType("p")
				.setValue("50.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("50% con tajeta cenco")
				.setDescription("Cenco Percentage")
				); 	


		promos.put("56", new Promotion()
				.setName("CENCO PERCENTAGE ALL_DAYS ALL_SC VALID IDENTICAL IGNORED")
				.setGroup("t-cen co")
				.setType("p")
				.setValue("50.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("50% con tajeta cenco 56")
				.setDescription("Cenco Percentage")
				); 	
		
		promos.put("57", new Promotion()
				.setName("CENCO PERCENTAGE ALL_DAYS ALL_SC VALID IGNORED")
				.setGroup("t-cenco sud")
				.setType("p")
				.setValue("50.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("50% con tajeta cenco 57")
				.setDescription("Cenco Percentage")
				); 	
		
		promos.put("58", new Promotion()
				.setName("CENCO 1x ALL_DAYS ALL_SC VALID")
				.setGroup("t-cenco")
				.setType("m")
				.setValue("1000.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("$1000 con tajeta cenco")
				.setDescription("Cenco 1x")
				.setQuantity("1")
				); 
		
		promos.put("59", new Promotion()
				.setName("OTHER 1x ALL_DAYS ALL_SC VALID")
				.setGroup("otros-medios")
				.setType("m")
				.setValue("1000.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_REGULAR)
				.setShort_description("$1000 con tajeta cualquier")
				.setDescription("Other 1x")
				.setQuantity("1")
				); 
		
		promos.put("60", new Promotion()
				.setName("INFO 1x ALL_DAYS ALL_SC VALID")
				.setGroup("info")
				.setType("m")
				.setValue("1000.00")
				.setStart(REMOTE_PAST_DATE)
				.setEnd(REMOTE_FUTURE_DATE)
				.setAvailableDays(AVAILABLE_ALL_DAYS)
				.setSalesChannels(AVAILABLE_ALL_S_CH)
				.setPromotionType(PROMO_TYPE_MX_U_PRICE)
				.setShort_description("Info 2x1")
				.setDescription("Info MxN")
				.setQuantity("2")
				); 
		
		return promos;
	}
	
	private List<String> getPromotionList(String kase) {
		
		switch(kase){
			case "cenco":
				return Arrays.asList("51");
			
			case "other":
				return Arrays.asList("52");
				
			case "someNew":
				return Arrays.asList("53");
				
			case "uknown":
				return Arrays.asList("54");
				
			case "bestCenco":
				return Arrays.asList("51", "41");
			
			case "cencoAndOther":
				return Arrays.asList("51", "52");
				
			case "cencoNominal":
				return Arrays.asList("42");
				
			case "bestCencoNominal":
				return Arrays.asList("42", "51");
				
			case "cencoNotToday":
				return Arrays.asList("43");
				
			case "cencoInvalidDate":
				return Arrays.asList("44");
				
			case "cencoNotThisSC":
				return Arrays.asList("45");
				
			case "cencoTypo":
				return Arrays.asList("55");
				
			case "cencoUnrecognized":
				return Arrays.asList("56", "57");

			case "cenco1x":
				return Arrays.asList("58");
				
			case "cencoOther1x":
				return Arrays.asList("58","59");
				
			case "InfoMxN":
				return Arrays.asList("60");
				
			default:
				return new ArrayList<>();
		}
	}
	
	private BigDecimal percentage(VtexPromotion p) {
		return p.getDiscountAmount().multiply(ONE_HUNDRED).divide(getListPriceFromVtexProduct(), 2, RoundingMode.HALF_EVEN);
	}

	private BigDecimal getListPriceFromVtexProduct() {
		return vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer().getListPrice();
	}
}
