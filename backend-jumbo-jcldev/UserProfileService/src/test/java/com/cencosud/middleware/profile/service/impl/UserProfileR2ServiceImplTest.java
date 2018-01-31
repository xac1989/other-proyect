package com.cencosud.middleware.profile.service.impl;

import com.cencosud.middleware.profile.dto.UserPreferencePostRequest;
import com.cencosud.middleware.profile.exception.UserProfileException;
import com.cencosud.middleware.profile.model.JumboUserProfile;
import com.cencosud.middleware.profile.model.UserPreference;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.model.WongUserProfile;
import com.cencosud.middleware.profile.repository.ProfileRepository;
import com.cencosud.middleware.profile.service.UserProfileService;
import com.cencosud.middleware.profile.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileR2ServiceImplTest {

	@InjectMocks
	private UserProfileService userProfileService = new UserProfileR2ServiceImpl();

	@Mock
	private ProfileRepository profileRepository;

	private JumboUserProfile userProfile;
	private UserPreference userPreference;
	private UserPreferencePostRequest userPreferenceRequest;

	private String email = "anyela.herrera@globant.com";

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userProfile = createUserProfile();
		userPreference = createUserPreference();
		userPreferenceRequest = createUserPreferenceRequest();
	}

	@Test
	public void getRegionIdTest() {
		String region = userProfileService.getRegionId();
		assertThat("The region must be r2",region, is("r2"));
	}

	@Test
	public void findByIdTest() {
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		JumboUserProfile jumboProfile = (JumboUserProfile) userProfileService.findById(email);
		verify(profileRepository, times(1)).findById(email);
		verify(profileRepository, times(1)).getUserPreference(email);
		assertThat("Sales Channel must be the new value",jumboProfile.getDefaultSalesChannel(), is(2));
	}

	@Test
	public void findByIdWithUserPreferenceNullTest() {
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(null);
		JumboUserProfile jumboProfile = (JumboUserProfile) userProfileService.findById(email);
		verify(profileRepository, times(1)).findById(email);
		verify(profileRepository, times(1)).getUserPreference(email);
		assertThat("Sales Channel must be the default value",jumboProfile.getDefaultSalesChannel(), is(1));
	}

	@Test
	public void findByIdWithUserProfileNullTest() {
		when(profileRepository.findById(email)).thenReturn(null);
		JumboUserProfile jumboProfile = (JumboUserProfile) userProfileService.findById(email);
		verify(profileRepository, times(1)).findById(email);
		verify(profileRepository, never()).getUserPreference(email);
		assertNull("User Profile must be null", jumboProfile);
	}

	@Test
	public void findByIdWithUserIdNullTest() {
		userProfile.setUserId(null);
		when(profileRepository.findById(email)).thenReturn(userProfile);
		JumboUserProfile jumboProfile = (JumboUserProfile) userProfileService.findById(email);
		verify(profileRepository, times(1)).findById(email);
		verify(profileRepository, never()).getUserPreference(email);
		assertNull("User Id must be null",jumboProfile.getUserId());
	}

	@Test
	public void findByIdWithUserIdEmptyTest() {
		userProfile.setUserId("");
		when(profileRepository.findById(email)).thenReturn(userProfile);
		JumboUserProfile jumboProfile = (JumboUserProfile) userProfileService.findById(email);
		verify(profileRepository, times(1)).findById(email);
		verify(profileRepository, never()).getUserPreference(email);
		assertThat("User Id must be empty", jumboProfile.getUserId(), is(""));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void saveOrUpdateTest() throws UserProfileException {
		userProfileService.saveOrUpdate(new WongUserProfile());
	}

	@Test
	public void updateUserProfileWithoutEmailTest() throws UserProfileException {
		UserProfile user = userProfileService.update(userProfile, null);
		verify(profileRepository, never()).findById(any());
		verify(profileRepository, never()).updateUserProfile(any(), any());
		assertNotNull("User Profile must not be null", user);
		assertNull("User Profile must be empty",user.getId());
	}

	@Test(expected = UserProfileException.class)
	public void updateUserProfileOtherInstanceTest() throws UserProfileException {
		userProfileService.update(new WongUserProfile(), email);
	}

	@Test
	public void updateUserProfileWithoutFieldsTest() throws UserProfileException {
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(profileJumbo, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(userProfile, email);
		assertNotEquals("First Name must be different", user.getFirstName(), profileJumbo.getFirstName());
	}

	@Test
	public void updateUserProfileUpdateFirstNameTest() throws UserProfileException {
		String newName = "New Name";
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setFirstName(newName);
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(userProfile, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(profileJumbo, email);
		assertThat("The First Name must be equals",user.getFirstName(), is(newName));
		assertNotEquals("The Last Name must be different", user.getLastName(), profileJumbo.getLastName());
		assertNotEquals("The Birth Date must be different", user.getBirthDate(), profileJumbo.getBirthDate());
		assertNotEquals("The Gender must be different", user.getGender(), profileJumbo.getGender());
		assertNotEquals("The Home Phone must be different", user.getHomePhone(), profileJumbo.getHomePhone());
	}

	@Test
	public void updateUserProfileUpdateLastNameTest() throws UserProfileException {
		String newName = "New Last Name";
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setLastName(newName);
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(userProfile, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(profileJumbo, email);
		assertNotEquals("The First Name must be different",user.getFirstName(), profileJumbo.getFirstName());
		assertThat("The Last Name must be equals", user.getLastName(), is(newName));
		assertNotEquals("The Birth Date must be different", user.getBirthDate(), profileJumbo.getBirthDate());
		assertNotEquals("The Gender must be different", user.getGender(), profileJumbo.getGender());
		assertNotEquals("The Home Phone must be different", user.getHomePhone(), profileJumbo.getHomePhone());
	}

	@Test
	public void updateUserProfileUpdateBirthDateTest() throws UserProfileException {
		Date now = Calendar.getInstance().getTime();
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setBirthDate(now);
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(userProfile, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(profileJumbo, email);
		assertNotEquals("The First Name must be different",user.getFirstName(), profileJumbo.getFirstName());
		assertNotEquals("The Last Name must be different", user.getLastName(), profileJumbo.getLastName());
		assertThat("The Birth date must be equals", user.getBirthDate(), is(now));
		assertNotEquals("The Gender must be different", user.getGender(), profileJumbo.getGender());
		assertNotEquals("The Home Phone must be different", user.getHomePhone(), profileJumbo.getHomePhone());
	}

	@Test
	public void updateUserProfileUpdateMaleGenderTest() throws UserProfileException {
		String gender = "male";
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setGender(gender);
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(userProfile, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(profileJumbo, email);
		assertNotEquals("The First Name must be different",user.getFirstName(), profileJumbo.getFirstName());
		assertNotEquals("The Last Name must be different", user.getLastName(), profileJumbo.getLastName());
		assertNotEquals("The Birth Date must be different", user.getBirthDate(), profileJumbo.getBirthDate());
		assertThat("The Gender must be equals", user.getGender(), is(gender));
		assertNotEquals("The Home Phone must be different", user.getHomePhone(), profileJumbo.getHomePhone());
	}

	@Test
	public void updateUserProfileUpdateUpperCaseMaleGenderTest() throws UserProfileException {
		String gender = "MALE";
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setGender(gender);
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(userProfile, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(profileJumbo, email);
		assertNotEquals("The First Name must be different",user.getFirstName(), profileJumbo.getFirstName());
		assertNotEquals("The Last Name must be different", user.getLastName(), profileJumbo.getLastName());
		assertNotEquals("The Birth Date must be different", user.getBirthDate(), profileJumbo.getBirthDate());
		assertThat("The Gender must be equals", user.getGender(), is(gender.toLowerCase()));
		assertNotEquals("The Home Phone must be different", user.getHomePhone(), profileJumbo.getHomePhone());
	}

	@Test
	public void updateUserProfileUpdateFemaleGenderTest() throws UserProfileException {
		String gender = "female";
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setGender(gender);
		userProfile.setGender("male");
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(userProfile, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(profileJumbo, email);
		assertNotEquals("The First Name must be different",user.getFirstName(), profileJumbo.getFirstName());
		assertNotEquals("The Last Name must be different", user.getLastName(), profileJumbo.getLastName());
		assertNotEquals("The Birth Date must be different", user.getBirthDate(), profileJumbo.getBirthDate());
		assertThat("The Gender must be equals", user.getGender(), is(gender));
		assertNotEquals("The Home Phone must be different", user.getHomePhone(), profileJumbo.getHomePhone());
	}

	@Test
	public void updateUserProfileUpdateUpperCaseFemaleGenderTest() throws UserProfileException {
		String gender = "FEMALE";
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setGender(gender);
		userProfile.setGender("male");
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(userProfile, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(profileJumbo, email);
		assertNotEquals("The First Name must be different",user.getFirstName(), profileJumbo.getFirstName());
		assertNotEquals("The Last Name must be different", user.getLastName(), profileJumbo.getLastName());
		assertNotEquals("The Birth Date must be different", user.getBirthDate(), profileJumbo.getBirthDate());
		assertThat("The Gender must not change", user.getGender(), is(gender.toLowerCase()));
		assertNotEquals("The Home Phone must be different", user.getHomePhone(), profileJumbo.getHomePhone());
	}

	@Test
	public void updateUserProfileUpdateHomePhoneTest() throws UserProfileException {
		String homePhone = "+5492217321209";
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setHomePhone(homePhone);
		when(profileRepository.findById(email)).thenReturn(userProfile);
		when(profileRepository.getUserPreference(email)).thenReturn(userPreference);
		doNothing().when(profileRepository).updateUserProfile(userProfile, email);
		JumboUserProfile user = (JumboUserProfile) userProfileService.update(profileJumbo, email);
		assertNotEquals("The First Name must be different",user.getFirstName(), profileJumbo.getFirstName());
		assertNotEquals("The Last Name must be different", user.getLastName(), profileJumbo.getLastName());
		assertNotEquals("The Birth Date must be different", user.getBirthDate(), profileJumbo.getBirthDate());
		assertNotEquals("The Gender must be different", user.getGender(), profileJumbo.getGender());
		assertThat("The Home Phone must be equals", user.getHomePhone(), is(homePhone));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void deleteTest() throws UserProfileException {
		userProfileService.delete(email);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void findByIdAndFieldsTest() throws UserProfileException {
		userProfileService.findByIdAndFields(email, "");
	}

	@Test
	public void savePreferencesTest() throws UserProfileException {
		doNothing().when(profileRepository).postPreferences(userPreference);
		userProfileService.savePreferences(userPreferenceRequest);
	}

	private JumboUserProfile createUserProfile() {
		JumboUserProfile profileJumbo = new JumboUserProfile();
		profileJumbo.setUserId(email);
		profileJumbo.setDocument("156037591");
		profileJumbo.setDocumentType("RUT");
		profileJumbo.setHomePhone("+56987321209");
		profileJumbo.setFirstName("Anyi");
		profileJumbo.setLastName("Herrera Galarce");
		profileJumbo.setDefaultSalesChannel(1);
		profileJumbo.setGender("female");
		profileJumbo.setBirthDate(StringUtils.convertToDate("2010-11-01"));
		return profileJumbo;
	}

	private UserPreference createUserPreference() {
		UserPreference userPreference = new UserPreference();
		userPreference.setDefaultSalesChannel(2);
		userPreference.setUserId(email);
		return userPreference;
	}
	
	private UserPreferencePostRequest createUserPreferenceRequest(){
		UserPreferencePostRequest userPreferenceRequest = new UserPreferencePostRequest();
		userPreferenceRequest.setDefaultSalesChannel(1);
		userPreferenceRequest.setUserId(email);
		return userPreferenceRequest;
	}

}
