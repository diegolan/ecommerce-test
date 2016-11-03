package com.lanfranchi.ecommercetest.security;

import java.security.MessageDigest;

import org.jboss.security.Base64Encoder;

public class PassGenerator {
	
	public static String generate(String rawPassword){
		try {
			byte[] hash = MessageDigest.getInstance("SHA-512").digest(rawPassword.getBytes());
			return Base64Encoder.encode(hash);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
