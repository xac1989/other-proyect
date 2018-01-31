/*
package apis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utils.Constants.HR_IBM_CLI_ID;
import static utils.Constants.HR_IBM_CLI_SECRET;
import static utils.Constants.HTTP_ST_OK;

import static utils.Constants.VAL_PROD_ID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.cencosud.mobile.config.AppConfig;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml" })
public class CategoriesApiTest {

	@Autowired
	private AppConfig config;

	private RequestSpecification requestSpec;


	@Before
	public void initialize() {
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

	
	@Test
	public void shouldReturnOkStatus(){
		given(requestSpec)
		.when()
			.get("categories")
		.then()
			.statusCode(HTTP_ST_OK);
	}
	
	
	
}
*/