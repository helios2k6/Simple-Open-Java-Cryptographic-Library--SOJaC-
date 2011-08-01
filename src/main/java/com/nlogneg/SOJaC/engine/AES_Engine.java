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

	private static int[] KEY_SIZES = {128, 192, 256};

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
			BlockModeEnum blockMode) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidAlgorithmParameterException {

		int maxKeySize = CipherUtils.getLargestKeySize(CipherEnum.AES.toString());

		int keySize = 0;

		MessageDigestEnum digestMethod = MessageDigestEnum.SHA256;

		MessageDigest digest = MessageDigest.getInstance(digestMethod.toString());

		for(int i : KEY_SIZES){
			if(maxKeySize > i){
				keySize = i;
			}
		}
		
		byte[] preKey = digest.digest(key.array());

		byte[] keyAsBytes = new byte[keySize/8];

		for(int index = 0; index < keySize/8; index++){
			keyAsBytes[index] = new Byte(preKey[index]);
		}

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

	}

	@Override
	public ByteBuffer decrypt(EncryptedResult cipherText, ByteBuffer key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		ByteBuffer result;

		BlockModeEnum blockMode = cipherText.getBlockMode();

		Cipher cipher = Cipher.getInstance(CipherEnum.AES.toString() + 
				"/" + cipherText.getBlockMode().toString() + "/" + CipherUtils.DEFAULT_PADDING);

		int maxKeySize = CipherUtils.getLargestKeySize(CipherEnum.AES.toString());

		int keySize = 0;

		MessageDigestEnum digestMethod = MessageDigestEnum.SHA256;

		MessageDigest digest = MessageDigest.getInstance(digestMethod.toString());

		for(int i : KEY_SIZES){
			if(maxKeySize > i){
				keySize = i;
			}
		}

		byte[] preKey = digest.digest(key.array());

		byte[] keyAsBytes = new byte[keySize/8];

		for(int index = 0; index < keySize/8; index++){
			keyAsBytes[index] = new Byte(preKey[index]);
		}

		SecretKeySpec secretKey  = new SecretKeySpec(keyAsBytes, CipherEnum.AES.toString());

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

	}



}
