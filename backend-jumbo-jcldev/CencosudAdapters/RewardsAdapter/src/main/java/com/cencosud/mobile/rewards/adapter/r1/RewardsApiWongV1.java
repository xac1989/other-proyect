package com.cencosud.mobile.rewards.adapter.r1;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.rewards.adapter.RewardsApiService;
import com.cencosud.mobile.service.UserProfileApiService;
import com.cencosud.mobile.service.model.UserProfile;
import com.ibm.json.java.JSONObject;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/r1/v1/rewards")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Rewards API V1")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
@Component
public class RewardsApiWongV1  {
	
	private final Logger logger = Logger.getLogger(RewardsApiWongV1.class.toString());
	private static final int DOC_TYPE = 1;
	private static final String REGION = "r2";
	private static final String VERSION = "v2";	
	
   @Autowired
   private RewardsApiService delegate = null;
   
   @Autowired
   private UserProfileApiService userProfileApiService = null;
   
   private RewardsApiService getRewardsApiService()  {

   		return delegate;
   }
   
   private UserProfileApiService getUserProfileApiService()  {
 		return userProfileApiService;
  }
   
    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(
    	value = "", 
		notes = "", 
		response = void.class, 
		authorizations = {
        	
    		}, tags={  })
    @ApiResponses(value = { 
    @ApiResponse(code = 200, message = "200 OK", response = void.class) })
    public Response rewardsGet(@ApiParam(value = "Email") @QueryParam("email") String email, @Context AdapterSecurityContext securityContext)
    throws NotFoundException {
    	
    	logger.info("[RewardsAdapter](rewardsGet) Get rewards by Auth User - email:::"+ email +" - securityContext:::"+securityContext);
    	
    	String docNumber = getDocNumber(securityContext, email);
    	
    	if (docNumber == null){
    		docNumber = "";
    	}
    	
        return getRewardsApiService().rewardsGet(docNumber, DOC_TYPE);
    	
    }
    
    
    private String getEmail(AuthenticatedUser authenticatedUser) throws NotFoundException {
        String email = "";
        
        logger.info("[RewardsAdapter](getEmail) Getting email by authenticated user:::"+authenticatedUser);
        
        if (authenticatedUser != null && authenticatedUser.getAttributes() != null
                && authenticatedUser.getAttributes().containsKey("email")
                && authenticatedUser.getAttributes().get("email") != null) {
            
        	for(String k : authenticatedUser.getAttributes().keySet()){
        		logger.info("[RewardsAdapter](getEmail)-->attr:"+k);
        	}
        	
            email = (String) authenticatedUser.getAttributes().get("email");
            if (email == null) {
                return "";
            }
                
            email = email.trim();
            if (email.equalsIgnoreCase("null") || email.equalsIgnoreCase("")) {
                return "";
            }
        }else{
        	logger.info("[RewardsAdapter](getEmail)-->Auth user:"+authenticatedUser);
        }
        return email;
    }
    
    private String getDocNumber(AdapterSecurityContext securityContext, String email) throws NotFoundException {
    	logger.info("[RewardsAdapter](getDocumentNumber) Getting document number");
    	
		String docNumber = null;
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		logger.info("[RewardsAdapter](getDocumentNumber)-->AuthUser::: "+user);
		
		if( email == null || "".equals(email) ){
    		logger.info("[RewardsAdapter](getDocumentNumber)-->Email null ");
    		email = this.getEmail(user);
		}
		logger.info("[RewardsAdapter](getDocumentNumber)-->Email for get user profile:::" + email);
		
		if( email != null && !"".equals(email) ){
			logger.info("[RewardsAdapter](getDocumentNumber)-->Email not null " + email);
			UserProfile userProfile = this.getUserProfileApiService().profileGet(email,REGION,VERSION);
			docNumber = (userProfile == null ? "" : userProfile.getDocument());
		}
		logger.info("[RewardsAdapter](getDocumentNumber)-->Document number received:::"+docNumber);
		
		return docNumber;
	}
    
    
    @GET
    @Path("/health")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(
    	value = "", 
		notes = "", 
		response = void.class, 
		authorizations = {
        	
    		}, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "200 OK", response = void.class) })
    @OAuthSecurity(enabled=false)
    public Response getHealth() throws NotFoundException {
    	
    	JSONObject obj = new JSONObject();
		
		obj.put("status", "UP");
		
		JSONObject objDisk = new JSONObject();
		objDisk.put("status", "UP");
		objDisk.put("total", "0");
		objDisk.put("free", "0");
		objDisk.put("threshold", "0");
		
		obj.put("diskSpace", objDisk);
			
    	
        return Response.status(Response.Status.OK).entity(obj).build();
    }
    
}
