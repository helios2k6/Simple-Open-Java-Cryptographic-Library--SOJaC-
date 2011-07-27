package com.nlogneg.SOJaC.engine;

import java.nio.ByteBuffer;
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
import com.nlogneg.SOJaC.interfaces.EncryptionEngine;

public class AES_Engine implements EncryptionEngine{

	@Override
	public ByteBuffer encrypt(ByteBuffer message, ByteBuffer key,
			BlockModeEnum blockMode) {
		try {
			
			MessageDigest dig = MessageDigest.getInstance(MessageDigestEnum.SHA256.toString());
			
			byte[] keyDigest = dig.digest(key.array());
			
			SecretKeySpec keySpec = new SecretKeySpec(keyDigest, CipherEnum.AES.toString());
			Cipher cipher = Cipher.getInstance(CipherEnum.AES.toString());
			
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			
			byte[] ciphertext = cipher.doFinal(message.array());
			
			
			return ByteBuffer.wrap(ciphertext);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ByteBuffer decrypt(ByteBuffer message, ByteBuffer key,
			BlockModeEnum blockMode) {
		// TODO Auto-generated method stub
		return null;
	}

}
