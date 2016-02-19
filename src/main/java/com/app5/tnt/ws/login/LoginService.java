package com.app5.tnt.ws.login;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.utils.MailUtility;
import com.app5.tnt.ws.login.jaxb.LoginUserInfo;
import com.app5.tnt.ws.login.jaxb.input.ValidateUserReqInfo;
import com.app5.tnt.ws.login.jaxb.input.newUserReqInfo;

@Path("/login")
public class LoginService {
	
	private Service service;
	private MailUtility mailUtility;;
	
	public LoginService() {
		//service = new Service();
		//mailUtility = new MailUtility();
	}
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
	public Response createUser(@FormParam("input") newUserReqInfo newUser) {
		try {
			boolean isInDataBase = false;
			boolean validAccount = false;
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put(User.EmailParameterName, newUser.getEmail());
//			User userInfo = service.getSingleResult(User.class, User.GetByEmailQueryName, param);
			
			// Check if the user is in database
//			if(userInfo != null)
//			{
//				isInDataBase = true;
//				validAccount = userInfo.getEmailValitated();
//			}
			
			// Check if the user is in database
			if(!isInDataBase) {
				// Check if the user has validate his/her account
				if(validAccount) {
					return Response.ok("{result:0}", MediaType.TEXT_PLAIN).build();
				}
				else {
					
					// Resend validation mail 
//					mailUtility.sendEmail(MailUtility.INSCRIPTION_MAIL, userInfo.getEmail(), parametersValues);
					return Response.ok("{result:2}", MediaType.TEXT_PLAIN).build();
				}
				
			}
			else {
				
				// Send validation mail
//				mailUtility.sendEmail(MailUtility.INSCRIPTION_MAIL, userInfo.getEmail(), parametersValues);
				return Response.ok("{result:1}", MediaType.TEXT_PLAIN).build();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
		
	}
	@Path("/validateUser")
	@POST
	@Produces("text/plain")
	@Consumes("application/json")
	public Response validateUser(@FormParam("input") ValidateUserReqInfo validateUser) {
		try {
			boolean isInDataBase = false;
			boolean validAccount = false;
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put(User.EmailParameterName, validateUser.getEmail());
			// WARNING don't forget to convert the userId
//			param.put(User.IdParameterName, validateUser.getUserId());
//			User userInfo = service.getSingleResult(User.class, User.GetByIdAndEmailQueryName, param);
			
			// Check if the user is in database
//			if(userInfo != null)
//			{
//				isInDataBase = true;
//				validAccount = userInfo.getEmailValitated();
//			}
			
			// Check if the user is in database
			if(isInDataBase) {
				// Check if the user has validate his/her account
				if(validAccount) {
					return Response.ok("{result:0}", MediaType.TEXT_PLAIN).build();
				}
				else {
					return Response.ok("{result:1}", MediaType.TEXT_PLAIN).build();
				}
			}
			else {
				return Response.serverError().status(500).entity("Utilisateur non trouvé en base").build();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}
}
