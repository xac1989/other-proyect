package com.cencosud.middleware.address.controller;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.middleware.address.model.ErrorResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class})
public class ExceptionHandlerControllerTest {

	@InjectMocks
	private ExceptionHandlerController handler;
	
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHandleException() {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);;
		Exception ex = new NullPointerException("Null Pointer");
		ErrorResponse error = handler.handleException(req, ex);
		
		assertThat(error, is(not(nullValue())));
		assertThat(error.getErrorMessage().getHttpCode(), is("500"));
		assertThat(error.getErrorMessage().getHttpMessage(), is("Internal Server Error"));
	}

	@Test
	public void testHandleIllegalArgument() {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);;
		Exception ex = new IllegalArgumentException("Illegal Argument");
		ErrorResponse error = handler.handleIllegalArgument(req, ex);
		
		assertThat(error, is(not(nullValue())));
		assertThat(error.getErrorMessage().getHttpCode(), is("8"));
		assertThat(error.getErrorMessage().getHttpMessage(), is("Illegal Argument"));
	}

}
