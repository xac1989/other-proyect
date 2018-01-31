package com.cencosud.mobile.login.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cencosud.mobile.login.model.LoginResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LoginService.class, HttpClients.class, HttpClientBuilder.class, CloseableHttpClient.class,
		CloseableHttpResponse.class, HttpGet.class, URI.class, HttpResponse.class })
public class LoginServiceTest {

	@Test
	public void getLoginResponse() throws Exception {
		String jsonObject = new String(Files.readAllBytes(Paths.get("src/test/resources/loginResponse.json")));
		LoginResponse loginResponse = getResponse(LoginResponse.class, jsonObject);
		Assert.assertThat(loginResponse.getUserId(),
				org.hamcrest.Matchers.equalTo("a976af51-b2a5-4371-8dd6-076337d04d41"));
		Assert.assertThat(loginResponse.getDescription(), org.hamcrest.Matchers.equalTo("Success"));
		Assert.assertThat(loginResponse.getCookies(), org.hamcrest.Matchers.notNullValue());
	}

	@SuppressWarnings("unchecked")
	private <T> T getResponse(Class<T> classResult, String jsonObject) throws Exception {

		PowerMockito.mockStatic(HttpClients.class);
		HttpClientBuilder httpClientBuilder = PowerMockito.mock(HttpClientBuilder.class);
		CloseableHttpClient closeableHttpClient = PowerMockito.mock(CloseableHttpClient.class);
		CloseableHttpResponse closeableHttpResponse = PowerMockito.mock(CloseableHttpResponse.class);
		HttpGet httpGet = Mockito.mock(HttpGet.class);
		URI uri = PowerMockito.mock(URI.class);
		HttpEntity httpEntity = Mockito.mock(HttpEntity.class);

		Mockito.when(HttpClients.custom()).thenReturn(httpClientBuilder);
		Mockito.when(httpClientBuilder.setDefaultHeaders(Matchers.anyCollection())).thenReturn(httpClientBuilder);
		Mockito.doReturn(closeableHttpClient).when(httpClientBuilder).build();
		PowerMockito.whenNew(HttpGet.class).withAnyArguments().thenReturn(httpGet);
		Mockito.when(httpGet.getURI()).thenReturn(uri);
		Mockito.when(closeableHttpClient.execute(httpGet)).thenReturn(closeableHttpResponse);
		Mockito.when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);

		InputStream stream = new ByteArrayInputStream(jsonObject.getBytes(StandardCharsets.UTF_8.name()));
		Mockito.when(httpEntity.getContent()).thenReturn(stream);

		LoginService loginService = new LoginService(
				"https://api.us.apiconnect.ibmcloud.com/supermercados-cencosud-wong-development/dev",
				"5fcf4990-1035-4f93-ab3b-1fe385af6c6a", "gX5nH3oH7fG1dG4qU2vS7kL5hJ5bS6uC3nY1fE6dI2pY6uR6hU");
		Map<String, String> parameters = new HashMap<>();
		return loginService.getResponse(classResult, "", parameters);
	}
}
