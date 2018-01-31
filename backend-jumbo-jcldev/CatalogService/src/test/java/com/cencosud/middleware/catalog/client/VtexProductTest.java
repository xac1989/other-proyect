package com.cencosud.middleware.catalog.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;


public class VtexProductTest {

	private static final String EMPTY_STR = "";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldHaveNutritionalFlags() {
		JSONObject fromObj = new JSONObject(getJSONObjectForNutritionFlags("shouldHaveNutritionalFlags"));
		VtexProduct vtexProduct = new VtexProduct(fromObj);
		Assert.assertNotNull("Should not return null", vtexProduct.getNutritionalFlags());
		Assert.assertEquals("Should have 4 nutritional flags", Arrays.asList(1,2,3,4), vtexProduct.getNutritionalFlags());
	}

	@Test
	public void shouldHaveOneNutritionalFlags() {
		JSONObject fromObj = new JSONObject(getJSONObjectForNutritionFlags("shouldHaveOneNutritionalFlags"));
		VtexProduct vtexProduct = new VtexProduct(fromObj);
		Assert.assertNotNull("Should not return null", vtexProduct.getNutritionalFlags());
		Assert.assertEquals("Should have 1 nutritional flags", 1, vtexProduct.getNutritionalFlags().size());
	}
	
	@Test
	public void shouldNotHaveNutritionalFlags() {
		JSONObject fromObj = new JSONObject(getJSONObjectForNutritionFlags("shouldNotHaveNutritionalFlags"));
		VtexProduct vtexProduct = new VtexProduct(fromObj);
		Assert.assertNotNull("Should not return null", vtexProduct.getNutritionalFlags());
		Assert.assertEquals("Should have none nutritional flags",0, vtexProduct.getNutritionalFlags().size());
	}	
	
	@Test
	public void shouldNotReturnNull() {
		JSONObject fromObj = new JSONObject(getJSONObjectForNutritionFlags("shouldNotReturnNull"));
		VtexProduct vtexProduct = new VtexProduct(fromObj);
		Assert.assertNotNull("Should not return null", vtexProduct.getNutritionalFlags());
		Assert.assertEquals("Should have none nutritional flags",0, vtexProduct.getNutritionalFlags().size());
	}	
	
	@Test
	public void shouldIgnoreUknownNutritionalFlags() {
		JSONObject fromObj = new JSONObject(getJSONObjectForNutritionFlags("shouldIgnoreUknownNutritionalFlags"));
		VtexProduct vtexProduct = new VtexProduct(fromObj);
		Assert.assertNotNull("Should not return null", vtexProduct.getNutritionalFlags());
		Assert.assertEquals("Should have 3 nutritional flags", 3, vtexProduct.getNutritionalFlags().size());
	}
	
	@Test
	public void shouldSupportNullOnImages() throws JSONException, IOException{
		JSONObject fromObj = new JSONObject(getJSONObject("jumboVtextProductNullImageLabel"));
		VtexProduct vtexProduct = new VtexProduct(fromObj);
		Assert.assertNotNull("Should not return null", vtexProduct.getNutritionalFlags());
		Assert.assertEquals("Should have image label as empty String", EMPTY_STR, vtexProduct.getItems().get(0).getImages().get(0).getImageLabel());
	}
	
	@Test
	public void shouldSupportEmptySkus() throws JSONException, IOException{
		JSONObject fromObj = new JSONObject(getJSONObject("jumboVtextProductEmptySkuData"));
		VtexProduct vtexProduct = new VtexProduct(fromObj);
		Assert.assertNotNull("Should not return null", vtexProduct.getNutritionalFlags());
		Assert.assertEquals("Should have empty skuData field", true, vtexProduct.getSkuData().isEmpty());
	}
	
