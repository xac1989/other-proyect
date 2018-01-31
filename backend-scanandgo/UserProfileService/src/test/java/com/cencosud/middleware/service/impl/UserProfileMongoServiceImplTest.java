package com.cencosud.middleware.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import com.cencosud.middleware.profile.model.UserConfiguration;
import com.cencosud.middleware.profile.model.UserProfile;
import com.cencosud.middleware.profile.repository.UserProfileRepository;
import com.cencosud.middleware.profile.service.impl.UserProfileMongoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileMongoServiceImplTest {

    @Mock
    private UserProfileRepository mockRepository;

    @Mock
    private UserProfile userProfile;

	@InjectMocks
	private UserProfileMongoServiceImpl serviceImpl;

	@Before
	public void setUp() throws Exception {
	    // Initialize mocks created above
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSave_newProfile() {
		when(userProfile.getId()).thenReturn("1");
		when(userProfile.getDisplayName()).thenReturn("testUser");
		when(userProfile.getDocumentType()).thenReturn("RUT");
		when(userProfile.getDocument()).thenReturn("123");
		when(userProfile.getRutPuntosCencosud()).thenReturn("235711");
		when(mockRepository.findOne(Matchers.<Example<UserProfile>>any())).thenReturn(null);
		when(mockRepository.save(any(UserProfile.class))).thenReturn(userProfile);

		serviceImpl.save(userProfile);

		verify(mockRepository, times(1)).findOne(Matchers.<Example<UserProfile>>any()); 
		verify(mockRepository, times(1)).save(userProfile); 
		verify(userProfile, never()).setDisplayName(any(String.class));
		verify(userProfile, never()).setDocumentType(any(String.class));
		verify(userProfile, never()).setDocument(any(String.class));
		verify(userProfile, never()).setRutPuntosCencosud(any(String.class));
		verify(userProfile, times(1)).setConfigurations(any(UserConfiguration.class)); 
	}

	@Test
	public void testSave_alreadyCreatedProfile() {
		when(userProfile.getId()).thenReturn("1");
		when(userProfile.getDisplayName()).thenReturn("testUser");
		when(userProfile.getDocument()).thenReturn("123");
		when(userProfile.getRutPuntosCencosud()).thenReturn("235711");
		when(mockRepository.findOne(Matchers.<Example<UserProfile>>any())).thenReturn(userProfile);
		when(mockRepository.save(any(UserProfile.class))).thenReturn(userProfile);

		serviceImpl.save(userProfile);

		verify(mockRepository, times(1)).findOne(Matchers.<Example<UserProfile>>any()); 
		verify(mockRepository, times(1)).save(userProfile);
		verify(userProfile, times(1)).setRutPuntosCencosud("235711");
		verify(userProfile, never()).setDisplayName(any(String.class));
		verify(userProfile, never()).setDocument(any(String.class));
	}

	@Test
	public void testUpdate() {
		when(userProfile.getId()).thenReturn("1");
		when(userProfile.getDisplayName()).thenReturn("testUser");
		when(userProfile.getDocument()).thenReturn("123");
		when(userProfile.getRutPuntosCencosud()).thenReturn("235711");
		when(mockRepository.findOne(Matchers.<Example<UserProfile>>any())).thenReturn(userProfile);
		when(mockRepository.save(any(UserProfile.class))).thenReturn(userProfile);

		serviceImpl.update(userProfile);

		verify(mockRepository, times(1)).findOne(Matchers.<Example<UserProfile>>any()); 
		verify(mockRepository, times(1)).save(userProfile);
		verify(userProfile, times(1)).setRutPuntosCencosud("235711");
		verify(userProfile, never()).setDisplayName(any(String.class));
		verify(userProfile, never()).setDocument(any(String.class));
	}
}
