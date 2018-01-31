package apis;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;
import static utils.Constants.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.cencosud.mobile.config.AppConfig;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml" })
public class RecomendationsApiTest {

	@Autowired
	private AppConfig config;

	private RequestSpecification requestSpec;

	public static final String GET_RECOMM = "/recommendations";
	
	@Autowired
	private Environment environment;

	@Before
	public void initialize() {
		System.out.println("**Env:");
		for(String e: environment.getActiveProfiles()){
			System.out.println(e);
		}
		
		System.out.println(config.getApiBasePath());
		System.out.println(config.getApiBaseUrl());
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.addHeader(HR_IBM_CLI_ID, config.getClientId());
		builder.addHeader(HR_IBM_CLI_SECRET, config.getClientSecret());
		builder.setBaseUri(config.getApiBaseUrl());
		builder.setBasePath(config.getApiBasePath());
		builder.setContentType(ContentType.JSON);
		
		if (!StringUtils.isEmpty(config.getProxyUrl()) && !StringUtils.isEmpty(config.getProxyPort())) {
			int proxyPort = Integer.parseInt(config.getProxyPort());
			builder.setProxy(config.getProxyUrl(), proxyPort);
		}
		
		requestSpec = builder.build();
	}
	
	@Ignore("Must Fail because of harcoded API in order to workaround the Chaordic/VTex environments diff")
	@Test
	public void defaultRecommendationsShouldBeNotFound(){
		given(requestSpec)
		.when()
			.get(GET_RECOMM)
		.then()
			.statusCode(HTTP_ST_NOT_FND)
		.and()
			.body("metadata.results", greaterThan(0));
	}
	
	@Test
	public void recommendationsBestSellersShouldBeOk(){
		given(requestSpec)
			.param("type", "BestSellers")
		.when()
			.get(GET_RECOMM)
		.then()
			.statusCode(HTTP_ST_OK)
		.and()
			.body("metadata.results", greaterThan(0));
	}
	
	
	@Test
	public void recommendationSimiliarPrices(){
		
		given(requestSpec)
			.param("type", "Similar")
			.param("productId", "521933")
		.when()
			.get(GET_RECOMM)
		.then()
			.statusCode(HTTP_ST_OK);
	
	}
	
	@Test
	public void recommendationSimiliarToProductShouldBeOk(){
		given(requestSpec)
			.param("type", "Similar")
			.param("productId", "521933")
		.when()
			.get(GET_RECOMM)
		.then()
			.statusCode(HTTP_ST_OK)
		.and()
			.body("metadata.results", greaterThan(0));
	}
	
	@Test
	public void noResultsShouldBeOk(){
		given(requestSpec)
			.param("type", "Similar")
			.param("productId", "0000000")
		.when()
			.get(GET_RECOMM)
		.then()
			.statusCode(HTTP_ST_OK)
		.and()
			.body("metadata.results", greaterThan(0));
	}	

	@Test
	public void validRecommendationsContract() throws IOException{
		String schema= new String(Files.readAllBytes(Paths.get("src/test/java/apis/recommendations-schema.json")));
		given(requestSpec)
			.param("type", "Similar")
			.param("productId", "521933")
		.when()
			.get(GET_RECOMM)
		.then()
			.statusCode(HTTP_ST_OK)
		.and()
			.body(matchesJsonSchema(schema));

	}
}
