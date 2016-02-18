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
	 * Test the case for inscription mail
	 */
	@Test
	public void mailUtilitySendEmailTestOk() {
		MailUtility mailer = new MailUtility();

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("name", "Sebastien testeur");
		mailer.sendEmail(MailUtility.INSCRIPTION_MAIL,
				"sebastien.ferrer@u-psud.fr", paramsValues);

		assert (true);
	}

	/**
	 * Test the case for inscription mail with a hashmap that does not exactly
	 * match the parameters required
	 */
	@Test
	public void mailUtilitySendEmailTestHashMapNok() {
		MailUtility mailer = new MailUtility();

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("name", "Sebastien testeur");
		paramsValues.put("name2", "Sebastien");
		paramsValues.put("name3", "Sebastien");
		mailer.sendEmail(MailUtility.INSCRIPTION_MAIL,
				"sebastien.ferrer@u-psud.fr", paramsValues);

		assert (true);
	}

	/**
	 * Test the case for inscription mail with a template path that does not
	 * match any existing template
	 */
	@Test
	public void mailUtilitySendEmailTestTemplatePathNok() {
		MailUtility mailer = new MailUtility();

		HashMap<String, String> paramsValues = new HashMap<String, String>(1);
		paramsValues.put("name", "Sebastien testeur");

		boolean expectedBehavior = false;
		try {
			mailer.sendEmail("non_existing_path", "sebastien.ferrer@u-psud.fr",
					paramsValues);
		} catch (RuntimeException e) {
			expectedBehavior = true;
		}

		assertTrue(expectedBehavior);
	}
}
