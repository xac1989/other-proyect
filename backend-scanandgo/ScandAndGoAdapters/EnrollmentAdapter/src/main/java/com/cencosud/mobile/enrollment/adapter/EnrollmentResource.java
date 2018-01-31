package com.cencosud.mobile.enrollment.adapter;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.cencosud.middleware.enrollment.model.Enrollment;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.EnrollmentApiService;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * 
 * <h1>EnrollmentResource</h1>
 */
@Api(value = "Enrollment Adapter Resource")
@Path("/r1/v1/enrollment")
public class EnrollmentResource {
	
	static Logger logger = Logger.getLogger(EnrollmentResource.class.getName());

	@Context
	private AdapterSecurityContext securityContext;
	
	private EnrollmentApiService enrollmentService;
	
	@ApiOperation(value = "Create new enrollment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Enrollment attributes returned") })
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Enrollment product(Enrollment enrollment) throws NotFoundException {
		return enrollmentService.product(enrollment);
	}

	public EnrollmentApiService getEnrollmentService() {
		return enrollmentService;
	}

	public void setEnrollmentService(EnrollmentApiService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

}
