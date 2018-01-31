package com.cencosud.middleware.login.dto;

import java.util.ArrayList;
import java.util.List;

import com.cencosud.middleware.login.model.UserLogin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginDto {

	private String description;
	private String userId;
	private List<CookieDto> cookies;
	
	public UserLoginDto(UserLogin userLogin) {
	
		this.description = userLogin.getDescription();
		this.userId = userLogin.getUserId();
		cookies = new ArrayList<CookieDto>();
		
		CookieDto cookieAuthCookie = new CookieDto();
		cookieAuthCookie.setPath(userLogin.getPath());
		cookieAuthCookie.setExpirationDate(userLogin.getExpirationDate());
		cookieAuthCookie.setName(userLogin.getAuthCookie().getName());
		cookieAuthCookie.setValue(userLogin.getAuthCookie().getValue());
		cookieAuthCookie.setDomain(userLogin.getDomain());
		cookies.add(cookieAuthCookie);
		
		CookieDto cookieAccountAuthCookie = new CookieDto();
		cookieAccountAuthCookie.setPath(userLogin.getPath());
		cookieAccountAuthCookie.setExpirationDate(userLogin.getExpirationDate());
		cookieAccountAuthCookie.setName(userLogin.getAccountAuthCookie().getName());
		cookieAccountAuthCookie.setValue(userLogin.getAccountAuthCookie().getValue());
		cookieAccountAuthCookie.setDomain(userLogin.getDomain());
		cookies.add(cookieAccountAuthCookie);
		
		CookieDto cookie = new CookieDto();
		cookie.setPath(userLogin.getPath());
		cookie.setExpirationDate(userLogin.getExpirationDate());
		int position = userLogin.getAccountAuthCookie().getName().indexOf('_');
		String nameCookie = userLogin.getAccountAuthCookie().getName().substring(0, position);
		cookie.setName(nameCookie);
		cookie.setValue(userLogin.getAccountAuthCookie().getValue());
		cookie.setDomain(userLogin.getDomain());
		cookies.add(cookie);
		
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CookieDto> getCookies() {
		return cookies;
	}

	public void setCookies(List<CookieDto> cookies) {
		this.cookies = cookies;
	}

	@Override
	public String toString() {
		return "{description:" + this.getDescription() + ", userId : " + this.userId + "}";
	}

}
