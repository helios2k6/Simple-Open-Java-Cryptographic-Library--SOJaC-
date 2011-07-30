package com.nlogneg.SOJaC.engine;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import junit.framework.TestCase;

import org.apache.commons.codec.binary.Base64;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.objects.EncryptedResult;

public class AES_EngineTest extends TestCase{

	private AES_Engine engine;

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

	public void testECBEncryption(){
		try {
			SecureRandom random = SecureRandom.getInstance(CipherUtils.DEFAULT_SECURE_RANDOM);

			byte[] secretKey = new byte[1024];

			random.nextBytes(secretKey);
			
			String originalString = generateLongString();
			
			byte[] clearTextBytes = originalString.getBytes();

			ByteBuffer buffer = ByteBuffer.wrap(clearTextBytes);
			ByteBuffer keyBuffer = ByteBuffer.wrap(secretKey);

			//test encryption
			EncryptedResult result = engine.encrypt(buffer, keyBuffer, BlockModeEnum.ECB);

			//test decryption
			ByteBuffer decryptedResult = engine.decrypt(result, keyBuffer);

			String message = new String(decryptedResult.array(), "UTF-8");

			assertTrue(originalString.equals(message));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testCBCEncryption(){
		try {
			SecureRandom random = SecureRandom.getInstance(CipherUtils.DEFAULT_SECURE_RANDOM);

			byte[] secretKey = new byte[1024];

			random.nextBytes(secretKey);
			
			String originalString = generateLongString();
			
			byte[] clearTextBytes = originalString.getBytes();

			ByteBuffer buffer = ByteBuffer.wrap(clearTextBytes);
			ByteBuffer keyBuffer = ByteBuffer.wrap(secretKey);

			//test encryption
			EncryptedResult result = engine.encrypt(buffer, keyBuffer, BlockModeEnum.CBC);

			//test decryption
			ByteBuffer decryptedResult = engine.decrypt(result, keyBuffer);

			String message = new String(decryptedResult.array(), "UTF-8");

			assertTrue(originalString.equals(message));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testCFBEncryption(){

	}

	public void testOFBEncryption(){

	}

	public void testPCPBEncryption(){

	}
}