package com.cencosud.mobile.login.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cencosud.middleware.category.model.Configuration;
import com.cencosud.middleware.category.model.UserProfile;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.UserProfileApiService;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User Profile Adapter Resource")
@Path("/r1/v1/userprofile")
public class UserProfileResource {
	static Logger logger = Logger.getLogger(UserProfileResource.class.getName());

	@Context
	private AdapterSecurityContext securityContext;


//	private CategoriesApiService categoriesService;


	private UserProfileApiService userProfileService;
	
//	public CategoriesApiService getCategoriesService() {
//		return categoriesService;
//	}
//
//	public void setCategoriesService(CategoriesApiService categoriesService) {
//		this.categoriesService = categoriesService;
//	}

	public UserProfileApiService getUserProfileService() {
		return userProfileService;
	}

	public void setUserProfileService(UserProfileApiService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@ApiOperation(value = "Returns user attributes and display name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User attributes returned") })
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = true, scope = "customer")
	public Map<String, Object> userProfile() throws NotFoundException {
		JSONObject json = new JSONObject();
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		if (user != null) {
			String email = getEmail(user);
			json = this.getuserProfile(email);
			json.put("displayName", user.getDisplayName());
			for (String key : user.getAttributes().keySet()) {
				json.put(key, user.getAttributes().get(key));
			}
		}

		return json.toMap();
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@OAuthSecurity(enabled = true, scope="customer")
	public UserProfile profilePut(UserProfile profile) throws NotFoundException {
		System.out.println(">>>>>>>>>>>>>>>>>:"+profile);
		// get email
		if (profile.getUserProfileId() == null || profile.getUserProfileId().equals("")) {
			throw new NotFoundException(500, "userProfileService no especificado");
		}
		return userProfileService.profilePut(profile);
	}

//	private String getHierarchy(Category category) throws NotFoundException {
//		String hierarchy = "";
//		Category parent = categoriesService.categoriesGet(String.valueOf(category.getParentId()));
//		if (parent != null) {
//			hierarchy += parent.getName();
//		}
//		hierarchy += "/" + category.getName();
//		return hierarchy;
//	}

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
		
	@ApiOperation(value = "Returns user attributes and display name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User attributes returned") })
	@GET
	@Path("/unsec")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Map<String, Object> userProfileUnsecured(@QueryParam("email") String email) throws NotFoundException {
		return this.getuserProfile(email).toMap();
	}
		
	
	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Path("/unsec")
	@OAuthSecurity(enabled = false)
	public UserProfile profilePutUnsecured(UserProfile profile) throws NotFoundException {
		System.out.println(">>>>>>>>>>>>>>>>>:"+profile);
		return userProfileService.profilePut(profile);
	}
	
	
	
	private JSONObject getuserProfile(String email) throws NotFoundException {		
		JSONObject json = new JSONObject();
		Map<String, Object> favorites = new HashMap<String, Object>();
		JSONArray categoriesJsonArr = new JSONArray();
		UserProfile profileResp = userProfileService.profileGet(email);
		if (profileResp != null) {
			json.put("id", profileResp.getId());
			json.put("displayName", profileResp.getDisplayName());
			json.put("attributes", profileResp.getAttributes());
			json.put("document", profileResp.getDocument());
			json.put("documentType", profileResp.getDocumentType());
			json.put("rutPuntosCencosud", profileResp.getRutPuntosCencosud());
			

			if(profileResp.getConfigurations() != null){
				JSONObject configuration = new JSONObject();
				JSONArray localConfiguration = new JSONArray();
				JSONArray pushConfiguration = new JSONArray();
				List<Configuration> configs = profileResp.getConfigurations().getLocalNotifications();
				if(configs != null){
					for(Configuration c:configs){
						JSONObject conf = new JSONObject();
						conf.put("id", c.getId());
						conf.put("enabled", c.isEnabled());
						localConfiguration.put(conf);
					}
				}
				configuration.put("localNotifications", localConfiguration);
				configuration.put("pushNotifications", pushConfiguration);
				json.put("configurations", configuration);
			}
					
			if (profileResp.getFavorites() != null && profileResp.getFavorites().getCategories() != null &&
					!profileResp.getFavorites().getCategories().isEmpty()) {
				for (String cId : profileResp.getFavorites().getCategories()) {
//					Category category = categoriesService.categoriesGet(cId);
					JSONObject categoryJson = new JSONObject();
//					if (category != null) {
//						categoryJson.put("id", category.getId());
//						categoryJson.put("active", true);
//						categoryJson.put("name", category.getName());
//						categoryJson.put("icon", category.getIcon());
//						categoryJson.put("hierarchy", getHierarchy(category));
//					} else {
						categoryJson.put("id", Integer.parseInt(cId));
						categoryJson.put("active", false);
						categoryJson.put("name", "");
						categoryJson.put("icon", "");
						categoryJson.put("hierarchy", "");
//					}
					categoriesJsonArr.put(categoryJson);
				}
			}
			favorites.put("categories", categoriesJsonArr);
			json.put("favorites", favorites);
		}
		return json;
	}
}
