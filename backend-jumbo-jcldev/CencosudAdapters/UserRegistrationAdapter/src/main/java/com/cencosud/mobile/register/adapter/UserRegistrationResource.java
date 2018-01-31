package com.cencosud.mobile.register.adapter;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.cencosud.mobile.register.dto.CreateUserRequest;
import com.cencosud.mobile.register.dto.SendCodeRequest;
import com.cencosud.mobile.register.model.GenericResponse;
import com.cencosud.mobile.register.model.enums.HttpResponseStatus;
import com.cencosud.mobile.register.service.UserRegistrationApiService;
import com.ibm.mfp.adapter.api.OAuthSecurity;

import io.swagger.annotations.Api;

/**
 * 
 * <h1>RegisterResource</h1>
 * <p>
 * Servicios expuestos para el adapter
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Sep 1, 2017
 */
@Api(value = "User Registration Resource")
@Path("/r2/v1")
public class UserRegistrationResource {
	
	private UserRegistrationApiService delegate;
	static Logger logger = Logger.getLogger(UserRegistrationResource.class.getName());
	
	/**
	 * @return the delegate
	 */
	public UserRegistrationApiService getDelegate() {
		return delegate;
	}

	/**
	 * @param delegate the delegate to set
	 */
	public void setDelegate(UserRegistrationApiService delegate) {
		this.delegate = delegate;
	}

	@Path("/userRegistration/send")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false)
	public Response sendCode(@PathParam("region") String region, SendCodeRequest request) {
		logger.info("Servicio SEND: ");
		logger.info("Objeto request recibido: "+request.toString());
		GenericResponse response = delegate.sendCode(request);
		if(response.getError() == null) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		return Response.status(Response.Status.fromStatusCode(HttpResponseStatus.findCodeHttp(response.getError().getCode()))).entity(response).build();
	}
	
	@Path("/userRegistration/setPassword")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false)
	public Response registerUser(@PathParam("region") String region, CreateUserRequest request) {
		logger.info("Servicio SETPASSWORD: ");
		logger.info("Objeto request recibido: "+request.toString());
		GenericResponse response = delegate.registerUser(request);
		if(response.getError() == null) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		return Response.status(Response.Status.fromStatusCode(HttpResponseStatus.findCodeHttp(response.getError().getCode()))).entity(response).build();
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
    public Response getHealth() {
        
        JSONObject obj = new JSONObject();
        
        obj.put("status", "UP");
        
        JSONObject objDisk = new JSONObject();
        objDisk.put("status", "UP");
        objDisk.put("total", "0");
        objDisk.put("free", "0");
        objDisk.put("threshold", "0");
        
        obj.put("diskSpace", objDisk);
            
        
        return Response.status(Response.Status.OK).entity(obj.toString()).build();
    }
}
