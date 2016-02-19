package com.app5.tnt.ws.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.service.CommitOperation;
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
	
	/**
	 * Attention, la date en JSON doit être au bon format (dd-MM-yyyy)
	 * @param userProfile
	 * @return
	 */
	@Path("/updateUserProfile")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUserProfile(UpdateUserProfileReqInfo userProfile) {
		try	{
			if (userProfile == null) return Response.serverError().entity("JSON Error").build();
			Service jpaServ = new Service();
			Long longId = Long.parseLong(userProfile.getIdUser());
			User userInfo = jpaServ.findById(User.class, longId);
			boolean isInDataBase = (userInfo != null);
			
			// Check if the user exist in the database
			if(isInDataBase) {
				userInfo.setFirstName(userProfile.getFirstName());
				userInfo.setLastName(userProfile.getLastName());
				userInfo.setBirthOfDate(userProfile.getBirthDate());
				userInfo.setGender(userProfile.getGender().charAt(0));
				userInfo.setEmail(userProfile.getEmail());
				jpaServ.commit(CommitOperation.Update, userInfo);
				
				return Response
						.ok("{result:1}", 
								MediaType.TEXT_PLAIN).build();
			}
			else {
				return Response
						.ok("{result:0}", 
								MediaType.TEXT_PLAIN).build();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.serverError().entity("Fatal Error").build();
		}
	}
}
