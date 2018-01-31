package com.cencosud.mobile.profile.adapter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.middleware.category.model.UserProfile;
import com.ibm.mfp.adapter.api.OAuthSecurity;

import io.swagger.annotations.ApiParam;

@Path("/profile")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the profile API")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-26T10:53:29.306-03:00")
@Component
public class ProfileApi  {
   @Autowired
   private ProfileApiService delegate = null;
   
   private ProfileApiService getProfileApiService()  {

   		return delegate;
   }
   
    @GET
    
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
    public Response profileGet(@ApiParam(value = "User id",required=true) @QueryParam("id") String id
, @Context SecurityContext securityContext)
    throws NotFoundException {
        return getProfileApiService().profileGet(id,securityContext);
    }
    @POST
    
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
    public Response profilePost( @Context SecurityContext securityContext)
    throws NotFoundException {
        return getProfileApiService().profilePost(securityContext);
    }
    @PUT
    
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
    public Response profilePut(
						@ApiParam(value = "" ) UserProfile profile, @Context SecurityContext securityContext)
    throws NotFoundException {
        return getProfileApiService().profilePut(profile,securityContext);
    }
}
