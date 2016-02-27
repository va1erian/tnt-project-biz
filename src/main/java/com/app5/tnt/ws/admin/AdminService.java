package com.app5.tnt.ws.admin;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/admin")
public class AdminService {
	
	Logger log = LoggerFactory.getLogger(AdminService.class);
	
	@GET
	@Path("/ping")
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping( ) {
		Date now = new Date();
		log.info("Server ping at : " + now.toString());
		
		return Response.ok("Success", MediaType.TEXT_PLAIN).build();
	}
}
