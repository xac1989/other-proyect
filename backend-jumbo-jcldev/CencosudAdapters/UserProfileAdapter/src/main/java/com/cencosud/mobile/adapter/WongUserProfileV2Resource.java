package com.cencosud.mobile.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.cencosud.mobile.dto.profile.jumbo.JumboUserProfileDto;
import com.cencosud.mobile.dto.profile.jumbo.UpdateProfileGenericRequest;
import com.cencosud.mobile.dto.profile.jumbo.UpdateProfileJumboRequest;
import com.cencosud.mobile.mapper.UserProfileMapperDto;
import com.cencosud.mobile.model.profile.jumbo.SalesChannel;
import com.cencosud.mobile.model.profile.jumbo.UserProfile;
import com.cencosud.mobile.model.profile.jumbo.UserProfileHomolog;
import com.cencosud.mobile.service.WongUserProfileApiServiceV2;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User Profile Adapter Resource")
@Path("/r1/v2/userprofile")
public class WongUserProfileV2Resource {

	static final Logger logger = LoggerFactory.getLogger(WongUserProfileV2Resource.class);
	static final String REGION_1 = "r1";

	@Context
	private AdapterSecurityContext securityContext;

	private WongUserProfileApiServiceV2 userProfileService;

	public WongUserProfileApiServiceV2 getUserProfileService() {
		return userProfileService;
	}

	public void setUserProfileService(WongUserProfileApiServiceV2 userProfileService) {
		this.userProfileService = userProfileService;
	}

	@ApiOperation(value = "Returns user attributes and display name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User attributes returned") })
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response userProfile() {
	    String region = REGION_1;
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		UserProfileHomolog userProfileHomolog = null;
		UserProfileMapperDto mapper = UserProfileMapperDto.INSTANCE;
		logger.info("@@@@@@@@@@@@@ region = {}", region);

		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Entity not found.\"}").build();
		}
		String email = getEmail(user);
		userProfileHomolog = this.getUserProfile(email, region);
		if (userProfileHomolog == null || (userProfileHomolog.getId() == null)) {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Entity not found.\"}").build();
		}
		JumboUserProfileDto jumboProfileResponse = mapper.userProfileHomologToJumboDto(userProfileHomolog);
		SalesChannel salesChannel = userProfileService.getSalesChannel(userProfileHomolog.getDefaultSalesChannel());
		if (salesChannel != null && salesChannel.getId() != null && !salesChannel.getId().equals(0)) {
			jumboProfileResponse.setDefaultSalesChannel(salesChannel);
		}

		List<Map<String, String>> cookies = getCookies(user);
		jumboProfileResponse.setCookies(cookies);
		return Response.ok(jumboProfileResponse, MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response profilePut(UpdateProfileJumboRequest profile) {
		logger.info(">>>>>>>>>>>>>>>>>: {}", profile);

		AuthenticatedUser user = securityContext.getAuthenticatedUser();

		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Entity not found.\"}").build();
		}
		String email = getEmail(user);

		return this.profilePutUnsecured(email, profile);
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

	@SuppressWarnings("unchecked")
	private List<Map<String, String>> getCookies(AuthenticatedUser authenticatedUser) {
		if (authenticatedUser != null && authenticatedUser.getAttributes() != null
				&& authenticatedUser.getAttributes().containsKey("cookies")
				&& authenticatedUser.getAttributes().get("cookies") != null) {
			List<Map<String, String>> cookies = (List<Map<String, String>>) authenticatedUser.getAttributes()
					.get("cookies");
			if (cookies != null) {
				return cookies;
			}
		}
		return new ArrayList<Map<String, String>>();
	}

	@ApiOperation(value = "Returns user attributes and display name")
	@GET
	@Path("/unsec")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Response userProfileUnsecured(@QueryParam("email") String email) {
	    String region = REGION_1;
		UserProfileHomolog userProfileHomolog = null;
		UserProfileMapperDto mapper = UserProfileMapperDto.INSTANCE;
		logger.info("@@@@@@@@@@@@@ region = {}", region);

		userProfileHomolog = this.getUserProfile(email, region);
		if (userProfileHomolog == null || (userProfileHomolog.getId() == null)) {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Entity not found.\"}").build();
		}
		JumboUserProfileDto jumboProfileResponse = mapper.userProfileHomologToJumboDto(userProfileHomolog);
		SalesChannel salesChannel = userProfileService.getSalesChannel(userProfileHomolog.getDefaultSalesChannel());
		if (salesChannel != null && salesChannel.getId() != null && !salesChannel.getId().equals(0)) {
			jumboProfileResponse.setDefaultSalesChannel(salesChannel);
		}
		return Response.ok(jumboProfileResponse, MediaType.APPLICATION_JSON).build();

	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/unsec")
	@OAuthSecurity(enabled = false)
	public Response profilePutUnsecured(@QueryParam("email") String email,
			UpdateProfileGenericRequest profile) {
	    String region = REGION_1;
		logger.info("@@@ Update userProfile ::email:: {} ::region:: {}", email, region);

		if (REGION_1.equals(region) && StringUtils.isEmpty(email)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		// ProfileUpdateRequest
		UserProfile userProfile = userProfileService.putProfile(profile, region, email);
		if (userProfile.getBirthDate() != null) {
			userProfile.setBirthDate(getFilteredDateString(userProfile.getBirthDate()));
		}
		return Response.ok(userProfile, MediaType.APPLICATION_JSON).build();
	}

	private UserProfileHomolog getUserProfile(String email, String region) {
		UserProfileMapperDto mapper = UserProfileMapperDto.INSTANCE;
		UserProfile profileResp = userProfileService.getUserProfile(email, region);
		UserProfileHomolog userResponse = mapper.userProfileToUserProfileDto(profileResp);
		return userResponse;
	}

	private String getFilteredDateString(String birthDate) {
		logger.info("Parsing birthdate:" + birthDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Calendar MAX_DATE = new GregorianCalendar(2999, Calendar.JANUARY, 1);
		if (birthDate == null || "".equals(birthDate)) {
			return "";
		}
		try {
			date = sdf.parse(birthDate);
			logger.info("Parsed birthdate:" + date);
		} catch (ParseException e) {
			logger.info("Unparseable birthdate");
			return "";
		}

		if (date.after(MAX_DATE.getTime())) {
			return "";
		}

		return birthDate;
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

		return Response.status(Response.Status.OK).entity(healthStatus.toString()).build();
	}

}
