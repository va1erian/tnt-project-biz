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

import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.service.Service;
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
			Service jpaServ = new Service();
			Long longId = Long.parseLong(userId);
			User userInfo = jpaServ.findById(User.class, longId);
			
			boolean isInDataBase = (userInfo != null);
			GetUserProfileResInfo user = new GetUserProfileResInfo();
			
			// Check if the user exist in the database
			if(isInDataBase) {
				user.setFirstName(userInfo.getFirstName());
				user.setLastName(userInfo.getLastName());
				//DateAdapter da = new DateAdapter();
				user.setBirthDate(userInfo.getBirthOfDate());
				user.setGender(userInfo.getGender().toString());
				user.setEmail(userInfo.getEmail());
				user.setId(userId);
				
				return Response
						.ok(user, 
								MediaType.APPLICATION_JSON).build();
			}
			else {
				// TODO : check message error
				return Response
						.serverError().entity("Error").build();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return Response.serverError().entity("Fatal Error").build();
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
