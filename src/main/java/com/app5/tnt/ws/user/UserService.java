package com.app5.tnt.ws.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.ws.adapter.DateAdapter;
import com.app5.tnt.ws.user.jaxb.input.UpdateUserProfileReqInfo;
import com.app5.tnt.ws.user.jaxb.output.GetUserProfileResInfo;

@Path("/user")
public class UserService {
	
	@Path("/getUserProfile")
	@GET
	@Consumes("text/plain")
	@Produces("application/json")
	public Response getUserProfile(@QueryParam("userId") String userId) {
		try	{
			boolean isInDataBase = false;
			GetUserProfileResInfo user = new GetUserProfileResInfo();
			// Check if the user exist in the database
			if(isInDataBase) {
				
				user.setFirstName("Johan");
				user.setLastName("Douillard");
				DateAdapter da = new DateAdapter();
				user.setBirthDate(da.unmarshal("14-01-1993"));
				user.setGender("M");
				user.setEmail("test@test.fr");
				user.setPassword("test");
				
				return Response
						.ok(user, 
								MediaType.APPLICATION_JSON).build();
			}
			else {
				return Response
						.ok(user, 
								MediaType.APPLICATION_JSON).build();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}
	@Path("/updateUserProfile")
	@PUT
	@Consumes("application/json")
	public Response updateUserProfile(@FormParam("updateUser") UpdateUserProfileReqInfo userProfile) {
		try	{
			boolean isInDataBase = false;
			// Check if the user exist in the database
			if(isInDataBase) {
				
				return Response
						.ok("{update:true}", 
								MediaType.APPLICATION_JSON).build();
			}
			else {
				return Response
						.ok("{update:true}", 
								MediaType.APPLICATION_JSON).build();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}

}
