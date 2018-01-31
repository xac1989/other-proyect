package com.cencosud.middleware.feedback.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.feedback.model.Feedback;
import com.cencosud.middleware.feedback.repository.FeedbackRepository;

/**
 * 
 * 
 * <h1>FeedbackMongoServiceImplTest</h1>
 * <p>
 * Test de la clase {@link FeedbackMongoServiceImpl}
 * </p>
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedbackMongoServiceImplTest {
	
	@Mock
	private FeedbackRepository mockRepository;
	
	@Mock
	private Feedback feedback;
	
	@InjectMocks
	private FeedbackMongoServiceImpl serviceImpl;
	
	@Captor ArgumentCaptor<List<Feedback>> argument;
	
	@Before
	public void setUp() throws Exception {
	    // Initialize mocks created above
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void save_newFeedback(){
		List<Feedback> lstFeedback = new ArrayList<>(1);
		when(feedback.getEmail()).thenReturn("aa@aa.com");
		when(feedback.getStoreId()).thenReturn("1502");
		when(feedback.getValue()).thenReturn("1");
		when(feedback.getDescription()).thenReturn("La entrega se realizo fuera de termino");
		lstFeedback.add(feedback);
		when(mockRepository.findAll()).thenReturn(null);
		when(mockRepository.findByEmail(anyString())).thenReturn(null);
		when(mockRepository.save(any(Feedback.class))).thenReturn(feedback);
		
		serviceImpl.save(feedback);
		
		verify(mockRepository, times(1)).save(feedback);
		
		verify(feedback, never()).setId(anyString());
	}
}
