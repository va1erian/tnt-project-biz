package com.app5.tnt.utils;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

/**
 * Class handling tests for the MailUtility class
 * 
 * @author Robin
 *
 */
public class MailUtilityTest {

	/**
	 * Test the case for confirm_inscription & password_lost template
	 */
	@Test
	public void mailUtilitySendEmailTestOk() {

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("FIRSTNAME", "Robin");
		paramsValues.put("LASTNAME", "Delgado");
		paramsValues.put("EMAIL", "test@gmail.com");
		paramsValues.put("URL", "http://lemonde.fr");
		MailUtility.getInstance().sendEmail(MailUtility.CONFIRM_EMAIL,
				"fausseadresse@mail.fr", paramsValues);

		assert (true);
		
		paramsValues = new HashMap<String, String>(1);
		paramsValues.put("EMAIL", "test@test.fr");
		paramsValues.put("NEW_PASSWORD", "coucou91");

		MailUtility.getInstance().sendEmail(MailUtility.PASSWORD_LOST,
				"fausseadresse@mail.fr", paramsValues);
		
		assert (true);
	}

	/**
	 * Test the case with a hashmap that does not exactly
	 * match the parameters required
	 */
	@Test
	public void mailUtilitySendEmailTestHashMapNok() {

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("name", "Sebastien testeur");
		paramsValues.put("name2", "Sebastien");
		paramsValues.put("name3", "Sebastien");
		MailUtility.getInstance().sendEmail(MailUtility.CONFIRM_EMAIL,
				"sebastien.ferrer@u-psud.fr", paramsValues);

		assert (true);
	}

	/**
	 * Test the case with a template path that does not
	 * match any existing template
	 */
	@Test
	public void mailUtilitySendEmailTestTemplatePathNok() {

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("name", "Sebastien testeur");

		boolean expectedBehavior = false;
		try {
			MailUtility.getInstance().sendEmail("non_existing_path",
					"sebastien.ferrer@u-psud.fr", paramsValues);
		} catch (RuntimeException e) {
			expectedBehavior = true;
		}

		assertTrue(expectedBehavior);
	}
}
