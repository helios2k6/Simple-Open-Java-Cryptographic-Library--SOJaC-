package com.nlogneg.SOJaC.engine;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

public class CipherUtils {

	public static final String DEFAULT_SECURE_RANDOM = "SHA1PRNG";
	public static final String DEFAULT_PADDING = "PKCS5Padding";
	public static final String NO_PADDING = "NoPadding";
	
	public static final int getLargestKeySize(String algorithm){
		int max = -1;
		try{
			max = Cipher.getMaxAllowedKeyLength(algorithm);
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return max;
	}
}
