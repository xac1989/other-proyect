package com.cencosud.mobile.login.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.mobile.login.model.LoginResponse;
import com.cencosud.mobile.login.model.Profile;
import com.cencosud.mobile.login.model.UserMigration;
import com.cencosud.mobile.login.model.UserProfileResponse;
import com.cencosud.mobile.login.service.impl.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class JumboClLoginAdapterTest {

	@InjectMocks
	JumboClLoginAdapter jumboClLoginAdapter = new JumboClLoginAdapter();

	@Mock
	LoginService loginService = Mockito.mock(LoginService.class);

	@Mock
	JumboClLoginConfiguration jumboClLoginConfiguration = Mockito.mock(JumboClLoginConfiguration.class);

	LoginResponse loginResponse;

	UserProfileResponse userProfileResponse;

	@Before
	public void setUp() {
		loginResponse = getLoginResponse();
		userProfileResponse = getUserProfileResponse();
	}

	private UserProfileResponse getUserProfileResponse() {
		Profile profile = new Profile();
		profile.setFullName("Test name");
		profile.setId("27bcb92d-2682-4655-8d93-40022d5186c0");

		UserProfileResponse userProfileResponse = new UserProfileResponse();
		userProfileResponse.setProfile(profile);
		return userProfileResponse;
	}

	private LoginResponse getLoginResponse() {
		LoginResponse loginResponse = new LoginResponse();

		Map<String, String> cookie = new HashMap<>();
		cookie.put("name", "VtexIdclientAutCookie_jumbochilehomolog");
		cookie.put("value", "ew0KICAia2lkIjogIjIwYWRiNmUyLTIzYTEtNGRkNi0");
		cookie.put("domain", ".jumbochilehomolog.vtexcommercestable.com.br");
		cookie.put("path", "/");
		cookie.put("expirationDate", "2017-10-03 03:40:18");

		List<Map<String, String>> cookies = new ArrayList<>();
		cookies.add(cookie);
		loginResponse.setCookies(cookies);
		loginResponse.setUserId("27bcb92d-2682-4655-8d93-40022d5186c0");
		loginResponse.setDescription("Success");
		return loginResponse;
	}

	@Test
	public void validateUserNameEmail() throws Exception {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", "engelbert.quiroz@globant.com");
		credential.put("password", "Cencodev1");

		LoginService loginService = Mockito.mock(LoginService.class);

		Mockito.doReturn(loginService).when(jumboClLoginConfiguration).getLoginService();
		Mockito.doReturn(loginResponse).when(loginService).getLoginResponse(Matchers.anyString(), Matchers.anyString());
		Mockito.doReturn(userProfileResponse).when(loginService).getUserProfileResponse(Matchers.anyString());

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(true));
	}

	@Test
	public void validateInvalidRut() throws Exception {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", "11111111");
		credential.put("password", "Cencodev1");

		LoginService loginService = Mockito.mock(LoginService.class);

		Mockito.doReturn(loginService).when(jumboClLoginConfiguration).getLoginService();
		Mockito.doReturn(loginResponse).when(loginService).getLoginResponse(Matchers.anyString(), Matchers.anyString());
		Mockito.doReturn(userProfileResponse).when(loginService).getUserProfileResponse(Matchers.anyString());
		Mockito.doReturn(null).when(loginService).getUserMigration(Matchers.anyString());

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(false));
	}

	@Test
	public void emptyCredentials() {
		boolean isValid = jumboClLoginAdapter.validateCredentials(new HashMap<String, Object>());
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(false));
	}

	@Test
	public void passwordNull() {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", "engelbert.quiroz@globant.com");
		credential.put("password", null);

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(false));
	}

	@Test
	public void userNameNull() {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", null);
		credential.put("password", "Cencodev1");

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(false));
	}

	@Test
	public void userNameEmpty() {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", "");
		credential.put("password", "Cencodev1");

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(false));
	}

	@Test
	public void passwordEmpty() {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", "engelbert.quiroz@globant.com");
		credential.put("password", "");

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(false));
	}

	@Test
	public void needMigrationFalse() throws Exception {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", "11111111");
		credential.put("password", "Cencodev1");

		UserMigration userMigration = new UserMigration();
		userMigration.setEmail("engelbert.quiroz@globant.com");
		userMigration.setCode(1);
		userMigration.setNeedsMigration(false);
		userMigration.setMessage(null);

		LoginService loginService = Mockito.mock(LoginService.class);

		Mockito.doReturn(loginService).when(jumboClLoginConfiguration).getLoginService();
		Mockito.doReturn(loginResponse).when(loginService).getLoginResponse(Matchers.anyString(), Matchers.anyString());
		Mockito.doReturn(userProfileResponse).when(loginService).getUserProfileResponse(Matchers.anyString());
		Mockito.doReturn(userMigration).when(loginService).getUserMigration(Matchers.anyString());

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(true));
	}

	@Test
	public void needMigrationTrue() throws Exception {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", "11111111");
		credential.put("password", "Cencodev1");

		UserMigration userMigration = new UserMigration();
		userMigration.setEmail("engelbert.quiroz@globant.com");
		userMigration.setCode(1);
		userMigration.setNeedsMigration(true);

		LoginService loginService = Mockito.mock(LoginService.class);

		Mockito.doReturn(loginService).when(jumboClLoginConfiguration).getLoginService();
		Mockito.doReturn(loginResponse).when(loginService).getLoginResponse(Matchers.anyString(), Matchers.anyString());
		Mockito.doReturn(userProfileResponse).when(loginService).getUserProfileResponse(Matchers.anyString());
		Mockito.doReturn(userMigration).when(loginService).getUserMigration(Matchers.anyString());

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(true));
	}

	@Test
	public void emailNullUserMigrationFalse() throws Exception {
		Map<String, Object> credential = new HashMap<>();
		credential.put("username", "11111111");
		credential.put("password", "Cencodev1");

		UserMigration userMigration = new UserMigration();
		userMigration.setCode(1);

		LoginService loginService = Mockito.mock(LoginService.class);

		Mockito.doReturn(loginService).when(jumboClLoginConfiguration).getLoginService();
		Mockito.doReturn(loginResponse).when(loginService).getLoginResponse(Matchers.anyString(), Matchers.anyString());
		Mockito.doReturn(userProfileResponse).when(loginService).getUserProfileResponse(Matchers.anyString());
		Mockito.doReturn(userMigration).when(loginService).getUserMigration(Matchers.anyString());

		boolean isValid = jumboClLoginAdapter.validateCredentials(credential);
		Assert.assertThat(isValid, org.hamcrest.Matchers.equalTo(false));
	}
}
