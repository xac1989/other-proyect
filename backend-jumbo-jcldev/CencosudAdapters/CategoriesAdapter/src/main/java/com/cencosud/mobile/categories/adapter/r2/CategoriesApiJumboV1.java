package com.cencosud.mobile.categories.adapter.r2;

import javax.annotation.Generated;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.mobile.categories.adapter.CategoriesApiService;
import com.ibm.json.java.JSONObject;
import com.ibm.mfp.adapter.api.OAuthSecurity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/r2/v1/categories")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Categories API Region 2 Version 1")
@Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T10:51:58.936-03:00")
@Component
public class CategoriesApiJumboV1 {
	
	private static final String REGION = "r2";
	private static final String VERSION = "v1";
	
	@Autowired
	private CategoriesApiService delegate = null;

	private CategoriesApiService getCategoriesApiService() {
		return delegate;
	}

	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	@OAuthSecurity(enabled = false)
	public Response categoriesGet(@Context SecurityContext securityContext) {
		return getCategoriesApiService().categoriesGet(REGION, VERSION);
	}

	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Path("/filter/fields")
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	@OAuthSecurity(enabled = false)
	public Response filterFieldsGet(
	        @QueryParam("filter") String filter,
			@QueryParam("q") String q,
			@Context SecurityContext securityContext,
			@QueryParam("isDepartment") boolean isDepartment,
			@QueryParam("deals") String deals) {
	    System.out.println("deals: " + deals);
	    boolean ofertas = deals != null && ("".equals(deals.trim()) || "Y".equals(deals.trim().toUpperCase()));
		return getCategoriesApiService().filterFieldsGet(REGION, VERSION, filter, q, isDepartment, ofertas);
	}

	@GET
	@Path("/health")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	@OAuthSecurity(enabled = false)
	public Response getHealth() {
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