	private String getJSONObject(String fileCase) throws IOException{
		
		String filePath = "src/test/resources/[[FILE_NAME]].json".replace("[[FILE_NAME]]", fileCase);
		
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	private String getJSONObjectForNutritionFlags(String kase){
		StringBuilder jsonStr = new StringBuilder();
		
		jsonStr.append("{'productId':'374','productName':'7up 2 L','brand':'7up','linkText':'bebida-7up-2-l-botella-desechable','productReference':'355153','categoryId':'160','clusterHighlights':{},'categories':['/Bebidas y Jugos/Bebidas Gaseosas/Bebidas/','/Bebidas y Jugos/Bebidas Gaseosas/','/Bebidas y Jugos/'],'categoriesIds':['/151/157/160/','/151/157/','/151/'],'link':'https://jumbochilehomolog.vtexcommercestable.com.br/bebida-7up-2-l-botella-desechable/p','Configuraciones':['ProductData','SkuData'],'Porci\u00F3n':['100 / 200'],'Porciones por envase':['3'],");
		
		switch(kase){
			case "shouldHaveNutritionalFlags":
				jsonStr.append("'Flag Nutricional':['Alto en Grasas Saturadas','Alto en Sodio','Alto en Az\u00FAcares','Alto en Calor\u00EDas'],");
			break;
			case "shouldHaveOneNutritionalFlags":
				jsonStr.append("'Flag Nutricional':['Alto en Calor\u00EDas'],");
			break;
			case "shouldNotHaveNutritionalFlags":
				jsonStr.append("'Flag Nutricional':[],");
			case "shouldNotReturnNull":
				jsonStr.append(EMPTY_STR);
			break;
			case "shouldIgnoreUknownNutritionalFlags":
				jsonStr.append("'Flag Nutricional':['Alto en Grasas Saturadas','Alto en Sodio','Alto en Az\u00FAcares', 'Alto en Gases'],");
			break;
		}
		
		jsonStr.append("'Energ\u00EDa (kcal)':['100 / 200'],'Proteinas (g)':['100 / 200'],'Grasa Total (g)':['100 / 200'],'- Grasa Saturada (g)':['100 / 200'],'- Grasa Monoinsat (g)':['100 / 200'],'- Grasa Poliinsat (g)':['100 / 200'],'- Colesterol (mg)':['100 / 200'],'Hidratos de Carbono disponibles (g)':['100 / 200'],'- Almid\u00F3n (g)':['100 / 200'],'- Az\u00FAcares (g)':['100 / 200'],'Sodio (mg)':['100 / 200'],'Calcio (mg)':['100 / 200'],'Informaci\u00F3n Nutricional (por 100 g / por porci\u00F3n)':['Porci\u00F3n','Porciones por envase','Flag Nutricional','Energ\u00EDa (kcal)','Proteinas (g)','Grasa Total (g)','- Grasa Saturada (g)','- Grasa Monoinsat (g)','- Grasa Poliinsat (g)','- Colesterol (mg)','Hidratos de Carbono disponibles (g)','- Almid\u00F3n (g)','- Az\u00FAcares (g)','Sodio (mg)','Calcio (mg)'],'allSpecifications':['Porci\u00F3n','Porciones por envase','Energ\u00EDa (kcal)','Proteinas (g)','Grasa Total (g)','- Grasa Saturada (g)','- Grasa Monoinsat (g)','- Grasa Poliinsat (g)','- Colesterol (mg)','Hidratos de Carbono disponibles (g)','- Almid\u00F3n (g)','- Az\u00FAcares (g)','Sodio (mg)','Calcio (mg)'],'description':'Encuentra lo mejor en Jumbo.cl','items':[{'itemId':'376','name':'7up 2 L','nameComplete':'7up 2 L','complementName':'Descripci\u00F3n','ean':'7801620016203','referenceId':[{'Key':'RefId','Value':'355153'}],'measurementUnit':'un','unitMultiplier':1,'images':[{'imageId':'178660','imageLabel':'','imageTag':'','imageUrl':'http://jumbochilehomolog.vteximg.com.br/arquivos/ids/178660/355153.jpg','imageText':'7up-2-L'},{'imageId':'188522','imageLabel':'','imageTag':'','imageUrl':'http://jumbochilehomolog.vteximg.com.br/arquivos/ids/188522/jumbo_icon_152.png','imageText':'jumbo_icon_152'}],'sellers':[{'sellerId':'1','sellerName':'Jumbo Chile Homolog','addToCartLink':'https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=376&qty=1&seller=1&sc=1&price=149000&cv=0dbb8256754bcb08e27fe723e5d426ab_geral:DA398E26A58D912D9BF7526C639331E2&sc=1','sellerDefault':true,'commertialOffer':{'DeliverySlaSamplesPerRegion':{'0':{'DeliverySlaPerTypes':[],'Region':null},'1':{'DeliverySlaPerTypes':[{'TypeName':'Retiro en Tienda','Price':0,'EstimatedTimeSpanToDelivery':'00:00:00'}],'Region':{'IsPersisted':true,'IsRemoved':false,'Id':1,'Name':'Santiago de Chile','CountryCode':'CHL','ZipCode':'8320000','CultureInfoName':'arn-CL'}}},'Installments':[{'Value':1490,'InterestRate':0,'TotalValuePlusInterestRate':1490,'NumberOfInstallments':1,'PaymentSystemName':'Pago de Pruebas','PaymentSystemGroupName':'custom201PaymentGroupPaymentGroup','Name':'Pago de Pruebas \u00E0 vista'},{'Value':1490,'InterestRate':0,'TotalValuePlusInterestRate':1490,'NumberOfInstallments':1,'PaymentSystemName':'WEBPAY (PaymentHub) ','PaymentSystemGroupName':'PaymentHubPaymentGroup','Name':'WEBPAY (PaymentHub)  \u00E0 vista'},{'Value':1490,'InterestRate':0,'TotalValuePlusInterestRate':1490,'NumberOfInstallments':1,'PaymentSystemName':'CAT (PaymentHub) ','PaymentSystemGroupName':'PaymentHubPaymentGroup','Name':'CAT (PaymentHub)  \u00E0 vista'}],'DiscountHighLight':[],'GiftSkuIds':[],'Teasers':[],'BuyTogether':[],'Price':1490,'ListPrice':1490,'PriceWithoutDiscount':1490,'RewardValue':0,'PriceValidUntil':'2018-07-21T15:43:08.208726Z','AvailableQuantity':1000000,'Tax':0,'DeliverySlaSamples':[{'DeliverySlaPerTypes':[],'Region':null},{'DeliverySlaPerTypes':[{'TypeName':'Retiro en Tienda','Price':0,'EstimatedTimeSpanToDelivery':'00:00:00'}],'Region':{'IsPersisted':true,'IsRemoved':false,'Id':1,'Name':'Santiago de Chile','CountryCode':'CHL','ZipCode':'8320000','CultureInfoName':'arn-CL'}}],'GetInfoErrorMessage':null,'CacheVersionUsedToCallCheckout':'0dbb8256754bcb08e27fe723e5d426ab_geral:DA398E26A58D912D9BF7526C639331E2'}}]}]}");
		
		return jsonStr.toString();
	}
}
