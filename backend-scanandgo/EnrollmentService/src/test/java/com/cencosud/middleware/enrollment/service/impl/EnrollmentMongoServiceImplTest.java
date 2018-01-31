package com.cencosud.middleware.enrollment.service.impl;

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

import com.cencosud.middleware.enrollment.model.Enrollment;
import com.cencosud.middleware.enrollment.repository.EnrollmentRepository;
import com.cencosud.middleware.enrollment.service.impl.EnrollmentMongoServiceImpl;

/**
 * 
 * 
 * <h1>EnrollmentMongoServiceImplTest</h1>
 * <p>
 * Test de la clase {@link EnrollmentMongoServiceImpl}
 * </p>
 */
@RunWith(MockitoJUnitRunner.class)
public class EnrollmentMongoServiceImplTest {
	
	@Mock
	private EnrollmentRepository mockRepository;
	
	@Mock
	private Enrollment enrollment;
	
	@InjectMocks
	private EnrollmentMongoServiceImpl serviceImpl;
	
	@Captor ArgumentCaptor<List<Enrollment>> argument;
	
	@Before
	public void setUp() throws Exception {
	    // Initialize mocks created above
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void save_newFeedback(){
		List<Enrollment> lstEnrollment = new ArrayList<>(1);
		when(enrollment.getEmail()).thenReturn("aa@aa.com");
		when(enrollment.getStoreId()).thenReturn("1502");
		when(enrollment.getAnotherStore()).thenReturn("otra tienda");
		when(enrollment.getLastname()).thenReturn("1");
		when(enrollment.getName()).thenReturn("La entrega se realizo fuera de termino");
		when(enrollment.getRut()).thenReturn("12345");
		lstEnrollment.add(enrollment);
		when(mockRepository.findAll()).thenReturn(null);
		when(mockRepository.findByEmail(anyString())).thenReturn(null);
		when(mockRepository.save(any(Enrollment.class))).thenReturn(enrollment);
		
		serviceImpl.save(enrollment);
		
		verify(mockRepository, times(1)).save(enrollment);
		
		verify(enrollment, never()).setId(anyString());
	}
}
