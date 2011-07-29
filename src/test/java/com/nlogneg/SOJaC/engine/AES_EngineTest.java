package com.nlogneg.SOJaC.engine;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import junit.framework.TestCase;

import org.apache.commons.codec.binary.Base64;

public class AES_EngineTest extends TestCase{
	
	private AES_Engine engine;
	
	private static final String MESSAGE_SHORT = "SOJaC";
	
	private static String generateLongString(){
		Random random = new Random();
		byte[] randomByteArray = new byte[128];
		random.nextBytes(randomByteArray);
		
		Base64 encoder = new Base64();
		
		try {
			String randomString = new String(encoder.encode(randomByteArray), "UTF-8");
			return randomString;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void setUp(){
		engine = AES_Engine.getInstance();
	}
	
	//Test encryption with all modes
	public void testNoBlockEncryption(){
		
	}
	
	public void testECBEncryption(){
		
	}
	
	public void testCBCEncryption(){
		
	}
	
	public void testCFBEncryption(){
		
	}
	
	public void testOFBEncryption(){
		
	}
	
	public void testPCPBEncryption(){
		
	}
}