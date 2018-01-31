//package apis;
//
//import static io.restassured.RestAssured.given;
//
//import static org.hamcrest.Matchers.equalTo;
//import static utils.Constants.*;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.*;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.StringUtils;
//
//import com.cencosud.mobile.config.AppConfig;
//
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml" })
//public class ProductApiTest {
//    
//    private static final int PRD_ID_WO_STOCK = 2000072;
//
//    public static final String GET_PROD_ID = "/product/{productId}";
//
//    @Autowired
//    private AppConfig config;
//
//    private RequestSpecification requestSpec;
//
//
//    @Before
//    public void initialize() {
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//        builder.addHeader(HR_IBM_CLI_ID, config.getClientId());
//        builder.addHeader(HR_IBM_CLI_SECRET, config.getClientSecret());
//        builder.setBaseUri(config.getApiBaseUrl());
//        builder.setBasePath(config.getApiBasePath());
//        builder.setContentType(ContentType.JSON);
//        
//        if (!StringUtils.isEmpty(config.getProxyUrl()) && !StringUtils.isEmpty(config.getProxyPort())) {
//            int proxyPort = Integer.parseInt(config.getProxyPort());
//            builder.setProxy(config.getProxyUrl(), proxyPort);
//        }
//        
//        requestSpec = builder.build();
//    }
//    
//    @Test
//    public void searchExistingProduct(){
//        given(requestSpec)
//            .pathParam(PAR_PROD_ID, VAL_PROD_ID)
//        .when()
//            .get(GET_PROD_ID)
//        .then()
//            .statusCode(HTTP_ST_OK)
//            .body(JPATH_PROD_ID,equalTo(VAL_PROD_ID));
//    }
//    
//    @Test
//    public void searchNonExistentProduct(){
//        //Non existent product id 
//        given(requestSpec)
//            .pathParam(PAR_PROD_ID, INV_PROD_ID)
//        .when()
//            .get(GET_PROD_ID)
//        .then()
//            .statusCode(HTTP_ST_NOT_FND)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_NOT_FND));
//        
//        //Non numeric product id
//        given(requestSpec)
//            .pathParam(PAR_PROD_ID, ANY_STR)
//        .when()
//            .get(GET_PROD_ID)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_BAD_REQ));
//    }
//    
//    @Test
//    public void validStockInfo(){
//        given(requestSpec)
//            .pathParam(PAR_PROD_ID, PRD_ID_WO_STOCK)
//        .when()
//            .get(GET_PROD_ID)
//        .then()
//            .statusCode(HTTP_ST_OK)
//        .and()
//            .body("product.items[0].sellers[0].commertialOffer.availableQuantity", equalTo(0))
//            .body("product.items[0].sellers[0].commertialOffer.available", equalTo(false));
//    }
//    
//    @Test
//    public void validProductContract() throws IOException{
//        String schema= new String(Files.readAllBytes(Paths.get("src/test/java/apis/product-schema.json")));
//        given(requestSpec)
//            .pathParam(PAR_PROD_ID, VAL_PROD_ID)
//        .when()
//            .get(GET_PROD_ID)
//        .then()
//            .statusCode(HTTP_ST_OK)
//        .body(matchesJsonSchema(schema));
//
//    }
//
//
//}
