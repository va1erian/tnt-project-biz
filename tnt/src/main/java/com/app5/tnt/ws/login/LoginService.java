package com.app5.tnt.ws.login;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.ws.login.jaxb.LoginUserInfo;

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
}
