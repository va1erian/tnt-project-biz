package com.app5.tnt.ws.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUserProfile(UpdateUserProfileReqInfo userProfile) {
		try	{
			System.out.println(userProfile.getLastName());

			if (userProfile == null) return Response.serverError().entity("userProfil NULL").build();
			Service jpaServ = new Service();
//			System.out.println("userId :"+userProfile.getIdUser());
//			Long longId = Long.parseLong(userProfile.getIdUser());
//			System.out.println("longId : "+longId);
//			User userInfo = jpaServ.findById(User.class, longId);
			
//			boolean isInDataBase = (userInfo != null);
			
			// Check if the user exist in the database
//			if(isInDataBase) {
//				userInfo.setFirstName(userProfile.getFirstName());
//				userInfo.setLastName(userProfile.getLastName());
//				userInfo.setBirthOfDate(userProfile.getBirthDate());
//				userInfo.setGender(userProfile.getGender().charAt(0));
//				userInfo.setEmail(userProfile.getEmail());
//				jpaServ.commit(CommitOperation.Update, userInfo);
//				
				return Response
						.ok("{result:1}", 
								MediaType.TEXT_PLAIN).build();
//			}
//			else {
//				return Response
//						.ok("{result:0}", 
//								MediaType.TEXT_PLAIN).build();
//			}
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.serverError().entity("Fatal Error").build();
		}
	}
	
	@Path("/createUser")
	@POST
	@Produces("text/plain")
	public Response createUser( UpdateUserProfileReqInfo newUser) {
		try {
			if (newUser == null) return Response.serverError().entity("newUser NULL").build();
			else{
				System.out.println("ça marche putain "+newUser.getFirstName());
				return Response.ok("yess", MediaType.TEXT_PLAIN).build();
			}
//			boolean isInDataBase = false;
//			boolean validAccount = false;
////			Map<String, Object> param = new HashMap<String, Object>();
////			param.put(User.EmailParameterName, newUser.getEmail());
////			User userInfo = service.getSingleResult(User.class, User.GetByEmailQueryName, param);
//			
//			// Check if the user is in database
////			if(userInfo != null)
////			{
////				isInDataBase = true;
////				validAccount = userInfo.getEmailValitated();
////			}
//			
//			// Check if the user is in database
//			if(!isInDataBase) {
//				// Check if the user has validate his/her account
//				if(validAccount) {
//					return Response.ok("{result:0}", MediaType.TEXT_PLAIN).build();
//				}
//				else {
//					
//					// Resend validation mail 
////					mailUtility.sendEmail(MailUtility.INSCRIPTION_MAIL, userInfo.getEmail(), parametersValues);
//					return Response.ok("{result:2}", MediaType.TEXT_PLAIN).build();
//				}
//				
//			}
//			else {
//				
//				// Send validation mail
////				mailUtility.sendEmail(MailUtility.INSCRIPTION_MAIL, userInfo.getEmail(), parametersValues);
//				return Response.ok("{result:1}", MediaType.TEXT_PLAIN).build();
//			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
		
	}

}
