//package apis;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//import static utils.Constants.ANY_STR;
//import static utils.Constants.HR_IBM_CLI_ID;
//import static utils.Constants.HR_IBM_CLI_SECRET;
//import static utils.Constants.HR_PREFIX_PAGING_BY_10;
//import static utils.Constants.HR_RESOURCES;
//import static utils.Constants.HTTP_BAD_REQ;
//import static utils.Constants.HTTP_ST_BAD_REQ;
//import static utils.Constants.HTTP_ST_OK;
//import static utils.Constants.HTTP_ST_PARCIAL;
//import static utils.Constants.INT_ONE_NEG;
//import static utils.Constants.INT_OVER_MAX_PGN;
//import static utils.Constants.INV_STR_ZERO_NEG;
//import static utils.Constants.PAR_LIMIT;
//import static utils.Constants.PAR_OFFSET;
//import static utils.Constants.PAR_ORDER;
//import static utils.Constants.PAR_PROD_ID;
//import static utils.Constants.PAR_QUERY;
//import static utils.Constants.RESP_ERROR_MSG;
//import static utils.Utils.randomOption;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
//import java.io.IOException;
//import java.math.BigInteger;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
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
///*
// * Testing /products/search Endpoint
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml" })
//public class ProductsApiTest {
//    
//    public static final String GET_PROD_SEARCH = "/products/search";
//    
//    @Autowired
//    private AppConfig config;
//    
//    private RequestSpecification requestSpec;
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
//
//    @Test
//    public void searchAll(){
//        given(requestSpec)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_PARCIAL);
//    }
//
//    @Test()
//    public void searchWithoutResults() {
//        given(requestSpec)
//            .param(PAR_QUERY, ANY_STR)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_OK)
//        .and()
//            .header(HR_RESOURCES,startsWith(HR_PREFIX_PAGING_BY_10));
//    }
//    
//    @Test
//    public void searchBadCriteria(){
//        given(requestSpec)
//            .param(PAR_QUERY, "?")
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_BAD_REQ));
//    }
//    
//    /**
//     * Ordering Tests
//     */
//    
//    @Test
//    public void searchWithValidOrderingOption(){
//        given(requestSpec)
//            .param(PAR_ORDER, randomOption())
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_PARCIAL);
//    }
//    
//    @Test
//    public void searchWithInvalidOrderingOption(){
//        given(requestSpec)
//            .param(PAR_ORDER, ANY_STR)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_BAD_REQ));
//    }
//    
//    /**
//     * Paging Tests
//     */
//    
//    @Test
//    public void searchWithoutPaging() {
//        given(requestSpec)
//            .param(PAR_QUERY, "s6")
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_OK);
//    }
//
//    @Test
//    public void searchWithPaging() {
//        given(requestSpec)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_PARCIAL);
//    }
//    
//    @Test
//    public void searchWithValidPaging(){
//        given(requestSpec)
//            .param(PAR_OFFSET, BigInteger.ZERO)
//            .param(PAR_LIMIT, BigInteger.TEN)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_PARCIAL)
//            .header(HR_RESOURCES,org.hamcrest.Matchers.startsWith(HR_PREFIX_PAGING_BY_10));
//    }
//    
//    @Test
//    public void searchWithInvalidNegativePaging(){
//        //Invalid negative value on limit
//        given(requestSpec)
//            .param(PAR_OFFSET, BigInteger.ZERO)
//            .param(PAR_LIMIT, BigInteger.TEN.intValue()*INT_ONE_NEG)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_BAD_REQ));
//        
//        //Invalid negative zero value on offset
//        given(requestSpec)
//            .param(PAR_OFFSET, INV_STR_ZERO_NEG)
//            .param(PAR_LIMIT, BigInteger.TEN)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_BAD_REQ));
//        
//        //invalid negative zero value on offset and negative value on limit
//        given(requestSpec)
//            .param(PAR_OFFSET, INV_STR_ZERO_NEG)
//            .param(PAR_LIMIT, BigInteger.TEN.intValue()*INT_ONE_NEG)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_BAD_REQ));
//        
//        //invalid negative value on offset and negative zero value on limit
//        given(requestSpec)
//            .param(PAR_OFFSET, BigInteger.TEN.intValue()*INT_ONE_NEG)
//            .param(PAR_LIMIT, INV_STR_ZERO_NEG)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_BAD_REQ));
//        
//        //invalid literal value on offset
//        given(requestSpec)
//            .param(PAR_OFFSET, ANY_STR)
//            .param(PAR_LIMIT, BigInteger.TEN)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ);
//        
//        //invalid literal value on limit
//        given(requestSpec)
//            .param(PAR_OFFSET, BigInteger.ZERO)
//            .param(PAR_LIMIT, ANY_STR)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ);
//        
//        //invalid literal values on both
//        given(requestSpec)
//            .param(PAR_OFFSET, ANY_STR)
//            .param(PAR_LIMIT, ANY_STR)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ);
//        
//        
//        //invalid over max 50 on limit  
//        given(requestSpec)
//            .param(PAR_OFFSET, BigInteger.ZERO)
//            .param(PAR_LIMIT, INT_OVER_MAX_PGN)
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_BAD_REQ)
//        .and()
//            .body(RESP_ERROR_MSG, equalTo(HTTP_BAD_REQ));
//    }
//    
//    @Test
//    public void validStockInfo(){
//        given(requestSpec)
//            .param(PAR_QUERY, "cocina")
//            .param(PAR_ORDER, "OrderByPriceASC")
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_OK)
//        .and()
//            .body("products[2].items[0].sellers[0].commertialOffer.availableQuantity", equalTo(0))
//            .body("products[2].items[0].sellers[0].commertialOffer.available", equalTo(false))
//        .and()
//            .body("products[0].items[0].sellers[0].commertialOffer.availableQuantity", greaterThan(0) )
//            .body("products[0].items[0].sellers[0].commertialOffer.available", equalTo(true));
//    }
//
//    
//    @Test
//    public void shouldReturnProductsByCategoryFilter(){
//        given(requestSpec)
//            .param("filter", "/tecnologia/televisores")
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_PARCIAL);
//    }
//    
//    @Test
//    public void validProductsContract() throws IOException{
//        String schema= new String(Files.readAllBytes(Paths.get("src/test/java/apis/products-schema.json")));
//        given(requestSpec)
//            .param(PAR_QUERY, "tv")
//        .when()
//            .get(GET_PROD_SEARCH)
//        .then()
//            .statusCode(HTTP_ST_PARCIAL)
//        .body(matchesJsonSchema(schema));
//
//    }
//
//    
//}