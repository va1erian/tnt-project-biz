package com.app5.tnt.ws.user;

import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.ws.user.jaxb.output.GetUserProfileResInfo;

@Path("/user")
public class UserService {
	
	@Path("/getUserProfile")
	@GET
	@Consumes("text/plain")
	@Produces("application/json")
	public Response getUserProfile(@WebParam(name="userId") String userId) {
		try	{
			boolean isInDataBase = false;
			// Check if the user exist in the database
			if(isInDataBase) {
				return Response
						.ok(new GetUserProfileResInfo("Johan", "Douillard", 
								"14-01-1993", "M", "test@t.fr", "test"), 
								MediaType.APPLICATION_JSON).build();
			}
			else {
				return Response
						.ok(new GetUserProfileResInfo(), 
								MediaType.APPLICATION_JSON).build();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}

}
