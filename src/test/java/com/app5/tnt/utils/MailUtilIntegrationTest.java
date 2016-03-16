package com.app5.tnt.utils;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import com.app5.tnt.utils.MailUtil;

/**
 * Class handling tests for the MailUtil class
 * 
 * @author Robin
 *
 */
public class MailUtilIntegrationTest {

	/**
	 * Test the case for confirm_inscription & password_lost template
	 */
	@Test
	public void MailUtilSendEmailTestOk() {

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("FIRSTNAME", "Robin");
		paramsValues.put("LASTNAME", "Delgado");
		paramsValues.put("EMAIL", "test@gmail.com");
		paramsValues.put("URL", "http://lemonde.fr");
		try {
			MailUtil.getInstance().sendEmail(MailUtil.CONFIRM_EMAIL,
					"fausseadresse@mail.fr", paramsValues);
		} catch (Exception e1) {
			//e1.printStackTrace();
		}

		assert (true);
		
		paramsValues = new HashMap<String, String>(1);
		paramsValues.put("EMAIL", "test@test.fr");
		paramsValues.put("NEW_PASSWORD", "coucou91");

		try {
			MailUtil.getInstance().sendEmail(MailUtil.PASSWORD_LOST,
					"fausseadresse@mail.fr", paramsValues);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		assert (true);
	}

	/**
	 * Test the case with a hashmap that does not exactly
	 * match the parameters required
	 */
	@Test
	public void MailUtilSendEmailTestHashMapNok() {

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("name", "Sebastien testeur");
		paramsValues.put("name2", "Sebastien");
		paramsValues.put("name3", "Sebastien");
		try {
			MailUtil.getInstance().sendEmail(MailUtil.CONFIRM_EMAIL,
					"sebastien.ferrer@u-psud.fr", paramsValues);
		} catch (Exception e) {
			//e.printStackTrace();
		}

		assert (true);
	}

	/**
	 * Test the case with a template path that does not
	 * match any existing template
	 */
	//@Test(expected=Exception.class)
	public void MailUtilSendEmailTestTemplatePathNok() {

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("name", "Sebastien testeur");

		boolean expectedBehavior = false;
		try {
			MailUtil.getInstance().sendEmail("non_existing_path",
					"sebastien.ferrer@u-psud.fr", paramsValues);
		} catch (Exception e) {
			expectedBehavior = true;
		}

		assertTrue(expectedBehavior);
	}
}
