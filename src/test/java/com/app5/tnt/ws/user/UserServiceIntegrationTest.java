package com.app5.tnt.ws.user;

import static org.junit.Assert.*; 

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app5.tnt.jpa.service.DBInit;
import com.app5.tnt.ws.user.jaxb.output.GetUserProfileResInfo;

import test.util.TestContextInitialisator;

public class UserServiceIntegrationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestContextInitialisator.initContext();
	}

	@Test
	public void testGetUserProfile() {
		UserService service = new UserService();
		Response r = service.getUserProfile(DBInit.u1.getId() + "");
		GetUserProfileResInfo getten = (GetUserProfileResInfo)r.getEntity();
		
		assertEquals(r.getStatus(), 200);
		assertEquals(getten.getBirthDate(), DBInit.u1.getDateOfBirth().getTime());
		assertEquals(getten.getFirstName(), DBInit.u1.getFirstName());
		assertEquals(getten.getLastName(), DBInit.u1.getLastName());
		assertEquals(getten.getEmail(), DBInit.u1.getEmail());
		assertEquals(getten.getGender(), DBInit.u1.getGender()+"");
		assertEquals(getten.getId(), DBInit.u1.getId() + "");

	}

	// @Test
	public void testUpdateUserProfile() {
		fail("Not yet implemented");
	}

}
