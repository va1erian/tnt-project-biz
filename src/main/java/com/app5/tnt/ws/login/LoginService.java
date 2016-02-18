package com.app5.tnt.ws.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.ws.login.jaxb.LoginUserInfo;
import com.app5.tnt.ws.login.jaxb.input.newUserReqInfo;


@Path("/login")
public class LoginService {
	
	@GET
	@Produces("application/json")
	public Response sayHelloPlain( ) {
		LoginUserInfo logineUserInfo = new LoginUserInfo();
		logineUserInfo.setNom("SEMGHOUNI");
		logineUserInfo.setPrenom("Younes");
		
		return Response.ok(logineUserInfo, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/createUser")
	@POST
	@Produces("text/plain")
	@Consumes("application/json")
	public Response createUser(@FormParam("newUser") newUserReqInfo newUser) {
		try {
			boolean isInDataBase = false;
			boolean validAccount = false;
			
			// Check if user is in database
			
			if(isInDataBase) {
				return Response.ok("{result:0}", MediaType.TEXT_PLAIN).build();
			}
			else {
				if(validAccount) {
					return Response.ok("{result:1}", MediaType.TEXT_PLAIN).build();
				}
				else {
					return Response.ok("{result:2}", MediaType.TEXT_PLAIN).build();
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
		
	}
}
