package com.nlogneg.SOJaC.engine;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.enums.CipherEnum;
import com.nlogneg.SOJaC.enums.MessageDigestEnum;
import com.nlogneg.SOJaC.objects.EncryptedResult;

/**
 * This class is meant to act as the DES encryption engine. This engine does NOT do 
 * Triple DES. That's a separate engine
 * @author Andrew
 *
 */
public class DES_Engine extends SuperEncryptionEngine{

	private static final int KEY_SIZE = 64;
	
	@Override
	public EncryptedResult encrypt(ByteBuffer message, ByteBuffer key,
			BlockModeEnum blockMode) throws NoSuchAlgorithmException, NoSuchPaddingException, 
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, 
			BadPaddingException {
		
		MessageDigest digest = MessageDigest.getInstance(MessageDigestEnum.SHA256.toString());
		
		byte[] keyDigested = digest.digest(key.array());
		
		byte[] keyAsBytes = new byte[KEY_SIZE];
		
		for(int index = 0; index < KEY_SIZE; index++){
			keyAsBytes[index] = new Byte(keyDigested[index]);
		}
		
		SecretKeySpec secretKey = new SecretKeySpec(keyAsBytes, CipherEnum.DES.toString());
		
		Cipher cipher = Cipher.getInstance(CipherEnum.DES.toString() + "/" + blockMode.toString() + "/"
				+ CipherUtils.DEFAULT_PADDING);
		
		return super.finalizeEncryption(cipher, blockMode, message, secretKey, MessageDigestEnum.SHA256);
	}

	@Override
	public ByteBuffer decrypt(EncryptedResult cipherText, ByteBuffer key) {
		// TODO Auto-generated method stub
		return null;
	}



}
