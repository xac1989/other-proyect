package com.cencosud.mobile.delivery.adapter;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.cencosud.mobile.delivery.model.DeliveryRequest;
import com.cencosud.mobile.delivery.model.GeoCoordinate;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.DeliveryApiService;
import com.cencosud.mobile.service.impl.DeliveryApiServiceImpl;
import com.cencosud.mobile.util.RestServiceUtil;
import com.ibm.mfp.adapter.api.OAuthSecurity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Delivery Adapter Resource")
@Path("/{region}/v1")
public class DeliveryResource {

	static Logger logger = Logger.getLogger(DeliveryResource.class.getName());
	static final String REGION_1 = "r1";
	static final String REGION_2 = "r2";

	private DeliveryApiService deliveryService;

	public DeliveryApiService getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryApiService deliveryService) {
		this.deliveryService = deliveryService;
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

		return Response.status(Response.Status.OK).entity(healthStatus.toString()).build();
	}

	@POST
	@Path("/delivery")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false, scope = "customer")
	public Response sendDeliveryMode(@QueryParam("email") String email, @PathParam("region") String region,
			DeliveryRequest deliveryRequest) throws NotFoundException {

		logger.info(" DeliveryAdapter (sendDeliveryModeUnsec) email ::: " + email);
		logger.info(" deliveryRequest ::: " + deliveryRequest.toString());

		return Response.ok(deliveryService.sendDeliveryMode(email, region, deliveryRequest), MediaType.APPLICATION_JSON)
				.build();

	}

	public static void main(String arg[]) throws NotFoundException {
		DeliveryResource resource = new DeliveryResource();
		DeliveryApiServiceImpl deliveryService = new DeliveryApiServiceImpl();
		RestServiceUtil restServiceUtil = new RestServiceUtil();
		restServiceUtil
				.setApiBaseUrl("https://api.us.apiconnect.ibmcloud.com/supermercados-cencosud-wong-development/dev");
		restServiceUtil.setApiClientId("5fcf4990-1035-4f93-ab3b-1fe385af6c6a");
		restServiceUtil.setApiSecret("gX5nH3oH7fG1dG4qU2vS7kL5hJ5bS6uC3nY1fE6dI2pY6uR6hU");
		deliveryService.setRestServiceUtil(restServiceUtil);
		resource.setDeliveryService(deliveryService);

		GeoCoordinate geo = new GeoCoordinate(new BigDecimal(-70.57427530000001), new BigDecimal(-33.4056776));
		DeliveryRequest request = new DeliveryRequest(geo, "500", 1, 1);
		System.out.println(resource.sendDeliveryMode("email@globant.com", "r2", request).getEntity());

	}

}
