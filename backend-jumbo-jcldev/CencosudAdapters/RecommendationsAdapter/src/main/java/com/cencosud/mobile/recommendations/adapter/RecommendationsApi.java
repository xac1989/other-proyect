package com.cencosud.mobile.recommendations.adapter;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Generated;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.json.java.JSONObject;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/r1/v1/recommendations")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(description = "the recommendations API")
@Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
@Component
public class RecommendationsApi {

	private final Logger logger = Logger.getLogger(RecommendationsApi.class.toString());

	@Autowired
	private RecommendationsApiService delegate = null;

	private RecommendationsApiService getRecommendationsApiService() {

		return delegate;
	}

	@GET

	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	public Response recommendationsGet(
			@ApiParam(value = "Recomendation type: Similar, FrequentlyBoughtTogether, EndedUpBuying, BestSellers, MostVisited, Trending", required = true) @QueryParam("type") String type,
			@ApiParam(value = "Product identification") @QueryParam("productId") List<String> productId,
			@ApiParam(value = "Category identification") @QueryParam("categoryId") List<String> categoryId,
			@ApiParam(value = "Tag Identification") @QueryParam("tagId") List<String> tagId,
			@ApiParam(value = "Device Identification") @QueryParam("deviceId") String deviceId,
			@ApiParam(value = "User identification") @QueryParam("userId") String userId,
			@ApiParam(value = "Use bought products flag") @QueryParam("useBoughtProducts") String useBoughtProducts,
			@ApiParam(value = "Use cart products flag") @QueryParam("useCartProducts") String useCartProducts,
			@ApiParam(value = "Use visited products flag") @QueryParam("useVisitedProducts") String useVisitedProducts,
			@ApiParam(value = "Elements size") @QueryParam("size") Integer size,
			@ApiParam(value = "Email") @QueryParam("email") String email,
			@Context AdapterSecurityContext securityContext) throws NotFoundException {

		logger.info("-->securityContext" + securityContext);

		if (email == null || "".equals(email)) {

			AuthenticatedUser user = securityContext.getAuthenticatedUser();
			email = this.getEmail(user);
		}
		return getRecommendationsApiService().recommendationsGet(type, productId, categoryId, tagId, deviceId, userId,
				useBoughtProducts, useCartProducts, useVisitedProducts, size, email, securityContext);
	}

	@GET
	@Path("/relevantProduct")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	public Response recommendationsRelevantProductGet(@ApiParam(value = "Email") @QueryParam("email") String email,
			@Context AdapterSecurityContext securityContext) throws NotFoundException {

		logger.info("-->securityContext" + securityContext);

		if (email == null || "".equals(email)) {

			AuthenticatedUser user = securityContext.getAuthenticatedUser();
			email = this.getEmail(user);
		}
		return getRecommendationsApiService().recommendationsRelevantProductGet(email, securityContext);
	}

	private String getEmail(AuthenticatedUser authenticatedUser) throws NotFoundException {
		String email = "";

		logger.info("-->user:" + authenticatedUser);

		if (authenticatedUser != null && authenticatedUser.getAttributes() != null
				&& authenticatedUser.getAttributes().containsKey("email")
				&& authenticatedUser.getAttributes().get("email") != null) {

			for (String k : authenticatedUser.getAttributes().keySet()) {
				logger.info("-->attr:" + k);
			}

			email = (String) authenticatedUser.getAttributes().get("email");
			if (email == null) {
				return "";
			}

			email = email.trim();
			if (email.equalsIgnoreCase("null") || email.equalsIgnoreCase("")) {
				return "";
			}
		} else {
			logger.info("-->Auth user:" + authenticatedUser);
		}
		return email;
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
