package com.cencosud.mobile.address.adapter;

import java.util.Collections;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.profile.model.Address;
import com.cencosud.mobile.profile.model.AddressResponse;
import com.cencosud.mobile.profile.model.AddressesResponse;
import com.cencosud.mobile.profile.model.ErrorMessage;
import com.cencosud.mobile.profile.model.ErrorResponse;
import com.cencosud.mobile.profile.model.Metadata;
import com.cencosud.mobile.service.AddressApiService;
import com.cencosud.mobile.service.impl.AddressApiServiceImpl;
import com.cencosud.mobile.util.RestServiceUtil;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Address Adapter Resource")
@Path("/{region}/v1")
public class AddressResource {

	static final Logger LOGGER = LoggerFactory.getLogger(AddressResource.class);
	static final String REGION_1 = "r1";
	static final String REGION_2 = "r2";

	@Context
	private AdapterSecurityContext securityContext;

	private AddressApiService addressService;

	
	public AddressApiService getAddressService() {
		return addressService;
	}


	public void setAddressService(AddressApiService addressService) {
		this.addressService = addressService;
	}


	private String getEmail(AuthenticatedUser authenticatedUser) {
		if (authenticatedUser != null && authenticatedUser.getAttributes() != null
				&& authenticatedUser.getAttributes().containsKey("email")
				&& authenticatedUser.getAttributes().get("email") != null) {
			String email = (String) authenticatedUser.getAttributes().get("email");
			if (email != null) {
				email = email.trim();
				if (email.equalsIgnoreCase("null") || email.equalsIgnoreCase("")) {
					return "";
				} else {
					return email;
				}
			}
		}
		return "";
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


	@ApiOperation(value = "Returns user attributes and display name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User attributes returned") })
	@GET
	@Path("/addresses")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response getUserAddresses(@PathParam("region") String region,
			@Context AdapterSecurityContext securityContext) throws NotFoundException {

		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		LOGGER.info(" Get Addresses for userProfile ");

		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(getErrorMessage(Response.Status.NOT_FOUND.getStatusCode(), "Entity not found")).build();
		}
		String email = getEmail(user);
		LOGGER.info(" >>email= {}", email);

		return this.getUnsecureUserAddresses(email, region);
	}

	private ErrorResponse getErrorMessage(int codeError, String messageError) {
		ErrorMessage errorMessage = new ErrorMessage(String.valueOf(codeError), messageError);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(errorMessage);
		return errorResponse;
	}

	@GET
	@Path("/addresses/unsec")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Response getUnsecureUserAddresses(@QueryParam("email") String email, @PathParam("region") String region)
			throws NotFoundException {

		String addresses = addressService.getUserAddresses(email, region);
		if (StringUtils.isEmpty(addresses)) {
			AddressesResponse response = new AddressesResponse(Collections.<Address>emptyList(), getMetadata());
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		}

		return Response.ok(addresses, MediaType.APPLICATION_JSON).build();

	}
	
	@POST
	@Path("/addresses")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response createUserAddress(@PathParam("region") String region,
			@Context AdapterSecurityContext securityContext,
			Address address) throws NotFoundException {
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		LOGGER.info(" Get Addresses for userProfile ");

		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(getErrorMessage(Response.Status.NOT_FOUND.getStatusCode(), "Entity not found")).build();
		}
		String email = getEmail(user);
		LOGGER.info(" >>email= {}", email);
		
		return this.createUnsecureUserAddress(email, region, address);

	}
	
	@POST
	@Path("/addresses/unsec")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false)
	public Response createUnsecureUserAddress(@QueryParam("email") String email, @PathParam("region") String region, Address address)
			throws NotFoundException {
		
		AddressResponse addressResponse = addressService.createUserAddress(email, region, address);
		return Response.ok(addressResponse, MediaType.APPLICATION_JSON).build();

	}
	
	
	@PUT
	@Path("/addresses")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response editUserAddress(@PathParam("region") String region,
			@Context AdapterSecurityContext securityContext,
			Address address) throws NotFoundException {
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		LOGGER.info(" Get Addresses for userProfile ");

		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(getErrorMessage(Response.Status.NOT_FOUND.getStatusCode(), "Entity not found")).build();
		}
		String email = getEmail(user);
		LOGGER.info(" >>email= {}", email);
		
		return this.editUnsecureUserAddress(email, region, address);

	}
	
	@PUT
	@Path("/addresses/unsec")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false)
	public Response editUnsecureUserAddress(@QueryParam("email") String email, @PathParam("region") String region, Address address)
			throws NotFoundException {
		
		AddressResponse addressResponse = addressService.editUserAddress(email, region, address);
		return Response.ok(addressResponse, MediaType.APPLICATION_JSON).build();

	}
	
	
	@DELETE
	@Path("/addresses")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response removeUserAddress(@PathParam("region") String region, 
			@QueryParam("addressId") String addressId,
			@Context AdapterSecurityContext securityContext) throws NotFoundException {
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		LOGGER.info(" Get Addresses for userProfile ");

		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(getErrorMessage(Response.Status.NOT_FOUND.getStatusCode(), "Entity not found")).build();
		}
		String email = getEmail(user);
		LOGGER.info(" >>email= {}", email);
		
		return this.removeUnsecureUserAddress(email, region, addressId);

	}
	
	@DELETE
	@Path("/addresses/unsec")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Response removeUnsecureUserAddress(@QueryParam("email") String email, @PathParam("region") String region, @QueryParam("addressId") String addressId)
			throws NotFoundException {
		
		LOGGER.info(" Delete Address for User ");
		
		String response = addressService.deleteUserAddress(email, addressId, region);
		LOGGER.info(" Delete Address for User response::: {}", response);
		
		return Response.noContent().build();
	}
	

	private Metadata getMetadata() {
		Metadata metadata = new Metadata();
		metadata.setRegion("r2");
		metadata.setRequestTime(new Date().toString());
		metadata.setVersion("v1");
		return metadata;
	}

	public static void main(String arg[]) throws NotFoundException{
		AddressResource resource = new AddressResource();
		AddressApiServiceImpl userProfileService = new AddressApiServiceImpl();
		RestServiceUtil restServiceUtil = new RestServiceUtil();
		restServiceUtil.setApiBaseUrl("https://api.us.apiconnect.ibmcloud.com/supermercados-cencosud-wong-development/sandbox");
		restServiceUtil.setApiClientId("a8b2ca23-8fc0-4643-8bcf-469fa8b0c814");
		restServiceUtil.setApiSecret("N5pC2xE1wY4gH6sN0xK0wI1kU8vO7fI3xN3kX1lA5fA4fE1aD6");
		userProfileService.setRestServiceUtil(restServiceUtil);
		resource.setAddressService(userProfileService);
		//BigDecimal[] geoCoordinate = {new BigDecimal(-70.57427530000001), new BigDecimal(-33.4056776)};
		//Address address = new Address("e8c9c1c5-61c8-449c-957c-a1a0d1b5f793","-1503349421861","-1503349421861","residential","","Santiago","REGION METROPOLITANA","Cerro El Plomo","5630","Las Condes","","Anyela Herrera",geoCoordinate);
		Address address = new Address();
		address.setAddressId("d5f3fe39-dcab-4010-ad95-b19efe3cda75");
		address.setCity("Coquimbo");
		//System.out.println(resource.removeUnsecureUserAddress("g.granados@globant.com", "r2", "prueba"));//DELETE
		System.out.println(resource.getUnsecureUserAddresses("g.granados@globant.com", "r2").getEntity());//GET
		//System.out.println(((AddressResponse)resource.createUnsecureUserAddress("g.granados@globant.com", "r2", address).getEntity()).getAddress());//POST
		//System.out.println(((AddressResponse)resource.editUnsecureUserAddress("g.granados@globant.com", "r2", address).getEntity()).getAddress());//PUT
	}
	
}
