package com.app5.tnt.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptoUtil {
	
	private static SecureRandom random = new SecureRandom();

	public static String generateRandomString(int size) {
		return new BigInteger(size, random).toString(32);
	}
	
	public static byte[] crypt(String input) {
		try {
			MessageDigest digest;
			digest = MessageDigest.getInstance("SHA-256");
			return digest.digest(input.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String generateRandomPassword(int size) {
		return generateRandomString(size);
	}
	
	public static boolean comparePassword(byte[] password1, byte[] password2)
	{
		return MessageDigest.isEqual(password1, password2);
	}
	
}
