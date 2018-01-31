package com.cencosud.mobile.order.adapter.r1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.mobile.order.adapter.NotFoundException;
import com.cencosud.mobile.order.adapter.OrderApiService;
import com.ibm.json.java.JSONObject;
import com.ibm.mfp.adapter.api.OAuthSecurity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/r1/v1/order")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Order Wong API V1")
@Component
public class OrderApiWongV1 {
	@Autowired
	private OrderApiService delegate = null;
	private static final String VERSION = "v1";
	private static final String REGION = "r1";

	private OrderApiService getOrderApiService() {

		return delegate;
	}

	@GET
	@Path("/{orderId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	@OAuthSecurity(enabled = false)
	public Response getOrderById(@ApiParam(value = "Order Id", required = true) @PathParam("orderId") String orderId,
			@Context SecurityContext securityContext) throws Exception {
		return getOrderApiService().getOrderById(REGION,VERSION, orderId);
	}

	@GET
	@Path("/{orderId}/{sc}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	@OAuthSecurity(enabled = false)
	public Response getOrderById(@ApiParam(value = "Order Id", required = true) @PathParam("orderId") String orderId,
			@ApiParam(value = "Sales Channel", required = true) @PathParam("sc") String sc,
			@Context SecurityContext securityContext) throws Exception {
		return getOrderApiService().getOrderById(REGION,VERSION,orderId, sc);
	}

	@GET
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns Order List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	@OAuthSecurity(enabled = false)
	public Response orderSearchGet(@ApiParam(value = "", required = true) @QueryParam("email") String email,
			@Context SecurityContext securityContext) throws NotFoundException {
		return getOrderApiService().findOrders(REGION,VERSION, email);
	}

	@GET
	@Path("/health")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@io.swagger.annotations.ApiOperation(value = "", notes = "", response = void.class, authorizations = {

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
