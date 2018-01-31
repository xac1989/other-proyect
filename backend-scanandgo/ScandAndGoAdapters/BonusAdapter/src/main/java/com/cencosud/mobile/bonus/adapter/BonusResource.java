package com.cencosud.mobile.bonus.adapter;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.cencosud.middleware.bonus.model.BonusResponse;
import com.cencosud.mobile.bonus.exceptions.NotFoundException;
import com.cencosud.mobile.bonus.service.BonusApiService;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Bonus Adapter Resource")
@Path("/r1/v1/bonus")
public class BonusResource {
	static Logger logger = Logger.getLogger(BonusResource.class.getName());

	@Context
	private AdapterSecurityContext securityContext;

	private BonusApiService bonusService;

	@ApiOperation(value = "Returns product attributes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Bonus attributes returned") })
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{doctype}")
	@OAuthSecurity(enabled = true, scope = "customer")
	public BonusResponse product(@PathParam("doctype") String doctype, @QueryParam("numdoc") String numdoc) throws NotFoundException {
		return bonusService.productGet(doctype, numdoc);
	}
	
	@ApiOperation(value = "Returns product attributes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Bonus attributes returned") })
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	@Path("unsecure/{doctype}")
	public BonusResponse productUnsecure(@PathParam("doctype") String doctype, @QueryParam("numdoc") String numdoc) throws NotFoundException {
		return bonusService.productGet(doctype, numdoc);
	}

	/**
	 * @return the bonusService
	 */
	public BonusApiService getBonusService() {
		return bonusService;
	}

	/**
	 * @param bonusService the bonusService to set
	 */
	public void setBonusService(BonusApiService bonusService) {
		this.bonusService = bonusService;
	}
	
	
}
