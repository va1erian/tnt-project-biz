package com.app5.tnt.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptoUtil {
	
	private static SecureRandom random = new SecureRandom();
	private static String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	public static String generateRandomString(int size) {
		String randomString = new String();
		
		for(int i = 0; i < size; i++)
		{
			int randomNum = 1 + (int)(Math.random() * 62); 
			randomString += ALPHA_NUMERIC_STRING.charAt(randomNum);
		}
		return randomString;
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
