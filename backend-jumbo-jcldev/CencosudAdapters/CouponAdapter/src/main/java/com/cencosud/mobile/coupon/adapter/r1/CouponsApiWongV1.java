package com.cencosud.mobile.coupon.adapter.r1;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Generated;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.mobile.coupon.adapter.CouponsApiService;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.UserProfileApiService;
import com.ibm.json.java.JSONObject;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/r1/v1/coupons")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Coupon API V1")
@Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
@Component
public class CouponsApiWongV1 {

	private final Logger logger = Logger.getLogger(CouponsApiWongV1.class.toString());
	private static final String REGION = "r1";
	private static final String VERSION = "v1";

	@Autowired
	private CouponsApiService delegate = null;

	@Autowired
	private UserProfileApiService userProfileApiService = null;

	@Context
	private AdapterSecurityContext securityContext;

	private CouponsApiService getCouponsApiService() {
		return delegate;
	}

	private UserProfileApiService getUserProfileApiService() {
		return userProfileApiService;
	}

	@GET
	@Path("/{dni}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = Response.class, authorizations = {}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Coupons", response = Response.class) })
	@OAuthSecurity(enabled = false)
	public Response getCouponsByDni(@ApiParam(value = "Customer identification") @PathParam("dni") String dni)
			throws NotFoundException, IOException {
		logger.info("Get coupons by DNI:" + dni);
		return getCouponsApiService().couponsGet(dni,REGION,VERSION);
	}

	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = Response.class, authorizations = {}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Coupons", response = Response.class) })
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response getCouponsByUser() throws NotFoundException, IOException {
		logger.info("Get coupons by Auth User");
		String dni = getDni(securityContext);

		return getCouponsApiService().couponsGet(dni,REGION,VERSION);
	}

	private String getDni(AdapterSecurityContext securityContext) throws NotFoundException {
		String dni = null;
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		logger.info("AuthUser: " + user);
		String email = this.getEmail(user);
		logger.info("id: " + email);
		dni = this.getUserProfileApiService().profileGet(email,REGION,VERSION).getDocument();
		logger.info("document: " + dni);
		return dni;
	}

	private String getEmail(AuthenticatedUser authenticatedUser) throws NotFoundException {
		String email = "";

		if (authenticatedUser != null && authenticatedUser.getAttributes() != null
				&& authenticatedUser.getAttributes().containsKey("email")
				&& authenticatedUser.getAttributes().get("email") != null) {
			email = (String) authenticatedUser.getAttributes().get("email");
//			if (email == null) {
//				logger.info("email null");
//				throw new NotFoundException(404, "Authenticated user data not found");
//			}

			email = email.trim();
			if (email.equalsIgnoreCase("null") || email.equalsIgnoreCase("")) {
				logger.info("email null or empty");
				throw new NotFoundException(404, "Authenticated user data not found");
			} else {
				return email;
			}

		} else {
			logger.info("user null or not contains email attribute");
			throw new NotFoundException(404, "Authenticated user data not found");
		}

	}

	@GET
	@Path("/health")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	@OAuthSecurity(enabled = false)
	public Response getHealth() throws NotFoundException {

		JSONObject healthStatus = new JSONObject();

		healthStatus.put("status", "UP");

		JSONObject healthDetailStatus = new JSONObject();
		healthDetailStatus.put("status", "UP");
		healthDetailStatus.put("total", "0");
		healthDetailStatus.put("free", "0");
		healthDetailStatus.put("threshold", "0");

		healthStatus.put("diskSpace", healthDetailStatus);

		return Response.status(Response.Status.OK).entity(healthStatus).build();
	}

}
