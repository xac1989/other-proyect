package com.cencosud.middleware.delivery.aspect;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceAspectTest {

	@Mock
	private ProceedingJoinPoint proceedingJoinPoint;

	@InjectMocks
	private ServiceAspect serviceAspect;

	@Mock
	private Signature signature;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void argsIsNullTest() throws Throwable {
		when(proceedingJoinPoint.getArgs()).thenReturn(null);
		when(proceedingJoinPoint.proceed()).thenReturn(new Object());
		when(proceedingJoinPoint.getTarget()).thenReturn(this);
		when(proceedingJoinPoint.getSignature()).thenReturn(signature);
		when(signature.getName()).thenReturn("argsIsNullTest");
		Object returnValue = serviceAspect.aroundCreation(proceedingJoinPoint);
		verify(proceedingJoinPoint, times(1)).getArgs();
		verify(proceedingJoinPoint, never()).proceed(null);
		verify(proceedingJoinPoint, times(1)).proceed();
		assertNotNull(returnValue);
	}

	@Test
	public void argsWith2elementsTest() throws Throwable {
		Object[] elements = new Object[2];
		elements[0] = new Integer(1);
		elements[1] = "Second value";
		when(proceedingJoinPoint.getArgs()).thenReturn(elements);
		when(proceedingJoinPoint.proceed()).thenReturn(new Object());
		when(proceedingJoinPoint.getTarget()).thenReturn(this);
		when(proceedingJoinPoint.getSignature()).thenReturn(signature);
		when(signature.getName()).thenReturn("argsWith2elementsTest");
		Object returnValue = serviceAspect.aroundCreation(proceedingJoinPoint);
		verify(proceedingJoinPoint, times(2)).getArgs();
		verify(proceedingJoinPoint, never()).proceed(null);
		verify(proceedingJoinPoint, times(1)).proceed();
		assertNotNull(returnValue);
	}
}
