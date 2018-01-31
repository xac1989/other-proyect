package com.cencosud.middleware.profile.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.profile.configuration.ApplicationConfig;
import com.cencosud.middleware.profile.dto.UserPreferencePostRequest;
import com.cencosud.middleware.profile.dto.WongUserProfileDto;
import com.cencosud.middleware.profile.exception.UserProfileException;
import com.cencosud.middleware.profile.model.Configuration;
import com.cencosud.middleware.profile.model.Favorites;
import com.cencosud.middleware.profile.model.JumboUserProfile;
import com.cencosud.middleware.profile.model.UserConfiguration;
import com.cencosud.middleware.profile.model.UserPreference;
import com.cencosud.middleware.profile.model.WongUserProfile;
import com.cencosud.middleware.profile.repository.ProfileRepository;
import com.cencosud.middleware.profile.repository.UserProfileRepository;
import com.cencosud.middleware.profile.service.UserProfileService;
import com.cencosud.middleware.profile.utils.StringUtils;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileR1ServiceImplTest {

	@InjectMocks
	UserProfileService userProfileService = new UserProfileR1ServiceImpl();

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Mock
	private ProfileRepository profileRepository;

	@Mock
	private UserProfileRepository userProfileRepository;
	
	@Mock
	private ApplicationConfig config;

	WongUserProfile userProfile;
	UserPreference userPreference;

	String email = "anyela.herrera@globant.com";

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userProfile = createUserProfile();
		userPreference = createUserPreference();
	}

	@Test
	public void findUserProfileById() throws UserProfileException {

		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		when(userProfileRepository.findOne(email)).thenReturn(userProfile);

		WongUserProfile wongProfile = (WongUserProfile) userProfileService.findById(email);

		verify(userProfileRepository, times(1)).findOne(email);
		assertThat(wongProfile, is(notNullValue()));

	}

	@Test
	public void updateUserProfileOtherInstance() throws UserProfileException {

		JumboUserProfile userProfile = new JumboUserProfile();

		exception.expect(UserProfileException.class);

		WongUserProfile wongProfile = (WongUserProfile) userProfileService.update(userProfile, email);

	}

	@Test
	public void updateUserProfile() throws UserProfileException {

		when(userProfileRepository.findOne(email)).thenReturn(userProfile);

		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

		WongUserProfile wongProfile = (WongUserProfile) userProfileService.update(userProfile, email);

		assertThat(wongProfile, is(notNullValue()));

	}
	
	@Test
	public void updateUserProfileEmptyId() throws UserProfileException {
		userProfile.setId("");
		
		exception.expect(UserProfileException.class);
		
		WongUserProfile wongProfile = (WongUserProfile) userProfileService.update(userProfile, email);
		
	}

	@Test
	public void updateUserProfileInvalidDoc() throws UserProfileException {

		when(userProfileRepository.findOne(email)).thenReturn(userProfile);

		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);
		
		when(config.getDocumentValidateRegex()).thenReturn("^[0-9]{7,8}$");

		exception.expect(UserProfileException.class);

		userProfile.setDocument("Xdf");
		WongUserProfile wongProfile = (WongUserProfile) userProfileService.update(userProfile, email);

	}
	
	@Test
	public void updateUserProfileValidDoc() throws UserProfileException {

		when(userProfileRepository.findOne(email)).thenReturn(userProfile);

		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);
		
		when(config.getDocumentValidateRegex()).thenReturn("^[0-9]{7,8}$");

		userProfile.setDocument("12345678");
		WongUserProfile wongProfile = (WongUserProfile) userProfileService.update(userProfile, email);

	}
	

	@Test
	public void findByIdAndFields() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(userProfile);

		String fields = "displayName";
		WongUserProfileDto wongProfile = (WongUserProfileDto) userProfileService.findByIdAndFields(email, fields);

		assertThat(wongProfile, is(notNullValue()));
	}
	
	@Test
	public void findByIdAndFieldsEmpty() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(userProfile);

		String fields = "";
		WongUserProfileDto wongProfile = (WongUserProfileDto) userProfileService.findByIdAndFields(email, fields);

		assertThat(wongProfile, is(notNullValue()));
	}

	@Test
	public void saveOrUpdate() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(userProfile);
		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

		WongUserProfile wongProfile = (WongUserProfile) userProfileService.saveOrUpdate(userProfile);

		assertThat(wongProfile, is(notNullValue()));
	}
	
	@Test
	public void saveOrUpdateNullData() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(userProfile);
		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);
		userProfile.setFullName(null);
		userProfile.setPhone(null);
		userProfile.setSex(null);
		WongUserProfile wongProfile = (WongUserProfile) userProfileService.saveOrUpdate(userProfile);

		assertThat(wongProfile, is(notNullValue()));
	}

	@Test
	public void saveOrUpdateEmptyId() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(userProfile);
		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

		userProfile.setId("");
		WongUserProfile wongProfile = (WongUserProfile) userProfileService.saveOrUpdate(userProfile);

		assertThat(wongProfile, is(notNullValue()));
	}

	@Test
	public void saveOrUpdateModified() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(userProfile);
		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

		userProfile.setModified(true);
		WongUserProfile wongProfile = (WongUserProfile) userProfileService.saveOrUpdate(userProfile);

		assertThat(wongProfile, is(notNullValue()));
	}

	@Test
	public void saveOrUpdateNewUser() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(null);
		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

		WongUserProfile wongProfile = (WongUserProfile) userProfileService.saveOrUpdate(userProfile);

		assertThat(wongProfile, is(notNullValue()));
	}

	@Test
	public void saveOrUpdateWithoutConfig() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(null);
		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

		userProfile.setConfigurations(null);
		WongUserProfile wongProfile = (WongUserProfile) userProfileService.saveOrUpdate(userProfile);

		assertThat(wongProfile, is(notNullValue()));
	}

	@Test
	public void saveOrUpdateWithoutNotif() throws UserProfileException {
		when(userProfileRepository.findOne(email)).thenReturn(null);
		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

		userProfile.getConfigurations().setLocalNotifications(null);
		WongUserProfile wongProfile = (WongUserProfile) userProfileService.saveOrUpdate(userProfile);

		assertThat(wongProfile, is(notNullValue()));
	}

	@Test
	public void delete() throws UserProfileException {

		doNothing().when(userProfileRepository).delete(email);
		userProfileService.delete(email);

	}

	@Test
	public void getRegionId() throws UserProfileException {

		String region = userProfileService.getRegionId();

		assertThat(region, is(notNullValue()));
	}

	@Test
	public void savePreferences() throws UserProfileException {

		UserPreferencePostRequest request = new UserPreferencePostRequest();

		exception.expect(UnsupportedOperationException.class);

		userProfileService.savePreferences(request);
	}

	private WongUserProfile createUserProfile() {
		WongUserProfile profileWong = new WongUserProfile();
		profileWong.setId(email);
		profileWong.setDisplayName("Marita");
		profileWong.setFullName("Marita Perez Cotapo");
		profileWong.setBirthDate(StringUtils.convertToDate("2010-10-05"));
		profileWong.setSex("F");
		profileWong.setPhone("+56912345678");
		// Map<String, String> attributes = new HashMap<>();
		// profileWong.setAttributes(attributes);
		Favorites favorites = new Favorites();
		List<String> categories = new ArrayList<>();
		categories.add("1000133");
		categories.add("1000145");
		favorites.setCategories(categories);
		profileWong.setFavorites(favorites);

		UserConfiguration configurations = new UserConfiguration();
		List<Configuration> localNotifications = new ArrayList<>();
		Configuration configuration = new Configuration();
		localNotifications.add(configuration);
		configurations.setLocalNotifications(localNotifications);
		profileWong.setConfigurations(configurations);

		return profileWong;
	}

	private UserPreference createUserPreference() {
		UserPreference userPreference = new UserPreference();
		userPreference.setDefaultSalesChannel(1);
		userPreference.setUserId("anyela.herrera@globant.com");
		return userPreference;
	}

}
