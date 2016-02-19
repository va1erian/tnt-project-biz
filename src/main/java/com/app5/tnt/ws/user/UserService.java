package com.app5.tnt.ws.user;

import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.service.Service;
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
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUserProfile(UpdateUserProfileReqInfo userProfile) {
		
		//Just to check the bindings conversion
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = formatter.format(userProfile.getBirthDate());
			
		//Log
			System.out.println("userId    : "  +  userProfile.getIdUser());
			System.out.println("FirstName : "  +  userProfile.getFirstName());
			System.out.println("LastName  : "  +  userProfile.getLastName());
			System.out.println("DofBirth  : "  +  formattedDate);
			System.out.println("Email     : "  +  userProfile.getEmail());
			System.out.println("Gender    : "  +  userProfile.getGender());
			System.out.println("Password  : "  +  userProfile.getPassword());
		
		try	{
			boolean isInDataBase = false;
			// Check if the user exist in the database
			if(isInDataBase) {
				
				return Response.ok("{update:true}", MediaType.APPLICATION_JSON).build();
			}
			else {
				return Response.ok("{update:true}", MediaType.APPLICATION_JSON).build();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}

}
