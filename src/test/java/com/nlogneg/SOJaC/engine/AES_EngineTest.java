package com.nlogneg.SOJaC.engine;

import junit.framework.TestCase;

public class AES_EngineTest extends TestCase{
	
	private AES_Engine engine;
	
	private static final String MESSAGE_SHORT = "SOJaC";
	
	private static final String MESSAGE_LONG = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
	
	private static String generateLongString(){
		
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