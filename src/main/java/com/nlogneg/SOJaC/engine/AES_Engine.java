package com.nlogneg.SOJaC.engine;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.enums.CipherEnum;
import com.nlogneg.SOJaC.enums.MessageDigestEnum;
import com.nlogneg.SOJaC.interfaces.EncryptionEngine;
import com.nlogneg.SOJaC.objects.EncryptedResult;

public class AES_Engine implements EncryptionEngine{

	private AES_Engine() {

	}

	private static class AES_EngineHolder {
		private static final AES_Engine INSTANCE = new AES_Engine();
	}

	public static AES_Engine getInstance() {
		return AES_EngineHolder.INSTANCE;
	}
	
	@Override
	public EncryptedResult encrypt(ByteBuffer message, ByteBuffer key,
			BlockModeEnum blockMode) {

		try {
			int keySize = CipherUtils.getLargestKeySize(CipherEnum.AES.toString());

			MessageDigestEnum digestMethod = MessageDigestEnum.getStrongestDigest(keySize);

			MessageDigest digest = MessageDigest.getInstance(digestMethod.toString());

			byte[] keyAsBytes = digest.digest(key.array());
			SecretKeySpec secretKey  = new SecretKeySpec(keyAsBytes, CipherEnum.AES.toString());

			Cipher cipher = Cipher.getInstance(CipherEnum.AES.toString() + "/" + blockMode.toString() + "/"
					+ CipherUtils.DEFAULT_PADDING);

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
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ByteBuffer decrypt(EncryptedResult cipherText, ByteBuffer key) {
		try {
			
			ByteBuffer result;
			
			BlockModeEnum blockMode = cipherText.getBlockMode();

			Cipher cipher = Cipher.getInstance(CipherEnum.AES.toString() + 
					"/" + cipherText.getBlockMode().toString() + "/" + CipherUtils.DEFAULT_PADDING);

			MessageDigest digest = MessageDigest.getInstance(cipherText.getDigest().toString());
			byte [] keyAsDigest = digest.digest(key.array());

			SecretKeySpec secretKey  = new SecretKeySpec(keyAsDigest, CipherEnum.AES.toString());

			if(BlockModeEnum.requiresIV(blockMode)){
				IvParameterSpec IV = new IvParameterSpec(cipherText.getIv());

				cipher.init(Cipher.DECRYPT_MODE, secretKey, IV);

				byte[] deciphertext = cipher.doFinal(cipherText.getCipherText());

				result = ByteBuffer.wrap(deciphertext);
			}else{
				cipher.init(Cipher.DECRYPT_MODE, secretKey);

				byte[] deciphertext = cipher.doFinal(cipherText.getCipherText());
				
				result = ByteBuffer.wrap(deciphertext);
			}
			
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}



}
