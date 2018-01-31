package com.cencosud.mobile.saleschannel.adapter;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.mobile.saleschannel.service.SalesChannelApiService;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * 
 * 
 * <h1>SalesChannelResource</h1>
 * <p>
 * Servicios publicados por el adapter
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Jul 21, 2017
 */
@Api(value = "Sales Channel Resource")
@Path("/r2/v1/saleschannel")
@Component
public class SalesChannelResource {

	@Autowired
	private SalesChannelApiService delegate;

	/**
	 * @return the delegate
	 */
	public SalesChannelApiService getDelegate() {
		return delegate;
	}

	/**
	 * @param delegate
	 *            the delegate to set
	 */
	public void setDelegate(SalesChannelApiService delegate) {
		this.delegate = delegate;
	}

	private final Logger logger = Logger.getLogger(SalesChannelResource.class.toString());

	@Path("/list")
	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@OAuthSecurity(enabled = false)
	public Response getSalesChannelList(@ApiParam(value = "Sales Chanel") @Context AdapterSecurityContext securityContext){
		logger.info("Inicia peticion de Sales Channel");
		return Response.status(Response.Status.OK).entity(delegate.getSalesChannels()).build();
	}
	
	@Path("/regions")
	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@OAuthSecurity(enabled = false)
	public Response getRegionList(@ApiParam(value = "Sales Chanel") @Context AdapterSecurityContext securityContext){
		logger.info("Inicia peticion de Sales Channel");
		return Response.status(Response.Status.OK).entity(delegate.getRegions()).build();
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
