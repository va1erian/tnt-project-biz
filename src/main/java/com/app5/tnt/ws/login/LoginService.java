package com.app5.tnt.ws.login;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.service.CommitOperation;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.jpa.service.ServiceFactory;
import com.app5.tnt.utils.CryptoUtil;
import com.app5.tnt.utils.MailUtil;
import com.app5.tnt.ws.login.jaxb.input.AuthentificateReqInfo;
import com.app5.tnt.ws.login.jaxb.input.ValidateEmailReqInfo;
import com.app5.tnt.ws.login.jaxb.output.AuthentificateResInfo;
import com.app5.tnt.ws.login.jaxb.output.UserData;
import com.app5.tnt.ws.login.jaxb.input.CreateUserReqInfo;

@Path("/login")
public class LoginService {
	
	Logger log = LoggerFactory.getLogger(LoginService.class);
	
	private Service service;
	private MailUtil mailUtil;;
	
	public LoginService() {
		service = ServiceFactory.getDefaultService();
		mailUtil = MailUtil.getInstance();
	}
	
	@Path("/createUser")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(CreateUserReqInfo newUser) {
		try {
			boolean isInDataBase = false;
			boolean validAccount = false;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(User.EmailParameterName, newUser.getEmail());
			User userInfo = service.getSingleResult(User.class, User.GetByEmailQueryName, param);
			
			// Check if the user is in database
			if(userInfo != null)
			{
				isInDataBase = true;
				validAccount = userInfo.isEmailValidated();
			}
			
			// Check if the user is in database
			if(isInDataBase) {
				// Check if the user has validate his/her account
				if(validAccount) {
					return Response.ok("{result:0}", MediaType.TEXT_PLAIN).build();
				}
				else {
					// Resend validation mail 
					Map<String, String> emailParams = new HashMap<String, String>();
					emailParams.put("LASTNAME", userInfo.getLastName());
					emailParams.put("FIRSTNAME", userInfo.getFirstName());
					emailParams.put("EMAIL", userInfo.getEmail());
					String jsonToCrypt = "{\"email\":\"" + userInfo.getEmail() + "\", "
										 + "\"idUser\":\"" + userInfo.getId()    + "\"}";
					String confirmationUrl = "http://toplel.xyz:8080" + "/confirmation" + "?data="  
											+ new String(CryptoUtil.cryptSHA256(jsonToCrypt), "UTF-8");
					emailParams.put("URL", confirmationUrl);
					mailUtil.sendEmail(MailUtil.CONFIRM_EMAIL, userInfo.getEmail(), emailParams);
					return Response.ok("{result:2}", MediaType.TEXT_PLAIN).build();
				}
			}
			else {
				// Create the new user in the database
				User u = new User();
				u.setEmail(newUser.getEmail());
				u.setBirthOfDate(newUser.getBirthDate());
				u.setEmailValitated(false);
				u.setFirstName(newUser.getFirstName());
				u.setGender(newUser.getGender());
				u.setLastName(newUser.getLastName());
				String passwordEncrypted = new String(CryptoUtil.cryptSHA256(newUser.getPassword()), "UTF-8");
				u.setPassword(passwordEncrypted);
				service.commit(CommitOperation.Persist, u);
				
				// Recover the user created before
				Map<String, Object> newUserParam = new HashMap<String, Object>();
				newUserParam.put(User.EmailParameterName, u.getEmail());
				User newUserInDatabase = service.getSingleResult(User.class, 
																 User.GetByEmailQueryName, 
																 newUserParam);
				
				// Send validation mail
				Map<String, String> emailParams = new HashMap<String, String>();
				emailParams.put("LASTNAME", newUserInDatabase.getLastName());
				emailParams.put("FIRSTNAME", newUserInDatabase.getFirstName());
				emailParams.put("EMAIL", newUserInDatabase.getEmail());
				String jsonToCrypt = "{\"email\":\"" + newUserInDatabase.getEmail() + "\", "
						 		   + "\"idUser\":\"" + newUserInDatabase.getId()    + "\"}";

				String confirmationUrl = "http://toplel.xyz:8080" + "/confirmation" + "?data="  
										+ new String(CryptoUtil.cryptSHA256(jsonToCrypt), "UTF-8");
				
				emailParams.put("URL", confirmationUrl);
				mailUtil.sendEmail(MailUtil.CONFIRM_EMAIL, newUserInDatabase.getEmail(), emailParams);
			
				return Response.ok("{result:1}", MediaType.TEXT_PLAIN).build();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
		
	}
	
	@Path("/validateEmail")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validateEmail(ValidateEmailReqInfo validateUser) {
		try {
			boolean isInDataBase = false;
			boolean validAccount = false;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(User.EmailParameterName, validateUser.getEmail());
			param.put(User.IdParameterName, validateUser.getidUser());
			User userInfo = service.getSingleResult(User.class, User.GetByIdAndEmailQueryName, param);
			
			// Check if the user is in database
			if(userInfo != null)
			{
				isInDataBase = true;
				validAccount = userInfo.isEmailValidated();
			}
			
			// Check if the user is in database
			if(isInDataBase) {
				// Check if the user has validate his/her account
				if(validAccount) {
					return Response.ok("{result:0}", MediaType.TEXT_PLAIN).build();
				}
				else {
					userInfo.setEmailValitated(true);
					service.commit(CommitOperation.Update, userInfo);
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
	
	@Path("/authentificate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authentificate(AuthentificateReqInfo authentificate) {
		try {
			boolean isInDataBase = false;
			boolean isValidAccount = false;
			boolean isCorrectLoginAndPassword = false;
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(User.EmailParameterName, authentificate.getEmail());
			User userInfo = service.getSingleResult(User.class, User.GetByEmailQueryName, param);
			
			// Check if the user is in database
			if(userInfo != null)
			{
				isInDataBase = true;
				isValidAccount = userInfo.isEmailValidated();
				if(authentificate.getEmail().equals(userInfo.getEmail()) && 
				   CryptoUtil.comparePassword(authentificate.getPassword().getBytes("UTF-8"), 
								   			  userInfo.getPassword().getBytes("UTF-8"))) {
					isCorrectLoginAndPassword = true;
				}
			}
			
			AuthentificateResInfo result = new AuthentificateResInfo();
			UserData userData = new UserData();
			// Check if the user is in database
			if(isInDataBase && isValidAccount && isCorrectLoginAndPassword) {
				result.setSuccess((short)1);
				userData.setFirstName(userInfo.getFirstName());
				userData.setLastName(userInfo.getLastName());
				userData.setBirthDate(userInfo.getBirthOfDate());
				userData.setGender(userInfo.getGender());
				userData.setEmail(userInfo.getEmail());
				userData.setIdUser(userInfo.getId());
				result.setUser(userData);
				return Response.ok(result, MediaType.APPLICATION_JSON).build();
			}
			else {
				result.setSuccess((short)0);
				userData = null;
				result.setUser(userData);
				return Response.ok(result, MediaType.APPLICATION_JSON).build();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}
	
	@Path("/resetPassword")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response resetPassword(@QueryParam("email")String email) {
		try {
			boolean isInDataBase = false;
			boolean isValidAccount = false;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(User.EmailParameterName, email);
			User userInfo = service.getSingleResult(User.class, User.GetByEmailQueryName, param);
			
			// Check if the user is in database
			if(userInfo != null)
			{
				isInDataBase = true;
				isValidAccount = userInfo.isEmailValidated();
			}
			// Check if the user is in database
			if(isInDataBase && isValidAccount) {
				
				// Send the mail
				String readableTemporaryPassword = CryptoUtil.generateRandomPassword(8);
				Map<String, String> params = new HashMap<String, String>();
				params.put("EMAIL", email);
				params.put("NEW_PASSWORD", readableTemporaryPassword);
				mailUtil.sendEmail(MailUtil.PASSWORD_LOST, userInfo.getEmail(), params);
				userInfo.setPassword(new String(CryptoUtil.cryptSHA256(readableTemporaryPassword), "UTF-8"));
				service.commit(CommitOperation.Update, userInfo);
				
				return Response.ok("{result:1}", MediaType.TEXT_PLAIN).build();
			}
			else {
				return Response.ok("{result:0}", MediaType.TEXT_PLAIN).build();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return Response.serverError().entity("Error").build();
		}
	}
	
}
