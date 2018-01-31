package com.cencosud.mobile.feedback.adapter;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.cencosud.middleware.feedback.model.Feedback;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.FeedbackApiService;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * 
 * <h1>FeedbackResource</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Apr 10, 2017
 */
@Api(value = "Feedback Adapter Resource")
@Path("/r1/v1/feedback")
public class FeedbackResource {
	
	static Logger logger = Logger.getLogger(FeedbackResource.class.getName());

	@Context
	private AdapterSecurityContext securityContext;
	
	private FeedbackApiService feedbackService;
	
	@ApiOperation(value = "Create new feedback")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product attributes returned") })
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Feedback product(Feedback feedback) throws NotFoundException {
		return feedbackService.product(feedback);
	}

	/**
	 * @return the feedbackService
	 */
	public FeedbackApiService getFeedbackService() {
		return feedbackService;
	}

	/**
	 * @param feedbackService the feedbackService to set
	 */
	public void setFeedbackService(FeedbackApiService feedbackService) {
		this.feedbackService = feedbackService;
	}

	
}
