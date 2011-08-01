package com.nlogneg.SOJaC.interfaces;

import java.nio.ByteBuffer;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.objects.EncryptedResult;

public interface EncryptionEngine {
	
	public static final String DEFAULT_PADDING = "PKCS5Padding";
	public static final String DEFAULT_RANDOM = "SHA1PRNG";
	
	public EncryptedResult encrypt(ByteBuffer message, ByteBuffer key, BlockModeEnum blockMode) throws Exception;
	public ByteBuffer decrypt(EncryptedResult cipherText, ByteBuffer key) throws Exception;
}
