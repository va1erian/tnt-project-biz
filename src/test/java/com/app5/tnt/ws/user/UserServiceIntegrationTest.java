package com.app5.tnt.ws.user;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app5.tnt.inject.Context;
import com.app5.tnt.inject.ContextManager;
import com.app5.tnt.jpa.service.DBInit;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.jpa.service.ServiceTestFactory;
import com.app5.tnt.ws.user.jaxb.output.GetUserProfileResInfo;

public class UserServiceIntegrationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		if (ContextManager.contextNull()) {
			ContextManager.setContext(new Context() {
				public Service getService() {
					return ServiceTestFactory.getIntegrationTesService();
				}
			});
			DBInit.fillDB(ServiceTestFactory.getIntegrationTesService());
		}
	}

	@Test
	public void testGetUserProfile() {
		UserService service = new UserService();
		GetUserProfileResInfo getten = service.getUserProfile(DBInit.u1.getId() + "")
				.readEntity(GetUserProfileResInfo.class);
		// assertEquals(getten.getBirthDate(),
		// DBInit.u1.getDateOfBirth().getTime());
		assertEquals(getten.getFirstName(), DBInit.u1.getFirstName());
		assertEquals(getten.getLastName(), DBInit.u1.getLastName());
		assertEquals(getten.getEmail(), DBInit.u1.getEmail());
		assertEquals(getten.getGender(), DBInit.u1.getGender());
		assertEquals(getten.getId(), DBInit.u1.getId() + "");

	}

	// @Test
	public void testUpdateUserProfile() {
		fail("Not yet implemented");
	}

}
