package com.cencosud.middleware.cencosud.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.cencosud.middleware.cencosud.configuration.ApplicationConfig;
import com.cencosud.middleware.cencosud.model.CencosudServiceRequest;
import com.cencosud.middleware.cencosud.model.CencosudServiceResponse;
import com.cencosud.middleware.cencosud.model.UserProfileInfo;
import com.cencosud.middleware.cencosud.service.UserLoginCencosudService;

@Service
public class UserLoginCencosudServiceImpl implements UserLoginCencosudService {

	public static final String KEY_PASS_AES = "E8f.c7e5#3$16w9G";
	
	public static final SecretKeySpec SECRET_KEY = new SecretKeySpec(KEY_PASS_AES.getBytes(), "AES");

	@Autowired
	private ApplicationConfig applicationConfig;

	@Override
	public UserProfileInfo loginCencosud(CencosudServiceRequest request) {

		try {
	    
			RestTemplate restTemplate = new RestTemplate(useApacheHttpClientWithSelfSignedSupport());
						
			MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			formData.add("client_id", applicationConfig.getServiceConfig().getClientId());
			formData.add("grant_type", "password");
			formData.add("username", request.getUserName());
			
			String password = request.getPassword();
			String encryptedPass = null;

			try {
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
				encryptedPass = Base64.encodeBase64String(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			formData.add("password", encryptedPass);

			HttpEntity<MultiValueMap<String, String>> requestForm = new HttpEntity<MultiValueMap<String, String>>(formData,
					headers);

			ResponseEntity<CencosudServiceResponse> responseEntity = restTemplate.postForEntity(
					applicationConfig.getServiceConfig().getUrlService(), requestForm, CencosudServiceResponse.class);

			if (!responseEntity.getStatusCode().equals(HttpStatus.OK))
				return null;

			CencosudServiceResponse response = responseEntity.getBody();

			headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

			headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer ".concat(response.getAccessToken()));
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			StringBuilder sb = new StringBuilder("?client_id=");
			sb.append(response.getClientId()).append("&type=g");
			HttpEntity<?> entity = new HttpEntity<>(headers);

			ResponseEntity<String> responseInfo = restTemplate.exchange(
					applicationConfig.getServiceConfig().getUrlInfoService().concat(sb.toString()), HttpMethod.GET, entity, String.class);

			if (!responseInfo.getStatusCode().equals(HttpStatus.OK))
				return null;

			JSONObject jsonObject = new JSONObject(responseInfo.getBody());

			UserProfileInfo userProfileInfo  = new UserProfileInfo();
			
			userProfileInfo.setDisplayName(jsonObject.getJSONObject("DataArea").getString("name")
					.concat(" ").concat(jsonObject.getJSONObject("DataArea").getString("last_name")));
			
			userProfileInfo.setEmail(jsonObject.getJSONObject("DataArea").getString("email"));
			
			return userProfileInfo;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static HttpComponentsClientHttpRequestFactory useApacheHttpClientWithSelfSignedSupport() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build());

	    HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		HttpComponentsClientHttpRequestFactory useApacheHttpClient = new HttpComponentsClientHttpRequestFactory();
		useApacheHttpClient.setHttpClient(httpClient);
		return useApacheHttpClient;
	}

}
