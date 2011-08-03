package com.nlogneg.SOJaC.engine;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.enums.CipherEnum;
import com.nlogneg.SOJaC.enums.MessageDigestEnum;
import com.nlogneg.SOJaC.interfaces.EncryptionEngine;
import com.nlogneg.SOJaC.objects.EncryptedResult;

public abstract class SuperEncryptionEngine implements EncryptionEngine {

	@Override
	public abstract EncryptedResult encrypt(ByteBuffer message, ByteBuffer key,
			BlockModeEnum blockMode) throws Exception;

	@Override
	public abstract ByteBuffer decrypt(EncryptedResult cipherText, ByteBuffer key)
			throws Exception;
	
	
	protected EncryptedResult finalizeEncryption(Cipher cipher, BlockModeEnum blockMode, 
			ByteBuffer message, SecretKeySpec secretKey, MessageDigestEnum digestMethod) 
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		if(BlockModeEnum.requiresIV(blockMode)){
			SecureRandom random = SecureRandom.getInstance(CipherUtils.DEFAULT_SECURE_RANDOM);
			byte[] IV_seed = new byte[16];
			random.nextBytes(IV_seed);
			IvParameterSpec IV = new IvParameterSpec(IV_seed);

			cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);

			byte[] ciphertext = cipher.doFinal(message.array());

			EncryptedResult result = new EncryptedResult(ciphertext, 
					CipherEnum.AES, 
					blockMode, 
					IV.getIV(), 
					null, 
					digestMethod);

			return result;
		}else{
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] ciphertext = cipher.doFinal(message.array());

			EncryptedResult result = new EncryptedResult(ciphertext, 
					CipherEnum.AES, 
					blockMode,
					null,
					null, 
					digestMethod);

			return result;
		}
	}
}
