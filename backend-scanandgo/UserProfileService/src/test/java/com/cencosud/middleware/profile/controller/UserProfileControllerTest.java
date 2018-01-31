package com.cencosud.middleware.profile.controller;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.cencosud.middleware.profile.model.Favorites;
import com.cencosud.middleware.profile.model.UserConfiguration;
import com.cencosud.middleware.profile.model.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileControllerTest {
	
	@Test
	public void client(){
		final String uri = "http://localhost:9080/UserProfileService/profile";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    
	    String plainCredentials="cencoappuser:HjztW7dQL7Y8XJ5cZbpfgMHb";
        String base64Credentials = new String(Base64.encode(plainCredentials.getBytes()));
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Basic " + base64Credentials);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    UserProfile request = new UserProfile();
	    UserConfiguration userConfiguration = new UserConfiguration();
	    Favorites favorites = new Favorites();
	    
	    request.setId("00001");
	    request.setDisplayName("Christian Castro");
	    request.setDocument("Prueba de documento");
	    request.setFavorites(favorites);
	    request.setConfigurations(userConfiguration);
	    
	    HttpEntity<UserProfile> entity = new HttpEntity<>(request, headers);
	    
	    ResponseEntity<UserProfile> result = restTemplate.exchange(uri, HttpMethod.POST, entity, UserProfile.class);
	    
	    System.out.println("Id:"+ result.getBody().getId());
	     
	}

}
