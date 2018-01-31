package com.cencosud.mobile.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.model.category.Category;
import com.cencosud.mobile.model.category.Configuration;
import com.cencosud.mobile.model.category.UserProfile;
import com.cencosud.mobile.model.profile.wong.ProfileUpdateRequest;
import com.cencosud.mobile.service.CategoriesApiService;
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
public class WongUserProfileV1Resource {
    static Logger logger = Logger.getLogger(WongUserProfileV1Resource.class.getName());

    @Context
    private AdapterSecurityContext securityContext;

    private CategoriesApiService categoriesService;

    private UserProfileApiService userProfileService;

    public CategoriesApiService getCategoriesService() {
        return categoriesService;
    }

    public void setCategoriesService(CategoriesApiService categoriesService) {
        this.categoriesService = categoriesService;
    }

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
    public Map<String, Object> userProfile() {
        JSONObject json = new JSONObject();
        AuthenticatedUser user = securityContext.getAuthenticatedUser();
        if (user != null) {
            String email = getEmail(user);
            json = this.getuserProfile(email);
            // json.put("displayName", user.getDisplayName());
            for (String key : user.getAttributes().keySet()) {
                json.put(key, user.getAttributes().get(key));
            }
        }

        return json.toMap();
    }

    @PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @OAuthSecurity(enabled = true, scope = "customer")
    public UserProfile profilePut(ProfileUpdateRequest profile) throws NotFoundException {
        System.out.println(">>>>>>>>>>>>>>>>>:" + profile);
        // get email
        String email = "";
        if (profile.getId() != null && profile.getId().equals("")) {
            AuthenticatedUser user = securityContext.getAuthenticatedUser();
            if (user != null) {
                email = getEmail(user);
            }
            if (email.equals("")) {
                throw new NotFoundException(404, "Email no especificado");
            }
        }

        UserProfile userProfile = userProfileService.updateProfile(profile);

        if (userProfile.getBirthDate() != null) {
            logger.info("Setting birth date if default then blank string");
            userProfile.setBirthDate(getFilteredDateString(userProfile.getBirthDate()));
        }

        return userProfile;
    }

    private String getHierarchy(Category category) {
        String hierarchy = "";
        Category parent = categoriesService.getCategory(String.valueOf(category.getParentId()));
        if (parent != null) {
            hierarchy += parent.getName();
        }
        hierarchy += "/" + category.getName();
        return hierarchy;
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
    public UserProfile profilePutUnsecured(ProfileUpdateRequest profile) throws NotFoundException {
        System.out.println(">>>>>>>>>>>>>>>>>:" + profile);

        UserProfile userProfile = userProfileService.updateProfile(profile);

        if (userProfile.getBirthDate() != null) {
            userProfile.setBirthDate(getFilteredDateString(userProfile.getBirthDate()));
        }

        return userProfile;
    }

    private JSONObject getuserProfile(String email) {
        JSONObject json = new JSONObject();
        Map<String, Object> favorites = new HashMap<String, Object>();
        JSONArray categoriesJsonArr = new JSONArray();
        UserProfile profileResp = userProfileService.getProfile(email);
        if (profileResp != null) {
            json.put("id", profileResp.getId());
            json.put("displayName", profileResp.getDisplayName());
            json.put("attributes", profileResp.getAttributes());
            json.put("document", profileResp.getDocument());
            json.put("documentType", profileResp.getDocumentType());
            json.put("fullName", profileResp.getFullName());
            json.put("birthDate", getFilteredDateString(profileResp.getBirthDate()));
            json.put("sex", profileResp.getSex());
            json.put("phone", profileResp.getPhone());

            if (profileResp.getConfigurations() != null) {
                JSONObject configuration = new JSONObject();
                JSONArray localConfiguration = new JSONArray();
                JSONArray pushConfiguration = new JSONArray();
                List<Configuration> configs = profileResp.getConfigurations().getLocalNotifications();
                if (configs != null) {
                    for (Configuration c : configs) {
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

            if (profileResp.getFavorites() != null && profileResp.getFavorites().getCategories() != null
                    && !profileResp.getFavorites().getCategories().isEmpty()) {
                for (String cId : profileResp.getFavorites().getCategories()) {
                    Category category = categoriesService.getCategory(cId);
                    JSONObject categoryJson = new JSONObject();
                    if (category != null) {
                        categoryJson.put("id", category.getId());
                        categoryJson.put("active", true);
                        categoryJson.put("name", category.getName());
                        categoryJson.put("icon", category.getIcon());
                        categoryJson.put("hierarchy", getHierarchy(category));
                        categoryJson.put("icon_pdf", category.getIcon_pdf());
                        categoryJson.put("icon_svg", category.getIcon_svg());
                    } else {
                        categoryJson.put("id", Integer.parseInt(cId));
                        categoryJson.put("active", false);
                        categoryJson.put("name", "");
                        categoryJson.put("icon", "");
                        categoryJson.put("hierarchy", "");
                        categoryJson.put("icon_pdf", "");
                        categoryJson.put("icon_svg", "");
                    }
                    categoriesJsonArr.put(categoryJson);
                }
            }
            favorites.put("categories", categoriesJsonArr);
            json.put("favorites", favorites);
        }
        return json;
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
}
