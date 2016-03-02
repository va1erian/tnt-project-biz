package com.app5.tnt.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app5.tnt.utils.CryptoUtil;

public class CryptoUtilUnitTest {

	@Test
	public void testGenerateRandomString() 
	{
		String generated = CryptoUtil.generateRandomString(10);
		assertEquals("Generated string size NOK", 10, generated.length());
	}

	@Test
	public void testCryptSHA256() {
		assertNotNull(CryptoUtil.cryptSHA256("test"));
	}

	@Test
	public void testGenerateRandomPassword() {
		String generated = CryptoUtil.generateRandomPassword(10);
		assertEquals("Generated string size NOK", 10, generated.length());
	}

	@Test
	public void testComparePassword() {
		byte[] password1 = CryptoUtil.cryptSHA256("password1");
		byte[] password2 = CryptoUtil.cryptSHA256("password2");
		byte[] password3 = CryptoUtil.cryptSHA256("password1");
		
		assertEquals(false, CryptoUtil.comparePassword(password1, password2));
		assertEquals(true, CryptoUtil.comparePassword(password1, password3));
	}

}
